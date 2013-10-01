package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class RecipeFactoryProperties extends BaseFactoryProperties {

	protected ItemList<NamedItemStack> fuel;
	protected int repair;
	protected int energyTime;

	public RecipeFactoryProperties(String factoryID, String name, Structure structure, Map<String, Offset> interactionPoints, ItemList<NamedItemStack> fuel, int repair, int energyTime) {
		super(factoryID, structure, interactionPoints, name);

		this.fuel = fuel;
		this.repair = repair;
		this.energyTime = energyTime;
	}
	
	public ItemList<NamedItemStack> getFuel() {
		return fuel;
	}

	public int getRepair() {
		return repair;
	}
	
	public int getEnergyTime() {
		return energyTime;
	}

	public Offset getCreationPoint() {
		return interactionPoints.get("center");
	}

	public Offset getInventoryOffset() {
		return interactionPoints.get("inventory");
	}

	public Offset getCenterOffset() {
		return interactionPoints.get("center");
	}

	public Offset getPowerSourceOffset() {
		return interactionPoints.get("power_source");
	}

	protected static RecipeFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		BaseFactoryProperties baseFactoryProperties = BaseFactoryProperties.fromConfig(factoryID, configurationSection);
		ItemList<NamedItemStack> fuel = ItemList.fromConfig(configurationSection.getConfigurationSection("fuel"));
		if (fuel.isEmpty()) {
			fuel = new ItemList<NamedItemStack>();
			fuel.add(new NamedItemStack(Material.getMaterial("COAL"), 1, (short) 1, "Charcoal"));
		}
		int energyTime = configurationSection.getInt("fuel_time", 2);
		int repair = configurationSection.getInt("repair_multiple", 0);
		return new RecipeFactoryProperties(baseFactoryProperties.factoryID, baseFactoryProperties.name, baseFactoryProperties.structure, baseFactoryProperties.interactionPoints, fuel, repair, energyTime);
	}
}