package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.AreaEffect.Area;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;

public class AreaFactoryProperties extends ContinuousFactoryProperties {

	Map<Integer, Set<Area.AreaEffect>> areaEffects;

	public AreaFactoryProperties(
		String factoryID,
		String name,
		Structure structure,
		Map<String, Offset> interactionPoints,
		ItemList<NamedItemStack> inputs,
		ItemList<NamedItemStack> fuel,
		int energyTime,
		Map<Integer, Set<Area.AreaEffect>> areaEffects)
		 {
		super(factoryID,
			name,
			structure,
			interactionPoints,
			inputs,
			fuel,
			energyTime);
		this.areaEffects=areaEffects;
	}

	/*
	 * Gets a set of all of the area effects
	 */
	public Set<Area.AreaEffect> getAllAreaEffects() {
		Set<Area.AreaEffect> allAreaEffects = new HashSet<Area.AreaEffect>();
		for (Set<Area.AreaEffect> areaEffect : areaEffects.values()) {
			allAreaEffects.addAll(areaEffect);
		}
		return allAreaEffects;
	}

	/*
	 * Imports a single Territorial factory properties from a configuration section
	 */
	protected static AreaFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		ContinuousFactoryProperties continuousFactoryProperties = ContinuousFactoryProperties.fromConfig(factoryID, configurationSection);
		Map<Integer, Set<Area.AreaEffect>> areaEffects = new HashMap<Integer, Set<Area.AreaEffect>>();
		ConfigurationSection areaEffectConfigurationSection = configurationSection.getConfigurationSection("area_effects");
		if(areaEffectConfigurationSection!=null) {
			for(String key:areaEffectConfigurationSection.getKeys(false)){
				
			}
		}
		return new com.github.igotyou.FactoryMod.properties.TerritoryFactoryProperties(continuousFactoryProperties.factoryID, continuousFactoryProperties.name, continuousFactoryProperties.structure, continuousFactoryProperties.interactionPoints, continuousFactoryProperties.inputs, continuousFactoryProperties.fuel, continuousFactoryProperties.energyTime, outputs, productionTime, territoryGroup);
	
}
}
