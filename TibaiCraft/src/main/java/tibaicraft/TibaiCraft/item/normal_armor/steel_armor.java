package tibaicraft.TibaiCraft.item.normal_armor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagDouble;
import net.minecraft.server.v1_16_R3.NBTTagInt;
import net.minecraft.server.v1_16_R3.NBTTagList;
import net.minecraft.server.v1_16_R3.NBTTagString;
import tibaicraft.TibaiCraft.main;

public class steel_armor implements Listener {
	private ItemStack steel_helmet;
	private ItemStack steel_chestplate;
	private ItemStack steel_legging;
	private ItemStack steel_boots;
	private ItemStack steel;
	private HashMap<ShapedRecipe, ItemStack>Recipe = new HashMap<ShapedRecipe, ItemStack>();

	private main plugin;
	private NamespacedKey key;
	public steel_armor(main main) {
		plugin = main;
		key = plugin.unforgetable_itemKey;
		steel_boots = steel_boots();
		steel_chestplate=steel_chestplate();
		steel_helmet = steel_helmet();
		steel_legging = steel_legging();
		steel = steel();
	}
	
	@EventHandler
	public void Oncrafting(PrepareItemCraftEvent e)
	{
		if(Recipe.keySet().contains(e.getRecipe()))
		{
			List<ItemStack> mat = Arrays.asList(e.getInventory().getMatrix());
			if(mat.containsAll(Collections.singletonList(steel)))
			{
				e.getInventory().setResult(Recipe.get(e.getRecipe()));
				return;
			}
		}
	}
	private ItemStack steel()
	{
		ItemStack steelItemStack = new ItemStack(Material.IRON_INGOT);
		ItemMeta itemMeta = steelItemStack.getItemMeta();
		itemMeta.setDisplayName("Steel");
		itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING,"steel");
		steelItemStack.setItemMeta(itemMeta);
		return steelItemStack;
	}
	
	private ItemStack steel_helmet()
	{
		 	ItemStack legging = new ItemStack(Material.IRON_HELMET);
		   ItemMeta leggingItemMeta = legging.getItemMeta();
		   leggingItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");
		   leggingItemMeta.setDisplayName("Steel Helmet");
		   legging.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(legging);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   NBTTagCompound armor_thoughmess = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.01));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(10876));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("head"));
			armor.set("AttributeName",NBTTagString.a("generic.armor"));
			armor.set("Name",NBTTagString.a("generic.armor"));
			armor.set("Amount",NBTTagDouble.a(2));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(597856));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("head"));
			armor_thoughmess.set("AttributeName",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Name",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Amount",NBTTagDouble.a(0.5));
			armor_thoughmess.set("Operation",NBTTagInt.a(0));
			armor_thoughmess.set("UUIDLeast",NBTTagInt.a(734905));
			armor_thoughmess.set("UUIDMost",NBTTagInt.a(2872));
			armor_thoughmess.set("Slot", NBTTagString.a("head"));
			modList.add(speed);
			modList.add(armor);
			modList.add(armor_thoughmess);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			legging = CraftItemStack.asBukkitCopy(nmStack);
			return legging;
	}
	private ItemStack steel_chestplate()
	{
		 	ItemStack legging = new ItemStack(Material.IRON_CHESTPLATE);
		   ItemMeta leggingItemMeta = legging.getItemMeta();
		   leggingItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");
		   leggingItemMeta.setDisplayName("Steel Chestplate");
		   legging.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(legging);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   NBTTagCompound armor_thoughmess = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.01));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(67891));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("chest"));
			armor.set("AttributeName",NBTTagString.a("generic.armor"));
			armor.set("Name",NBTTagString.a("generic.armor"));
			armor.set("Amount",NBTTagDouble.a(6));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(16342));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("chest"));
			armor_thoughmess.set("AttributeName",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Name",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Amount",NBTTagDouble.a(1));
			armor_thoughmess.set("Operation",NBTTagInt.a(0));
			armor_thoughmess.set("UUIDLeast",NBTTagInt.a(290870));
			armor_thoughmess.set("UUIDMost",NBTTagInt.a(2872));
			armor_thoughmess.set("Slot", NBTTagString.a("chest"));
			modList.add(speed);
			modList.add(armor);
			modList.add(armor_thoughmess);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			legging = CraftItemStack.asBukkitCopy(nmStack);
			return legging;
	}
	private ItemStack steel_legging()
	{
		 	ItemStack legging = new ItemStack(Material.IRON_LEGGINGS);
		   ItemMeta leggingItemMeta = legging.getItemMeta();
		   leggingItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");
		   leggingItemMeta.setDisplayName("Steel Legging");
		   legging.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(legging);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   NBTTagCompound armor_thoughmess = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.01));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(12345));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("legs"));
			armor.set("AttributeName",NBTTagString.a("generic.armor"));
			armor.set("Name",NBTTagString.a("generic.armor"));
			armor.set("Amount",NBTTagDouble.a(6));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(13456));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("legs"));
			armor_thoughmess.set("AttributeName",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Name",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Amount",NBTTagDouble.a(1));
			armor_thoughmess.set("Operation",NBTTagInt.a(0));
			armor_thoughmess.set("UUIDLeast",NBTTagInt.a(256789));
			armor_thoughmess.set("UUIDMost",NBTTagInt.a(2872));
			armor_thoughmess.set("Slot", NBTTagString.a("legs"));
			modList.add(speed);
			modList.add(armor);
			modList.add(armor_thoughmess);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			legging = CraftItemStack.asBukkitCopy(nmStack);
			return legging;
	}	
	private ItemStack steel_boots()
	{
		 	ItemStack legging = new ItemStack(Material.IRON_BOOTS);
		   ItemMeta leggingItemMeta = legging.getItemMeta();
		   leggingItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		   leggingItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");
		   leggingItemMeta.setDisplayName("Steel Boots");
		   legging.setItemMeta(leggingItemMeta);
		   net.minecraft.server.v1_16_R3.ItemStack nmStack = CraftItemStack.asNMSCopy(legging);
		   NBTTagCompound compound = (nmStack.hasTag()) ? nmStack.getTag() : new NBTTagCompound();
		   NBTTagList modList = new NBTTagList();
		   NBTTagCompound speed = new NBTTagCompound();
		   NBTTagCompound armor = new NBTTagCompound();
		   NBTTagCompound armor_thoughmess = new NBTTagCompound();
		   speed.set("AttributeName",NBTTagString.a("generic.movement_speed"));
			speed.set("Name",NBTTagString.a("generic.movement_speed"));
			speed.set("Amount",NBTTagDouble.a(0.01));
			speed.set("Operation",NBTTagInt.a(0));
			speed.set("UUIDLeast",NBTTagInt.a(94654));
			speed.set("UUIDMost",NBTTagInt.a(2872));
			speed.set("Slot", NBTTagString.a("feet"));
			armor.set("AttributeName",NBTTagString.a("generic.armor"));
			armor.set("Name",NBTTagString.a("generic.armor"));
			armor.set("Amount",NBTTagDouble.a(2));
			armor.set("Operation",NBTTagInt.a(0));
			armor.set("UUIDLeast",NBTTagInt.a(89465));
			armor.set("UUIDMost",NBTTagInt.a(2872));
			armor.set("Slot", NBTTagString.a("feet"));
			armor_thoughmess.set("AttributeName",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Name",NBTTagString.a("generic.armor_toughness"));
			armor_thoughmess.set("Amount",NBTTagDouble.a(0.5));
			armor_thoughmess.set("Operation",NBTTagInt.a(0));
			armor_thoughmess.set("UUIDLeast",NBTTagInt.a(89654));
			armor_thoughmess.set("UUIDMost",NBTTagInt.a(2872));
			armor_thoughmess.set("Slot", NBTTagString.a("feet"));
			modList.add(speed);
			modList.add(armor);
			modList.add(armor_thoughmess);
			compound.set("AttributeModifiers", modList);
			nmStack.setTag(compound);
			legging = CraftItemStack.asBukkitCopy(nmStack);
			return legging;
	}

	public ShapedRecipe steelRecipe()
	{ 
	ShapedRecipe recipe = new ShapedRecipe(key, steel);
	  recipe.shape("EC ","   ","   ");
	  recipe.setIngredient('E', Material.IRON_INGOT);
	  recipe.setIngredient('C', Material.COAL);
	  return recipe;
	}
	public ShapedRecipe SH() {
		 ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "a"), steel_helmet);
		  recipe.shape("EEE","E E","   ");
		  recipe.setIngredient('E', steel.getType());
		  Recipe.put(recipe,steel_helmet);
		  return recipe;
	}
	public ShapedRecipe SC() {
		 ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "b"), steel_chestplate);
		  recipe.shape("E E","EEE","EEE");
		  recipe.setIngredient('E', steel.getType());
		  Recipe.put(recipe,steel_chestplate);
		  return recipe;
	}
	public ShapedRecipe SL() {
		 ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "c"),steel_legging);
		  recipe.shape("EEE","E E","E E");
		  recipe.setIngredient('E', steel.getType());
		  Recipe.put(recipe,steel_legging);
		  return recipe;
	}
	public ShapedRecipe SB() {
		 ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "d"),steel_boots);
		  recipe.shape("   ","E E","E E");
		  recipe.setIngredient('E', steel.getType());
		  Recipe.put(recipe,steel_boots);
		  return recipe;
	}
}
