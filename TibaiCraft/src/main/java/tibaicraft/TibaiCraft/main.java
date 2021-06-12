package tibaicraft.TibaiCraft;

import java.util.ArrayList;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;



public class main extends JavaPlugin {
	public ItemStack e_chest;
	public NamespacedKey e_chest_key;
	public ItemStack grinder;
	public NamespacedKey grinder_key;
	public ItemStack Filter;
	public NamespacedKey filter_key;
	public ItemStack lighting_pulse;
	public NamespacedKey lighting_pulse_key;
	 public void onEnable()
	    {
		 getServer().getPluginManager().registerEvents(new E_chestListener(this), this);
		 getServer().getPluginManager().registerEvents(new GrinderListener(this), this);
		 getServer().getPluginManager().registerEvents(new FilterListener(this), this);
		 getServer().getPluginManager().registerEvents(new lighting_pulse(this), this);
		 this.getCommand("lighting_pulse").setExecutor(new SummonItemCommand(this));
		 Bukkit.addRecipe(e_chest());
		 Bukkit.addRecipe(Grinder());
		 Bukkit.addRecipe(flint());
		 Bukkit.addRecipe(filter_cpd());
		 Bukkit.addRecipe(Filter());
		 lighting_pulse = lighting_pulse();
	    }
	 public void onDisable()
	    {
	    }
	 
// recipe
	   private ShapedRecipe e_chest()
	   {
		   e_chest = new ItemStack(Material.CHEST);
		   NamespacedKey key = new NamespacedKey(this, "E_chest");
		   e_chest_key = key;
		   ItemMeta itemMeta = e_chest.getItemMeta();
		   itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "E_chest_tag");
		   itemMeta.setDisplayName(ChatColor.DARK_GREEN+"E_Chest");
		   e_chest.setItemMeta(itemMeta);
		  ShapedRecipe recipe = new ShapedRecipe(key, e_chest);
		  recipe.shape("EEE","ECE","EEE");
		  recipe.setIngredient('E', Material.ENDER_EYE);
		  recipe.setIngredient('C', Material.ENDER_CHEST);
		  return recipe;
	   }
	   private ShapedRecipe Grinder()
	   {
		   grinder = new ItemStack(Material.FURNACE);
		   NamespacedKey key = new NamespacedKey(this, "Grinder");
		   grinder_key = key;
		   ItemMeta itemMeta = grinder.getItemMeta();
		   itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Grinder_tag");
		   itemMeta.setDisplayName(ChatColor.DARK_GREEN+"Grinder");
		   grinder.setItemMeta(itemMeta);
		  ShapedRecipe recipe = new ShapedRecipe(key, grinder);
		  recipe.shape("IHI","ECE","III");
		  recipe.setIngredient('C', Material.BLAST_FURNACE);
		  recipe.setIngredient('E', Material.DIAMOND);
		  recipe.setIngredient('H', Material.HOPPER);
		  recipe.setIngredient('I', Material.IRON_BLOCK);
		  return recipe;
	   }
	   private FurnaceRecipe flint()
	   {
		  ItemStack flint = new ItemStack(Material.FLINT,10);
		  NamespacedKey key = new NamespacedKey(this, "flint");
		  return new FurnaceRecipe(key, flint, Material.COBBLESTONE, 0, 200);
	   }
	   private FurnaceRecipe filter_cpd()
	   {
		  ItemStack flint = new ItemStack(Material.GUNPOWDER);
		  NamespacedKey key = new NamespacedKey(this, "iron_nugget");
		  return new FurnaceRecipe(key, flint, Material.FLINT, 0, 40);
	   }	   
	   private ShapedRecipe Filter()
	   {
		   Filter = new ItemStack(Material.FURNACE);
		   NamespacedKey key = new NamespacedKey(this, "Filter");
		   filter_key = key;
		   ItemMeta itemMeta = grinder.getItemMeta();
		   itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Filter_tag");
		   itemMeta.setDisplayName(ChatColor.DARK_GREEN+"Filter");
		   Filter.setItemMeta(itemMeta);
		  ShapedRecipe recipe = new ShapedRecipe(key, Filter);
		  recipe.shape("   ","ECE","BBB");
		  recipe.setIngredient('C', Material.BLAST_FURNACE);
		  recipe.setIngredient('E', Material.WATER_BUCKET);
		  recipe.setIngredient('B', Material.BREWING_STAND);
		  return recipe;
	   }
// weapon
	   private ItemStack lighting_pulse()
	   {
		   ItemStack crossbow = new ItemStack(Material.CROSSBOW);
		   ItemMeta crossbowItemMeta = crossbow.getItemMeta();
		   crossbowItemMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Lighting Pulse");
		   List<String> lore = new ArrayList<String>();
		   lore.add("Shoot them down, like a Lighting.");
		   lore.add("");
		   lore.add(ChatColor.GREEN+"When In Main Hand:");
		   lore.add(ChatColor.GREEN+"Projectile Damage: 7");
		   lore.add(ChatColor.GREEN+"Projectile Speed: 3");
		   crossbowItemMeta.setLore(lore);
		   NamespacedKey key = new NamespacedKey(this, "lighting_pulse");
		   lighting_pulse_key = key;
		   crossbowItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "lighting_pulse");
		   crossbowItemMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
		   crossbow.setItemMeta(crossbowItemMeta);
		   return crossbow;
	   }
}
