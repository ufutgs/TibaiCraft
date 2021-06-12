package tibaicraft.TibaiCraft;

import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class FilterListener  implements Listener{
	private main plugin;
	private Material[] smeltingMaterials = {Material.AIR,Material.STRING,Material.GUNPOWDER,Material.REDSTONE,Material.IRON_NUGGET,Material.GOLD_NUGGET,Material.EMERALD};
	private double[] smeltingProb = {1,0.8,0.7,0.5,0.4,0.3,0.05};
	public FilterListener(main plugin)
	{
		this.plugin=plugin;
	}
	@EventHandler
	public void OnPlaceFilter(BlockPlaceEvent e)
	{
		if(e.getBlockPlaced().getState() instanceof org.bukkit.block.Furnace )
		{
			org.bukkit.block.Furnace Filter = (org.bukkit.block.Furnace) e.getBlockPlaced().getState();
			if(!e.getItemInHand().equals(plugin.Filter))
			{
				return;
			}
			Filter.getPersistentDataContainer().set(plugin.filter_key, PersistentDataType.STRING, "Filter_tag");
			Filter.update();
		}
	}
	
	@EventHandler 
	public void CancelSmelting(FurnaceBurnEvent e)
	{
		if(e.getBlock().getState() instanceof Furnace)
		{
			Furnace Filter =(Furnace) e.getBlock().getState();
			if(Filter.getInventory().getSmelting().getType()==Material.FLINT)
			{
				if(Filter.getPersistentDataContainer().has(plugin.filter_key,  PersistentDataType.STRING))
				{
					for (HumanEntity player : Filter.getInventory().getViewers()) {
					Player	player1 = (Player) player;
					player1.updateInventory();
					}
					return;
				}
				else
				{
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void OnFiltering(FurnaceSmeltEvent e)
	{
		if(e.getBlock().getState() instanceof org.bukkit.block.Furnace)
		{
			org.bukkit.block.Furnace Filter = (org.bukkit.block.Furnace) e.getBlock().getState();
			if(Filter.getPersistentDataContainer().has(plugin.filter_key,  PersistentDataType.STRING)&&e.getSource().getType()==Material.FLINT)
			{
				ItemStack chosenItemStack = new ItemStack(smelItemStack());
				if(chosenItemStack.getType()==Material.AIR)
				{
					e.setResult(new ItemStack(Material.AIR));
					return;
				}
				if(Filter.getInventory().getResult()!=null&&Filter.getInventory().getResult().getType()!=chosenItemStack.getType())
				{
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().clone().add(0,2,0), chosenItemStack);
					e.setResult(new ItemStack(Material.AIR));
				}
				else {
					e.setResult(chosenItemStack);
				}
				return;
			}
			
		}
		if(e.getSource().getType()==Material.FLINT)
		{
			e.setCancelled(true);
		}
	}
	private Material smelItemStack()
	{
		double roll =0;
		Material chosenMaterial = null;
		for (int i =0;i<smeltingMaterials.length;i++) {
			double tmp = smeltingProb[i]+Math.random();
			if(tmp>roll)
			{roll=tmp;
			chosenMaterial=smeltingMaterials[i];}
		}
		return chosenMaterial;
	}
}
