/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.SimpleFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.SimpleFactoryProperties;
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

/**
 *
 * @author Brian
 */
public class SimpleFactoryManager extends BaseFactoryManager {

	private String savesFileName;

	public SimpleFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateManager();
	}
	/*
	 * Saves teh specifics of Production factories
	 */
	@Override
	protected void saveSpecifics(ObjectOutputStream oos, Factory baseFactory) {
		SimpleFactory simpleFactory = (SimpleFactory) baseFactory;
		try {	
			oos.writeUTF(simpleFactory.getFactoryType());
			oos.writeInt(simpleFactory.getProductionTime());
			oos.writeInt(simpleFactory.getEnergyTime());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Loads the specific attributes of a production factory
	 */
	@Override
	protected Factory loadSpecifics(ObjectInputStream ois, Location location, Orientation orientation) throws IOException {
		try {
			String factoryType = ois.readUTF();
			int productionTimer = ois.readInt();
			int energyTimer = ois.readInt();

			if (allFactoryProperties.containsKey(factoryType)) {
				return new SimpleFactory(
					new Anchor(orientation, location),
					(SimpleFactoryProperties) allFactoryProperties.get(factoryType),
					energyTimer,
					productionTimer);
			}
		}
		catch(IOException e) {
			throw e;
		}
		return null;
	}
	
	/*
	 * Unsupported Legacy load
	 */
	@Override
	public void load1(File file){
		
	}

	/*
	 * Initializes the configuration for simple factories
	 */
	public void initConfig(ConfigurationSection configurationSection) {
		//Import factory properties
		allFactoryProperties = SimpleFactoryProperties.simplePropertiesFromConfig(configurationSection.getConfigurationSection("factories"));

	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */

	@Override
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		SimpleFactoryProperties areaFactoryProperties = (SimpleFactoryProperties) properties;
		ItemList<NamedItemStack> fuel = areaFactoryProperties.getFuel();
		Offset creationPoint = areaFactoryProperties.getCreationPoint();
		Inventory inventory = ((InventoryHolder) anchor.getLocationOfOffset(creationPoint).getBlock().getState()).getInventory();
		if (fuel.allIn(inventory)) {
			fuel.removeFrom(inventory);
			SimpleFactory areaFactory = new SimpleFactory(anchor, (SimpleFactoryProperties) properties);
			addFactory(areaFactory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "Successfully created " + areaFactoryProperties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present");
		return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}

	public String getSavesFileName() {
		return savesFileName;
	}

	/*
	 * Returns simple factory properties given a factoryID
	 */
	public SimpleFactoryProperties getProperties(String factoryID) {
		return (SimpleFactoryProperties) allFactoryProperties.get(factoryID);
	}
}
