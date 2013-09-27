package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ContinousFactoryProperties extends BaseFactoryProperties {

	Map<Integer, Set<AreaEffect>> areaEffects;
	protected int energyTime;
	protected int productionTime;
	protected ItemList<NamedItemStack> fuel;
	protected ItemList<NamedItemStack> outputs;

	public ContinousFactoryProperties(
		String factoryID,
		String name, 
		Structure structure, 
		List<Offset> interactionPoints, 
		ItemList<NamedItemStack> inputs, 
		ItemList<NamedItemStack> fuel,
		ItemList<NamedItemStack> outputs, 
		int energyTime, 
		int productionTime) {
		super(factoryID, structure, interactionPoints, name);
		this.fuel = fuel;
		this.outputs = outputs;
		this.energyTime = energyTime;
		this.productionTime = productionTime;
	}

	public int getEnergyTime() {
		return energyTime;
	}

	public int getProductionTime() {
		return productionTime;
	}

	public ItemList<NamedItemStack> getFuel() {
		return fuel;
	}
	
	public ItemList<NamedItemStack> getOutputs() {
		return outputs;
	}

	public Offset getInventory() {
		return interactionPoints.get(0);
	}
	
	public Offset getPowerIndicator() {
		return interactionPoints.get(1);
	}

	public Offset getCreationPoint() {
		return interactionPoints.get(0);
	}

	public Map<Integer, Set<AreaEffect>> getAreaEffects() {
		return areaEffects;
	}

	/*
	 * Gets a set of all of the area effects
	 */
	public Set<AreaEffect> getAllAreaEffects() {
		Set<AreaEffect> allAreaEffects = new HashSet<AreaEffect>();
		for (Set<AreaEffect> areaEffect : areaEffects.values()) {
			allAreaEffects.addAll(areaEffect);
		}
		return allAreaEffects;
	}
	
	/*
	 * Imports all of the continous factory properties from a configuration section
	 */
	public static Map<String, FactoryProperties> continousPropertiesFromConfig(ConfigurationSection configurationSection) {
		Map<String, FactoryProperties> continousFactoryProperties=new HashMap<String, FactoryProperties>();
		for(String title:configurationSection.getKeys(false))
		{
			continousFactoryProperties.put(title, ContinousFactoryProperties.fromConfig(title, configurationSection.getConfigurationSection(title)));
		}
		return continousFactoryProperties;
	}
	
	/*
	 * Imports a single continous factory properties from a configuration section
	 */
	protected static ContinousFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		factoryID=factoryID.replaceAll(" ","_");
		String factoryName=configurationSection.getString("name","Default Name");
		//Uses overpowered getItems method for consistency, should always return a list of size=1
		//If no fuel is found, default to charcoal
		ItemList<NamedItemStack> fuel=ItemList.fromConfig(configurationSection.getConfigurationSection("fuel"));
		int energyTime=configurationSection.getInt("fuel_time",2);
		ItemList<NamedItemStack> inputs=ItemList.fromConfig(configurationSection.getConfigurationSection("inputs"));
		ItemList<NamedItemStack> outputs=ItemList.fromConfig(configurationSection.getConfigurationSection("outputs"));
		int productionTime=configurationSection.getInt("productionTime",2);
		Structure structure = FactoryModPlugin.getManager().getStructureManager().getStructure(configurationSection.getString("structure","ItemFactory"));
		
		ConfigurationSection interactionPointsConfiguration = configurationSection.getConfigurationSection("interaction_points");
		List<Offset> interactionPoints=new ArrayList<Offset>(2);
		interactionPoints.set(0, new Offset(0,0,0));
		interactionPoints.set(1, new Offset(1,0,0));
		if(interactionPointsConfiguration!=null) {
			if(interactionPointsConfiguration.contains("inventory")) {
				interactionPoints.set(0, Offset.fromConfig(interactionPointsConfiguration.getConfigurationSection("inventory")));
			}
			if(interactionPointsConfiguration.contains("power_indicator")) {
				interactionPoints.set(1, Offset.fromConfig(interactionPointsConfiguration.getConfigurationSection("power_indicator")));
			}
		}
		
		return new ContinousFactoryProperties(factoryID, factoryName, structure, interactionPoints,inputs, outputs, fuel, energyTime, productionTime);
		
		
	}
}
