/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.SimpleFactory;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.SimpleFactoryProperties;
import com.github.igotyou.FactoryMod.properties.ProductionFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 *
 * @author Brian
 */
public class SimpleFactoryManager extends BaseFactoryManager{
	
	private String savesFileName;
	
	public SimpleFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateManager();
	}
	
	/*
	 * Saves the current factories
	 */
	@Override
	public void save(File file) throws IOException 
	{
		//Takes difference between last repair update and current one and scales repair accordingly
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		int version = 2;
		oos.writeInt(version);
		oos.writeInt(factories.size());
		for (Factory factory : factories)
		{
			SimpleFactory simpleFactory=(SimpleFactory) factory;
		
			oos.writeUTF(simpleFactory.getAnchor().location.getWorld().getName());
						
			oos.writeInt(simpleFactory.getAnchor().location.getBlockX());
			oos.writeInt(simpleFactory.getAnchor().location.getBlockY());
			oos.writeInt(simpleFactory.getAnchor().location.getBlockZ());
			
			oos.writeInt(simpleFactory.getAnchor().orientation.id);
			
			oos.writeUTF(simpleFactory.getFactoryType());
			
			oos.writeInt(simpleFactory.getProductionTime());
			oos.writeInt(simpleFactory.getEnergyTime());
		}
		oos.flush();
		fileOutputStream.close();
	}	
	
	/*
	 * Loads the saved factories
	 */
	@Override
	public void load(File file) throws IOException 
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			int version = ois.readInt();
			assert(version == 2);
			int count = ois.readInt();
			int i = 0;
			for (i = 0; i < count; i++)
			{
				String worldName = ois.readUTF();
				World world = plugin.getServer().getWorld(worldName);
				Location location = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Anchor.Orientation orientation = Anchor.Orientation.getOrientation(ois.readInt());
				String factoryType = ois.readUTF();
				int productionTimer = ois.readInt();
				int energyTimer = ois.readInt();
				
				if(allFactoryProperties.containsKey(factoryType))
				{
					SimpleFactory simpleFactory = new SimpleFactory(
						new Anchor(orientation,location),
						(SimpleFactoryProperties) allFactoryProperties.get(factoryType),
						energyTimer,
						productionTimer);
					addFactory(simpleFactory);
				}
			}
			fileInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Initializes the configuration for simple factories
	 */
	public void initConfig(ConfigurationSection configurationSection) {
		//Import factory properties
		allFactoryProperties=SimpleFactoryProperties.simplePropertiesFromConfig(configurationSection.getConfigurationSection("factories"));
	
	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */
	@Override
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		SimpleFactoryProperties areaFactoryProperties = (SimpleFactoryProperties)properties;
		ItemList<NamedItemStack> fuel =  areaFactoryProperties.getFuel();
		Offset creationPoint = areaFactoryProperties.getCreationPoint();
		Inventory inventory = ((InventoryHolder)anchor.getLocationOfOffset(creationPoint).getBlock().getState()).getInventory();
		if(fuel.allIn(inventory))
		{
			fuel.removeFrom(inventory);
			SimpleFactory areaFactory = new SimpleFactory(anchor, (SimpleFactoryProperties) properties);
			addFactory(areaFactory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "Successfully created " + areaFactoryProperties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present");
		return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}
	
	
	public String getSavesFileName() 
	{
		return savesFileName;
	}
	
	/*
	 * Returns simple factory properties given a factoryID
	 */
	public SimpleFactoryProperties getProperties(String factoryID) {
		return (SimpleFactoryProperties) allFactoryProperties.get(factoryID);
	}
}
