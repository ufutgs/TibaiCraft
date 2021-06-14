package tibaicraft.TibaiCraft.item;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTrident;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import net.minecraft.server.v1_16_R3.EntityThrownTrident;
import tibaicraft.TibaiCraft.main;
import tibaicraft.TibaiCraft.registerEnchantment;

// Handle endless wrath function , and also mana resonance
public class endless_wrath implements Listener {
	private main plugin;
	public endless_wrath(main main) {
		plugin=main;
	}
	@EventHandler
	public void OnCriticalStrike(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Trident&&e.getEntity() instanceof LivingEntity)
		{
			if(e.getDamager().hasMetadata("endless_wrath"))
			{
				List<Entity> entities=e.getEntity().getNearbyEntities(3,3,3);
				for(Entity f : entities)
				{
					if(f instanceof Damageable && !(f instanceof Player))
					{
						((Damageable) f).damage(10);
						f.setFireTicks(100);
					}
				}
				e.getEntity().setFireTicks(100);
				e.setDamage(20);
				e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());
				Trident trident = (Trident) e.getDamager();
				trident.remove();
				}
		}
		else if(e.getDamager() instanceof Player)
		{
			Player player = (Player) e.getDamager();
			if(isCritical(player)&&player.getInventory().getItemInMainHand().getItemMeta()!=null&&player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(registerEnchantment.MANA_RESONANCE))
			{
				List<Entity> entities=e.getEntity().getNearbyEntities(3,3,3);
				double dmg = e.getDamage()*0.4;
				org.bukkit.util.Vector vector = player.getLocation().getDirection().normalize().add(new org.bukkit.util.Vector(0, 0.6, 0));
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BELL_USE, (float) 0.8, (float) 0.8);
				mana_resonance(e.getEntity());
				for(Entity f : entities)
				{
					if(f instanceof Damageable && !(f instanceof Player))
					{
						mana_resonance(f);
						((Damageable) f).damage(dmg);
						f.setVelocity(vector);
					}
				}
			}
		}
			
	}
	@EventHandler
	private void onhit(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Trident)
		{
			if(e.getEntity().hasMetadata("endless_wrath"))
			{
				Trident trident = (Trident) e.getEntity();
				trident.remove();
			}
		}

	}
	@EventHandler
	public void OnThrow(ProjectileLaunchEvent e)
	{
		if(e.getEntity() instanceof Trident && e.getEntity().getShooter() instanceof Player)
		{
			EntityThrownTrident t = ((CraftTrident) e.getEntity()).getHandle();
			ItemStack thrownTrident = CraftItemStack.asBukkitCopy(t.trident);
			Player player= (Player) e.getEntity().getShooter();
			if(thrownTrident.getItemMeta().getPersistentDataContainer().has(plugin.endless_wrath_Key, PersistentDataType.STRING))
			{
				e.getEntity().setMetadata("player_shot", new FixedMetadataValue(plugin, player.getName()));
				e.getEntity().setMetadata("endless_wrath", new FixedMetadataValue(plugin, player.getName()));
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(4));
				if(isOffhanded(player))
				{
					player.getInventory().setItemInOffHand(thrownTrident);
				}
				else {
					player.getInventory().setItemInMainHand(thrownTrident);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean isCritical(Player player)
	{
	return	player.getFallDistance() > 0.0F &&
        !player.isOnGround() &&
        !player.isInsideVehicle() &&
        !player.hasPotionEffect(PotionEffectType.BLINDNESS) &&
        player.getLocation().getBlock().getType() != Material.LADDER &&
        player.getLocation().getBlock().getType() != Material.VINE;
	}
	
	private boolean isOffhanded(Player player)
	{
		return player.getInventory().getItemInOffHand().getItemMeta()!=null&&
				player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(plugin.endless_wrath_Key, PersistentDataType.STRING)&&
				 (player.getInventory().getItemInMainHand().getItemMeta()==null||(player.getInventory().getItemInMainHand().getItemMeta()!=null&&
				!(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(plugin.endless_wrath_Key, PersistentDataType.STRING))));
	}
private void mana_resonance(Entity entity)
	{
		BukkitTask task =Bukkit.getScheduler().runTaskTimer(plugin, ()->{entity.getWorld().spawnParticle(Particle.SWEEP_ATTACK,entity.getLocation().add(0, 1, 0),1);for(int i =1;i<10;i++)
		{
			double number1 = new Random().nextDouble();
			double number2 = new Random().nextDouble();
			double number3 = new Random().nextDouble();
			entity.getWorld().spawnParticle(Particle.SPELL_INSTANT,entity.getLocation().add(number1*2-1, number3*2-1, number2*2-1),0,number1*0.1,number2*0.1,number2*0.1);
			entity.getWorld().spawnParticle(Particle.CRIT_MAGIC,entity.getLocation().add(number1*2-1, number3*2-1, number2*2-1),0,number1*0.1,number2*0.1,number2*0.1);
			entity.getWorld().spawnParticle(Particle.SPELL_MOB,entity.getLocation().add(number1*2-1, number3*2-1, number2*2-1),0,number1*0.1,number2*0.1,number2*0.1);
		}}, 0, 1);
		Bukkit.getScheduler().runTaskLater(plugin, ()->{Bukkit.getScheduler().cancelTask(task.getTaskId());}, 2);
	}
}
