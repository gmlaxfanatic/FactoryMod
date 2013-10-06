package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

public class TerritoryFactoryProperties extends ContinuousFactoryProperties {

	protected ItemList<NamedItemStack> outputs;
	protected int productionTime;
	protected String territoryGroup;

	public TerritoryFactoryProperties(
		String factoryID,
		String name,
		Structure structure,
		Map<String,Offset> interactionPoints,
		ItemList<NamedItemStack> inputs,
		ItemList<NamedItemStack> fuel,
		int energyTime,
		ItemList<NamedItemStack> outputs,
		int productionTime,
		String territoryGroup) {
		super(factoryID,
			name,
			structure,
			interactionPoints,
			inputs,
			fuel,
			energyTime);
		this.outputs = outputs;
		this.productionTime = productionTime;
		this.territoryGroup=territoryGroup;
	}

	public int getProductionTime() {
		return productionTime;
	}

	public ItemList<NamedItemStack> getOutputs() {
		return outputs;
	}
	public String getTerritoryGroup() {
		return territoryGroup;
	}
	
	/*
	 * Imports a single Territorial factory properties from a configuration section
	 */
	protected static TerritoryFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		ContinuousFactoryProperties continuousFactoryProperties = ContinuousFactoryProperties.fromConfig(factoryID,configurationSection);
		ItemList<NamedItemStack> outputs=ItemList.fromConfig(configurationSection.getConfigurationSection("outputs"));
		int productionTime=configurationSection.getInt("production_time",2);
		String territoryGroup=configurationSection.getString("territory_group", "");
		return new TerritoryFactoryProperties(continuousFactoryProperties.factoryID, continuousFactoryProperties.name, continuousFactoryProperties.structure, continuousFactoryProperties.interactionPoints,continuousFactoryProperties.inputs, continuousFactoryProperties.fuel, continuousFactoryProperties.energyTime,outputs,productionTime,territoryGroup);	
	}
}
