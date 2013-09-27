
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.AreaEffect.PotionArea;
import com.github.igotyou.FactoryMod.AreaEffect.ChatArea;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ContinousFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.ContinousFactoryProperties;
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

public abstract class ContinousFactoryManager extends BaseFactoryManager {


	protected int territoryUpdatePeriod;
	

	public ContinousFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateManager();
	}
	/*
	 * Initializes the configuration for continous factories
	 */
	public void initConfig(ConfigurationSection configurationSection) {
		//Import factory properties
		allFactoryProperties = ContinousFactoryProperties.continousPropertiesFromConfig(configurationSection.getConfigurationSection("factories"));

	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */
	public abstract Factory getFactory(Anchor anchor, ContinousFactoryProperties properties);
	@Override
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		ContinousFactoryProperties continousFactoryProperties = (ContinousFactoryProperties) properties;
		ItemList<NamedItemStack> fuel = continousFactoryProperties.getFuel();
		Offset creationPoint = continousFactoryProperties.getCreationPoint();
		Inventory inventory = ((InventoryHolder) anchor.getLocationOfOffset(creationPoint).getBlock().getState()).getInventory();
		if (fuel.allIn(inventory)) {
			fuel.removeFrom(inventory);
			Factory continousFactory = getFactory(anchor, continousFactoryProperties);
			addFactory(continousFactory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "Successfully created " + continousFactoryProperties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present");
		return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}
}
