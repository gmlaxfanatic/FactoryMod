package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.TerritorialFactory;
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

public class TerritorialFactoryManager extends ContinuousFactoryManager {

	Map<String, TerritoryMap> territoryMaps;
	Map<String, Set<Factory>> territorialGroups;

	public TerritorialFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		territoryMaps = new HashMap<String, TerritoryMap>();
		territorialGroups = new HashMap<String, Set<Factory>>();
		initiateTerritoryCalculations();
	}

	/*
	 * Inititates the territory calculations
	 */
	private void initiateTerritoryCalculations() {
		//Seperates the factories into their respective territorial groups
		for (Factory factory : factories) {
			String territorialGroup = getProperties(factory.getFactoryType()).getTerritorialGroup();
			if (!territorialGroup.equals("")) {
				if (!territorialGroups.containsKey(territorialGroup)) {
					territorialGroups.put(territorialGroup, new HashSet<Factory>());
				}
				territorialGroups.get(territorialGroup).add(factory);
			}
		}
		for (String territorialGroup : territorialGroups.keySet()) {
			if (!territorialGroup.equals("")) {
				territoryMaps.put(territorialGroup, new TerritoryMap(territorialGroups.get(territorialGroup)));
			}
		}
	}

	/*
	 * Updates the territory calculations for a particular factory
	 */
	public void updateTerritoryCalculation(Factory factory, boolean add) {
		String territorialGroup = getProperties(factory.getFactoryType()).getTerritorialGroup();
		//Escapes if the default territorialGroup is used.
		if (territorialGroup.equals("")) {
			return;
		}

		//Adds or removes the factory from the given territorial group
		if (add) {
			if (!territorialGroups.containsKey(territorialGroup)) {
				territorialGroups.put(territorialGroup, new HashSet<Factory>());
			}
			territorialGroups.get(territorialGroup).add(factory);
		} else {
			territorialGroups.get(territorialGroup).remove(factory);
		}
		//recalculates that factories territorialGroup
		territoryMaps.put(territorialGroup, new TerritoryMap(territorialGroups.get(territorialGroup)));
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
		return new TerritorialFactory(anchor, properties.getName());
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
