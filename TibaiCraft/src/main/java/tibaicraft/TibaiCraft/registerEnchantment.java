package tibaicraft.TibaiCraft;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

import tibaicraft.TibaiCraft.enchantment.mana_resonance;
import tibaicraft.TibaiCraft.enchantment.pinning_shot;
import tibaicraft.TibaiCraft.enchantment.wind_blessing;

public class registerEnchantment {
	
	public static final Enchantment WIND_BLESSING = new wind_blessing("wind_blessing", "Wind Blessing", 3);
	public static final Enchantment PINNING_SHOT = new pinning_shot("pinning_shot", "Pinning Shot", 7);
	public static final Enchantment MANA_RESONANCE = new mana_resonance("mana_resonance", "Mana Resonance", 1);
	
	public static void register(Enchantment enchantment)
	{
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);
		if(!registered) {resEnchantment(enchantment);}
	}
	public static void resEnchantment(Enchantment enchantment)
	{
		boolean registered = true;
		try {
			Field field = Enchantment.class.getDeclaredField("acceptingNew");
			field.setAccessible(true);
			field.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered) {
			
		}
	}
}
