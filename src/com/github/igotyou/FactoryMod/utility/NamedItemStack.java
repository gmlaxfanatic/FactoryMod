/*
 * A simple Object to connect a name to display to the player with an ItemStack.
 * This is required since the actual name displayed is taken care of client side
 * so different languages can be used and therefor it is unavailable to the
 * plugin. Bukkit does not provide a reliable naming
 * structure and does not take into account different names for different damage
 * values of items. This could instead be stored in the display name field, but
 * then the display name field would have to be ignored on item comparisons and
 * couldn't be used to designate unique items. So this is needed :(
 */
package com.github.igotyou.FactoryMod.utility;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Brian Landry
 */
public class NamedItemStack extends ItemStack {
	//Name to be printed to the user, not necessarily the DisplayName

	private final String commonName;

	public NamedItemStack(final Material type, final int amount, final short damage, final String commonName) {
		super(type, amount, damage);
		this.commonName = commonName;
	}

	public NamedItemStack clone() {
		try {
			NamedItemStack namedItemStack = (NamedItemStack) super.clone();
			return namedItemStack;
		} catch (Error e) {
			throw e;
		}
	}

	public String toString() {
		return String.valueOf(getAmount()) + " " + commonName;
	}

	public String getCommonName() {
		return commonName;
	}

	public static NamedItemStack fromConfig(String commonName, ConfigurationSection configItem) {
		String materialName = configItem.getString("material");
		Material material = Material.getMaterial(materialName);
		//only proceeds if an acceptable material name was provided
		if (material != null) {
			int amount = configItem.getInt("amount", 1);
			short durability = (short) configItem.getInt("durability", 0);
			String displayName = configItem.getString("display_name");
			String lore = configItem.getString("lore");
			NamedItemStack namedItemStack = new NamedItemStack(material, amount, durability, commonName);
			String metaType = configItem.getString("meta_type", "ItemMeta");
			ItemMeta itemMeta;
			switch (metaType) {
				case "PotionEffectMeta":
					itemMeta = potionEffectFromConfig(configItem.getConfigurationSection(metaType));
				case "ItemMeta":
					itemMeta = namedItemStack.getItemMeta();
				default:
					itemMeta = null;
			}
			if (itemMeta != null) {
				if (displayName != null) {
					itemMeta.setDisplayName(displayName);
				}
				if (lore != null) {
					List<String> loreArray = new ArrayList<String>();
					loreArray.add(lore);
					itemMeta.setLore(loreArray);
				}

				if (displayName != null || lore != null || metaType != "ItemMeta") {
					namedItemStack.setItemMeta(itemMeta);
				}
			}
			return namedItemStack;
		}
		return null;
		
	}

	public static PotionMeta potionEffectFromConfig(ConfigurationSection configurationSection) {
		if (configurationSection == null) {
			return null;
		}
		PotionMeta potionMeta = (PotionMeta) Bukkit.getItemFactory().getItemMeta(Material.POTION);
		for (String key : configurationSection.getKeys(false)) {
			if (key == "lore" || key == "displayName") {
				continue;
			}
			ConfigurationSection effectConfiguration = configurationSection.getConfigurationSection(key);
			PotionEffectType effectType = PotionEffectType.getByName(effectConfiguration.getString("PotionEffect1"));
			if (effectType == null) {
				continue;
			}
			int duration = effectConfiguration.getInt("duration", 60);
			int amplifier = effectConfiguration.getInt("amplifier", 1);
			boolean ambient = effectConfiguration.getBoolean("ambient", false);
			potionMeta.addCustomEffect(new PotionEffect(effectType, duration, amplifier, ambient), false);
		}
		return potionMeta;
	}
}
