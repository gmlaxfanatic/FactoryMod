package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.AreaEffect.ChatArea;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.TerritorialFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.properties.ContinuousFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import org.bukkit.configuration.ConfigurationSection;

public class TerritorialFactoryManager extends ContinuousFactoryManager {

	public TerritorialFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		updateTerritoryCalculations();
	}

	/*
	 * Updates the territory calculation
	 */
	public void updateTerritoryCalculations() {
	}

	/*
	 * Gets an AreaEffect Factory
	 */
	public Factory getFactory(Anchor anchor, ContinuousFactoryProperties properties) {
		return new TerritorialFactory(anchor, properties.getName());
	}
}
