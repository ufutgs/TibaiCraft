package tibaicraft.TibaiCraft;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagDouble;
import net.minecraft.server.v1_16_R3.NBTTagInt;
import net.minecraft.server.v1_16_R3.NBTTagList;
import net.minecraft.server.v1_16_R3.NBTTagString;
import tibaicraft.TibaiCraft.item.endless_wrath;
import tibaicraft.TibaiCraft.item.lighting_pulse;
import tibaicraft.TibaiCraft.item.tempest_blessing;
import tibaicraft.TibaiCraft.item.normal_armor.steel_armor;



public class main extends JavaPlugin {
	public ItemStack e_chest;
	public NamespacedKey e_chest_key;
	public ItemStack grinder;
	public NamespacedKey grinder_key;
	public ItemStack Filter;
	public NamespacedKey filter_key;
	public NamespacedKey unforgetable_itemKey=new NamespacedKey(this, "unique_item");
	public ItemStack lighting_pulse;
	public NamespacedKey lighting_pulse_key;
	public ItemStack tempest_blessing;
	public NamespacedKey tempest_blessing_key;
	public ItemStack endless_wrath;
	public NamespacedKey endless_wrath_Key;
	
	private steel_armor steel_armor = new steel_armor(this);
	 public void onEnable()
	    {
		 registerEnchantment.register(registerEnchantment.WIND_BLESSING);
		 registerEnchantment.register(registerEnchantment.PINNING_SHOT);
		 registerEnchantment.register(registerEnchantment.MANA_RESONANCE);
		 getServer().getPluginManager().registerEvents(new E_chestListener(this), this);
		 getServer().getPluginManager().registerEvents(new GrinderListener(this), this);
		 getServer().getPluginManager().registerEvents(new FilterListener(this), this);
		 getServer().getPluginManager().registerEvents(new lighting_pulse(this), this);
		 getServer().getPluginManager().registerEvents(new tempest_blessing(this), this);
		 getServer().getPluginManager().registerEvents(new endless_wrath(this), this);
		 getServer().getPluginManager().registerEvents(steel_armor, this);
		 this.getCommand("lighting_pulse").setExecutor(new SummonItemCommand(this));
		 Bukkit.addRecipe(steel_armor.steelRecipe());
		 Bukkit.addRecipe(steel_armor.SH());
		 Bukkit.addRecipe(steel_armor.SL());
		 Bukkit.addRecipe(steel_armor.SC());
		 Bukkit.addRecipe(steel_armor.SB());
		 Bukkit.addRecipe(e_chest());
		 Bukkit.addRecipe(Grinder());
		 Bukkit.addRecipe(flint());
		 Bukkit.addRecipe(filter_cpd());
		 Bukkit.addRecipe(Filter());
		 lighting_pulse = lighting_pulse();
		 tempest_blessing = tempest_blessing();
		 endless_wrath=endless_wrath();
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
// weapon & armor
	   private ItemStack lighting_pulse()
	   {
		   ItemStack crossbow = new ItemStack(Material.CROSSBOW);
		   ItemMeta crossbowItemMeta = crossbow.getItemMeta();
		   crossbowItemMeta.setDisplayName(ChatColor.of("#3dd8ff")+"Lighting Pulse");
		   List<String> lore = new ArrayList<String>();
		   lore.add(ChatColor.GRAY+"Pinning Shot IV");
		   lore.add(ChatColor.of("#006deb")+"Shoot and run, like a Lighting.");
		   lore.add("");
		   lore.add(ChatColor.GRAY+"When In Main Hand:");
		   lore.add(ChatColor.DARK_GREEN+"10 Projectile Damage");
		   lore.add(ChatColor.DARK_GREEN+"4 Projectile Speed");
		   crossbowItemMeta.setLore(lore);
		   NamespacedKey key = new NamespacedKey(this, "lighting_pulse");
		   lighting_pulse_key = key;
		   crossbowItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "lighting_pulse");
		   crossbowItemMeta.getPersistentDataContainer().set(unforgetable_itemKey, PersistentDataType.STRING, "");
		   crossbowItemMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
		   crossbowItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		   crossbow.setItemMeta(crossbowItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(crossbow);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.02));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(565656));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("mainhand"));
			modList.add(speed);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			crossbow = CraftItemStack.asBukkitCopy(nmStack);
		   return crossbow;
	   }
	   private ItemStack tempest_blessing()
	   {
		   ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
		   ItemMeta leggingItemMeta = legging.getItemMeta();
		   List<String> lore = new ArrayList<String>();
		   leggingItemMeta.setDisplayName(ChatColor.of("#1bf5d8")+"Sky Thruster");
		   lore.add(ChatColor.GRAY+"Wind Blessing III");
		   lore.add(ChatColor.of("#4fffd9")+"Sorcerer of the Wind, fly through the sky.");
		   leggingItemMeta.setLore(lore);
		   leggingItemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		   NamespacedKey key = new NamespacedKey(this, "tempest_blessing");
		   tempest_blessing_key = key;
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "tempest_blessing");
		   leggingItemMeta.getPersistentDataContainer().set(unforgetable_itemKey, PersistentDataType.STRING, "");
		   legging.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(legging);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.02));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(22222));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("legs"));
			armor.set("AttributeName",NBTTagString.a("generic.armor"));
			armor.set("Name",NBTTagString.a("generic.armor"));
			armor.set("Amount",NBTTagDouble.a(2));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(333333));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("legs"));
			modList.add(speed);
			modList.add(armor);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			legging = CraftItemStack.asBukkitCopy(nmStack);
			legging.addUnsafeEnchantment(registerEnchantment.WIND_BLESSING, 3);
			return legging;
	   }
	   private ItemStack endless_wrath()
	   {
		   ItemStack trident = new ItemStack(Material.TRIDENT);
		   ItemMeta leggingItemMeta = trident.getItemMeta();
		   List<String> lore = new ArrayList<String>();
		   leggingItemMeta.setDisplayName(ChatColor.BOLD+""+ChatColor.DARK_GRAY+"Endless Wrath");
		   lore.add(ChatColor.GRAY+"Thunder Strike");
		   lore.add(ChatColor.GRAY+"Mana Resonance");
		   lore.add(ChatColor.of("#ad0009")+"Flood them with the endless wrath.");
		   lore.add("");
		   lore.add(ChatColor.GRAY+"When In Main Hand:");
		   lore.add(ChatColor.DARK_GREEN+"10 Attack Damage");
		   lore.add(ChatColor.DARK_GREEN+"2 Attack Speed");
		   lore.add(ChatColor.DARK_GREEN+"10 Projectile Damage");
		   lore.add(ChatColor.DARK_GREEN+"4 Projectile Speed");
		   leggingItemMeta.setLore(lore);
		   leggingItemMeta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
		   leggingItemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		   NamespacedKey key = new NamespacedKey(this, "endless_wrath");
		   endless_wrath_Key = key;
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "endless_wrath");
		   leggingItemMeta.getPersistentDataContainer().set(unforgetable_itemKey, PersistentDataType.STRING, "");
		   leggingItemMeta.setUnbreakable(true);
		   leggingItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		   trident.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(trident);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.attack_damage"));
			speed.set("Name",NBTTagString.a("generic.attack_damage"));
			speed.set("Amount",NBTTagDouble.a(9));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(444444));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("mainhand"));
			armor.set("AttributeName",NBTTagString.a("generic.attack_speed"));
			armor.set("Name",NBTTagString.a("generic.attack_speed"));
			armor.set("Amount",NBTTagDouble.a(0.9));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(555555));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("mainhand"));
			modList.add(speed);
			modList.add(armor);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			trident = CraftItemStack.asBukkitCopy(nmStack);
			trident.addUnsafeEnchantment(registerEnchantment.MANA_RESONANCE,1);
			return trident;
	   }
}
