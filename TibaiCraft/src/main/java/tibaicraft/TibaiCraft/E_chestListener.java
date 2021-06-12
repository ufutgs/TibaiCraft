package tibaicraft.TibaiCraft;

import java.util.HashMap;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public class E_chestListener  implements Listener{
	private main plugin;
	public  E_chestListener(main plugin) {
		 this.plugin=plugin;
	}
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent e)
	{
		if(e.getBlockPlaced().getState() instanceof Chest )
		{
			Chest E_Chest = (Chest) e.getBlockPlaced().getState();
			if(!e.getItemInHand().equals(plugin.e_chest))
			{
				return;
			}
			E_Chest.getPersistentDataContainer().set(plugin.e_chest_key, PersistentDataType.STRING, "E_chest_tag");
			E_Chest.update();
		}
	}
	@EventHandler
	public void OnDispenserShoot(BlockDispenseEvent e)
	{
		if(e.getBlock().getType()==Material.DISPENSER&&e.getItem().getType()==Material.COAL)
		{
			Location chestLocation = e.getBlock().getLocation().clone().add(0,1,0);
			if(chestLocation.getBlock().getState() instanceof Chest)
			{
				Chest chest = (Chest) chestLocation.getBlock().getState();
				if(chest.getPersistentDataContainer().has(plugin.e_chest_key, PersistentDataType.STRING)&&chest.getInventory().contains(Material.WHEAT_SEEDS))
				{

					HashMap<Integer, ? extends ItemStack>Inventory = chest.getInventory().all(Material.WHEAT_SEEDS);
					Integer[] slot =new Integer[Inventory.keySet().size()];
					slot=Inventory.keySet().toArray(slot);
					ItemStack[] itemStacks = new ItemStack[Inventory.values().size()];
					itemStacks = Inventory.values().toArray(itemStacks);
					int wheat_count = 8;
					for(int i =0;i<Inventory.size();i++)
					{
						int remaining_itemstack = wheat_count< itemStacks[i].getAmount() ? itemStacks[i].getAmount()-wheat_count : 0;
						wheat_count = remaining_itemstack>0 ? 0 : wheat_count-itemStacks[i].getAmount();
						if(wheat_count==0)
						{
							chest.getInventory().getItem(slot[i]).setAmount(remaining_itemstack);
							break;
						}
						chest.getInventory().setItem(slot[i], new ItemStack(Material.AIR));
					}
					if(wheat_count==8) {return;}
					Dispenser blockDispenser = (Dispenser) e.getBlock().getState();
					int a =blockDispenser.getInventory().first(Material.COAL);
					ItemStack coal = blockDispenser.getInventory().getItem(blockDispenser.getInventory().first(Material.COAL));
					coal.setAmount(coal.getAmount()-1);
					blockDispenser.getInventory().setItem(a,coal);
					blockDispenser.update();
					for(int i =0; i<8-wheat_count;i++)
					{
						Location wheatLocation = chestLocation.clone().add(i>0&&i<4? 1:i==4||i==0?0:-1 , 1,i>2&&i<6? -1:i==2||i==6?0:1);
						if(wheatLocation.getBlock().getType().equals(Material.AIR))
							{wheatLocation.getBlock().setType(Material.WHEAT);}
						else {
							chest.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS,1));
						}
					}
					e.setCancelled(true);
					
				}
			}
		}
	}
}
