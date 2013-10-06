package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.TerritoryFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.properties.ContinuousFactoryProperties;
import com.github.igotyou.FactoryMod.properties.TerritoryFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.voronoi.TerritoryMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class TerritoryFactoryManager extends ContinuousFactoryManager {

	Map<String, TerritoryMap> territoryMaps;
	Map<String, Set<Factory>> territoryGroups;

	public TerritoryFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		territoryMaps = new HashMap<String, TerritoryMap>();
		territoryGroups = new HashMap<String, Set<Factory>>();
		initiateTerritoryCalculations();
	}

	/*
	 * Inititates the territory calculations
	 */
	private void initiateTerritoryCalculations() {
		//Seperates the factories into their respective territory groups
		for (Factory factory : factories) {
			String territoryGroup = getProperties(factory.getFactoryType()).getTerritoryGroup();
			if (!territoryGroup.equals("")) {
				if (!territoryGroups.containsKey(territoryGroup)) {
					territoryGroups.put(territoryGroup, new HashSet<Factory>());
				}
				territoryGroups.get(territoryGroup).add(factory);
			}
		}
		for (String territoryGroup : territoryGroups.keySet()) {
			if (!territoryGroup.equals("")) {
				territoryMaps.put(territoryGroup, new TerritoryMap(territoryGroups.get(territoryGroup)));
			}
		}
	}

	/*
	 * Updates the territory calculations for a particular factory
	 */
	public void updateTerritoryCalculation(Factory factory, boolean add) {
		String territoryGroup = getProperties(factory.getFactoryType()).getTerritoryGroup();
		//Escapes if the default territoryGroup is used.
		if (territoryGroup.equals("")) {
			return;
		}

		//Adds or removes the factory from the given territory group
		if (add) {
			if (!territoryGroups.containsKey(territoryGroup)) {
				territoryGroups.put(territoryGroup, new HashSet<Factory>());
			}
			territoryGroups.get(territoryGroup).add(factory);
		} else {
			territoryGroups.get(territoryGroup).remove(factory);
		}
		//recalculates that factories territoryGroup
		territoryMaps.put(territoryGroup, new TerritoryMap(territoryGroups.get(territoryGroup)));
	}

	@Override
	public TerritoryFactoryProperties getProperties(String title) {
		return (TerritoryFactoryProperties) allFactoryProperties.get(title);
	}

	/*
	 * Gets an AreaEffect Factory
	 */
	@Override
	public Factory getFactory(Anchor anchor, ContinuousFactoryProperties properties) {
		return new TerritoryFactory(anchor, properties.getName());
	}

	@Override
	public void removeFactory(Factory factory) {
		this.removeFactory(factory);
		updateTerritoryCalculation(factory, false);
	}

	@Override
	public InteractionResponse createFactory(Location location) {
		InteractionResponse interactionResponse = super.createFactory(location);
		if (interactionResponse.getInteractionResult() == InteractionResult.SUCCESS) {
			updateTerritoryCalculation(factoryAtLocation(location), true);
		}
		return interactionResponse;
	}
}
