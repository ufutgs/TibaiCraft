package tibaicraft.TibaiCraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class GrinderListener  implements Listener{
	private main plugin;
	public GrinderListener(main plugin)
	{
		this.plugin=plugin;
	}
	@EventHandler
	public void OnPlaceGrinder(BlockPlaceEvent e)
	{
		if(e.getBlockPlaced().getState() instanceof org.bukkit.block.Furnace )
		{
			org.bukkit.block.Furnace Grinder = (org.bukkit.block.Furnace) e.getBlockPlaced().getState();
			if(!e.getItemInHand().equals(plugin.grinder))
			{
				return;
			}
			Grinder.getPersistentDataContainer().set(plugin.grinder_key, PersistentDataType.STRING, "Grinder_tag");
			Grinder.update();
		}
	}

	@EventHandler
	public void OnGrinderActivate(FurnaceSmeltEvent e)
	{
		if(e.getBlock().getState() instanceof org.bukkit.block.Furnace)
		{
			org.bukkit.block.Furnace Grinder = (org.bukkit.block.Furnace) e.getBlock().getState();
			if(Grinder.getPersistentDataContainer().has(plugin.grinder_key,  PersistentDataType.STRING))
			{
				return;
			}
			
		}
		if(e.getSource().getType()==Material.COBBLESTONE)
		{
			e.setResult(new ItemStack(Material.STONE));
		}
	}
}
