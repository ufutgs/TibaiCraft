package tibaicraft.TibaiCraft.item;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tibaicraft.TibaiCraft.main;


public class lighting_pulse implements Listener {

	private main plugin;
	private double damage = 10;
	public lighting_pulse(main main) {
		plugin = main;
	}
	@EventHandler
	public void ArrowDamage(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Arrow)
		{
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.hasMetadata("pinning_shot"))
			{
				if(e.getEntity() instanceof LivingEntity)
				{
					LivingEntity entity = (LivingEntity) e.getEntity();
					entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 160,arrow.getMetadata("pinning_shot").get(0).asInt()-1));
				}
			}
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
		if(e.getDamager() instanceof Player)
		{
			Player player = (Player) e.getDamager();
			player.sendMessage(Double.toString(e.getFinalDamage()));
		}
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
				e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(4));
				e.getProjectile().setMetadata("lighting_pulse", new FixedMetadataValue(plugin, player.getName()));
				e.getProjectile().setMetadata("pinning_shot", new FixedMetadataValue(plugin, 4));
			}
		}
	}
}
