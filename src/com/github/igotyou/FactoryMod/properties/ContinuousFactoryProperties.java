package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

public class ContinuousFactoryProperties extends BaseFactoryProperties {

	protected int energyTime;
	protected ItemList<NamedItemStack> inputs;
	protected ItemList<NamedItemStack> fuel;

	public ContinuousFactoryProperties(
		String factoryID,
		String name,
		Structure structure,
		Map<String, Offset> interactionPoints,
		ItemList<NamedItemStack> inputs,
		ItemList<NamedItemStack> fuel,
		int energyTime) {
		super(factoryID, structure, interactionPoints, name);
		this.inputs = inputs;
		this.fuel = fuel;
		this.energyTime = energyTime;
	}

	public int getEnergyTime() {
		return energyTime;
	}

	public ItemList<NamedItemStack> getFuel() {
		return fuel;
	}

	public Offset getInventory() {
		return interactionPoints.get("inventory");
	}

	public Offset getPowerIndicator() {
		return interactionPoints.get("power");
	}

	@Override
	public Offset getCreationPoint() {
		return interactionPoints.get("inventory");
	}

	/*
	 * Imports all of the continuous factory properties from a configuration section
	 */
	public static Map<String, FactoryProperties> continuousPropertiesFromConfig(ConfigurationSection configurationSection) {
		Map<String, FactoryProperties> continuousFactoryProperties = new HashMap<String, FactoryProperties>();
		for (String title : configurationSection.getKeys(false)) {
			continuousFactoryProperties.put(title, ContinuousFactoryProperties.fromConfig(title, configurationSection.getConfigurationSection(title)));
		}
		return continuousFactoryProperties;
	}

	/*
	 * Imports a single continuous factory properties from a configuration section
	 */
	protected static ContinuousFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		BaseFactoryProperties baseFactoryProperties = BaseFactoryProperties.fromConfig(factoryID, configurationSection);
		ItemList<NamedItemStack> fuel = ItemList.fromConfig(configurationSection.getConfigurationSection("fuel"));
		int energyTime = configurationSection.getInt("fuel_time", 2);
		ItemList<NamedItemStack> inputs = ItemList.fromConfig(configurationSection.getConfigurationSection("inputs"));
		return new ContinuousFactoryProperties(baseFactoryProperties.factoryID, baseFactoryProperties.name, baseFactoryProperties.structure, baseFactoryProperties.interactionPoints, inputs, fuel, energyTime);
	}
}
