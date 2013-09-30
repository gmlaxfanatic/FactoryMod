package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.TerritorialFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.properties.ContinuousFactoryProperties;
import com.github.igotyou.FactoryMod.properties.TerritoryFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.voronoi.TerritoryMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class TerritorialFactoryManager extends ContinuousFactoryManager {

	Map<String, TerritoryMap> territoryMaps;
	Map<String, Set<Factory>> territorialGroups;

	public TerritorialFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		territoryMaps = new HashMap<String, TerritoryMap>();
		territorialGroups = new HashMap<String, Set<Factory>>();
		updateTerritoryCalculations();
	}

	/*
	 * Inititates the territory calculations
	 */
	public void initiateTerritoryCalculations() {
		for (Factory factory : factories) {
			String territorialGroup = getProperties(factory.getFactoryType()).getTerritorialGroup();
			if (territorialGroup != "") {
				if (!territorialGroups.containsKey(territorialGroup)) {
					territorialGroups.put(territorialGroup, new HashSet<Factory>());
				}
				territorialGroups.get(territorialGroup).add(factory);
			}
		}
		for (String territorialGroup : territorialGroups.keySet()) {
			territoryMaps.put(territorialGroup, new TerritoryMap(territorialGroups.get(territorialGroup)));
		}
	}

	/*
	 * Updates the territory calculations for a particular factory
	 */
	public void updateTerritoryCalculation(Factory factory, boolean add) {
		String territorialGroup = getProperties(factory.getFactoryType()).getTerritorialGroup();
		if (territorialGroup == "") {
			return;
		}

		if (!territorialGroups.containsKey(territorialGroup)) {
			territorialGroups.put(territorialGroup, new HashSet<Factory>());
		}
		if(add) {
		territorialGroups.get(territorialGroup).add(factory);
		}
		else {
			territorialGroups.remove(factory);
		}
		territoryMaps.put(territorialGroup, new TerritoryMap(territorialGroups.get(territorialGroup)));

	}

	public TerritoryFactoryProperties getProperties(String title) {
		return (TerritoryFactoryProperties) allFactoryProperties.get(title);
	}

	/*
	 * Gets an AreaEffect Factory
	 */
	public Factory getFactory(Anchor anchor, ContinuousFactoryProperties properties) {
		return new TerritorialFactory(anchor, properties.getName());
	}

	public void removeFactory(Factory factory) {
		this.removeFactory(factory);
		updateTerritoryCalculation(factory,false);
	}

	public InteractionResponse createFactory(Location location) {
		InteractionResponse interactionResponse = this.createFactory(location);
		updateTerritoryCalculation(factoryAtLocation(location),true);
		return interactionResponse;
	}
}
