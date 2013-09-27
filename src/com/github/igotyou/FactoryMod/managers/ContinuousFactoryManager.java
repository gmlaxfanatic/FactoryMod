
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.AreaEffect.PotionArea;
import com.github.igotyou.FactoryMod.AreaEffect.ChatArea;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ContinuousFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.ContinuousFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class ContinuousFactoryManager extends BaseFactoryManager {


	protected int territoryUpdatePeriod;
	

	public ContinuousFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateManager();
	}
	/*
	 * Initializes the configuration for continuous factories
	 */
	public void initConfig(ConfigurationSection configurationSection) {
		//Import factory properties
		allFactoryProperties = ContinuousFactoryProperties.continuousPropertiesFromConfig(configurationSection.getConfigurationSection("factories"));

	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */
	public abstract Factory getFactory(Anchor anchor, ContinuousFactoryProperties properties);
	@Override
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		ContinuousFactoryProperties continuousFactoryProperties = (ContinuousFactoryProperties) properties;
		ItemList<NamedItemStack> fuel = continuousFactoryProperties.getFuel();
		Offset creationPoint = continuousFactoryProperties.getCreationPoint();
		Inventory inventory = ((InventoryHolder) anchor.getLocationOfOffset(creationPoint).getBlock().getState()).getInventory();
		if (fuel.allIn(inventory)) {
			fuel.removeFrom(inventory);
			Factory continuousFactory = getFactory(anchor, continuousFactoryProperties);
			addFactory(continuousFactory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "Successfully created " + continuousFactoryProperties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present");
		return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}
}
