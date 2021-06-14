package tibaicraft.TibaiCraft.item;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tibaicraft.TibaiCraft.main;
import tibaicraft.TibaiCraft.registerEnchantment;

public class tempest_blessing implements Listener{
	@SuppressWarnings("unused")
	private main plugin;
	public tempest_blessing(main main) {
		plugin = main;
	}
	@EventHandler
	public void Onwear(PlayerInteractEvent e)
	{
		if(e.getItem()!=null&&e.getItem().getItemMeta().getEnchants().containsKey(registerEnchantment.WIND_BLESSING)&&e.getPlayer().getInventory().getLeggings()==null)
		{
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				int lvl = e.getItem().getItemMeta().getEnchants().get(registerEnchantment.WIND_BLESSING);
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, lvl-1));
				return;
			}
		}
	}
	@EventHandler
	public void OnclickInventory(InventoryClickEvent e)
	{
		if(e.getWhoClicked() instanceof Player)
		{
			Player player = (Player) e.getWhoClicked();
			if(e.getClickedInventory()==player.getInventory())
			{
				if(e.getCursor().getItemMeta()!=null&&e.getCursor().getItemMeta().getEnchants().containsKey(registerEnchantment.WIND_BLESSING)&&e.getRawSlot()==7)
				{
					int lvl = e.getCursor().getItemMeta().getEnchants().get(registerEnchantment.WIND_BLESSING);
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, lvl-1));
					return;
				}
				else if(e.getCurrentItem().getItemMeta()!=null&&e.getCurrentItem().getItemMeta().getEnchants().containsKey(registerEnchantment.WIND_BLESSING)&&e.getRawSlot()==7)
				{
					if(e.isShiftClick())
					{
						int lvl = e.getCursor().getItemMeta().getEnchants().get(registerEnchantment.WIND_BLESSING);
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, lvl-1));
					}
					else {player.removePotionEffect(PotionEffectType.JUMP);}
					return;
				}
			}
		}
	}
}
