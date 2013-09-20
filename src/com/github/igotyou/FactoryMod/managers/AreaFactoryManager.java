/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.AreaFactoryProperties;
import com.github.igotyou.FactoryMod.properties.ProductionFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 *
 * @author Brian
 */
public class AreaFactoryManager extends BaseFactoryManager{
	public AreaFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateManager();
	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		AreaFactoryProperties areaFactoryProperties = (AreaFactoryProperties)properties;
		ItemList<NamedItemStack> fuel =  areaFactoryProperties.getFuel();
		Offset creationPoint = areaFactoryProperties.getCreationPoint();
		Inventory inventory = ((InventoryHolder)anchor.getLocationOfOffset(creationPoint).getBlock().getState()).getInventory();
		if(fuel.allIn(inventory))
		{
			fuel.removeFrom(inventory);
			AreaFactory areaFactory = new AreaFactory(anchor, (AreaFactoryProperties) properties);
			addFactory(areaFactory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "Successfully created " + areaFactoryProperties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present");
		return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}
	
	public void initConfig(ConfigurationSection configurationSection) {
		
	}
	
}
