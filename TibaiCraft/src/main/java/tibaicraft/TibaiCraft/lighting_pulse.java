package tibaicraft.TibaiCraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;


public class lighting_pulse implements Listener {

	private main plugin;
	private double damage = 7;
	public lighting_pulse(main main) {
		plugin = main;
	}
	@EventHandler
	public void ArrowDamage(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Arrow)
		{
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.hasMetadata("lighting_pulse"))
			{
				e.setDamage(damage);
				Bukkit.getPlayer(arrow.getMetadata("lighting_pulse").get(0).asString()).sendMessage(Double.toString(e.getFinalDamage()));
				return;
			}
			else if(arrow.hasMetadata("player_shot"))
			{
				Bukkit.getPlayer(arrow.getMetadata("player_shot").get(0).asString()).sendMessage(Double.toString(e.getFinalDamage()));
				return;
			}
		}
		Player player = (Player) e.getDamager();
		player.sendMessage(Double.toString(e.getFinalDamage()));
		return;
	}
	
	@EventHandler
	public void ProjectileSpeed(EntityShootBowEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			e.getProjectile().setMetadata("player_shot", new FixedMetadataValue(plugin, player.getName()));
			if (e.getBow().getItemMeta().getPersistentDataContainer().has(plugin.lighting_pulse_key, PersistentDataType.STRING)) {
				e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(3));
				e.getProjectile().setMetadata("lighting_pulse", new FixedMetadataValue(plugin, player.getName()));
			}
		}
	}
}
