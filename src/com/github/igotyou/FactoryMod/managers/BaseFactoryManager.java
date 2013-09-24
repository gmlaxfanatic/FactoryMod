/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.PrintingFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Superclass for specific Factory managers to extend
 */
public abstract class BaseFactoryManager implements FactoryManager {

	protected FactoryModPlugin plugin;
	protected String savesFilename;
	protected List<Factory> factories;
	protected Map<String,FactoryProperties> allFactoryProperties;
	protected int updatePeriod;
	//Initally generated set of possible materials and interaction
	//materials to speed up responses to interactions and construction.
	protected Set<Material> materials;
	protected Set<Material> interactionMaterials;
	//For efficiency in factory creation Each unique structure points to a creation point
	//used by that structure, which in turn points to the properites which use those offsets
	protected Map<Structure, Map<Offset, Set<FactoryProperties>>> structures = new HashMap<Structure, Map<Offset, Set<FactoryProperties>>>();

	public BaseFactoryManager(FactoryModPlugin plugin, ConfigurationSection configuration) {
		this.plugin = plugin;
		this.updatePeriod = configuration.getInt("update_period", 20);
		this.savesFilename = configuration.getString("save_filename","defaultSaves");
		factories = new ArrayList<Factory>();
		allFactoryProperties = new HashMap<String, FactoryProperties>();
		materials = new HashSet<Material>();
		interactionMaterials = new HashSet<Material>();
	}

	public int getUpdatePeriod() {
		return updatePeriod;
	}
	protected void updateManager() {
		updateMaterials();
		updateInteractionMaterials();
		updateStructures();
		updateFactorys();
	}

	public void updateFactorys() {
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Factory factory : factories) {
					factory.update();
				}
			}
		}, 0L, updatePeriod);
	}
	/*
	 * Finds a factory whose three dimensional binding box is contains the 
	 * given location. If no factory is found null is returned
	 */

	public Factory factoryAtLocation(Location factoryLocation) {
		for (Factory factory : factories) {
			if (factory.getAnchor().containedIn(factoryLocation, factory.getStructure().getDimensions())) {
				return factory;
			}
		}
		return null;
	}

	/*
	 * Removes all record of the factory from the manager
	 */
	public void removeFactory(Factory factory) {
		factories.remove(factory);
	}

	/*
	 * Add the given factory to the list of factories
	 */
	public void addFactory(Factory factory) {
		factories.add(factory);
	}

	/*
	 * Atempt to create a factory given the location as the creation point.
	 * Checks that a potential structure exists, if it does it calls a 
	 * subclass to attempt to create a factory, the subclass then checks for
	 * special subconditions for the factory creation
	 */
	public abstract InteractionResponse createFactory(FactoryProperties properties, Anchor anchor);

	public InteractionResponse createFactory(Location location) {
		InteractionResponse response = new InteractionResponse(InteractionResult.IGNORE, "Not a viable structure");
		FactoryModPlugin.debugMessage("BaseFactoryManager creating factory...");
		for (Structure structure : structures.keySet()) {
			Map<Offset, Set<FactoryProperties>> offsets = structures.get(structure);
			for (Offset offset : offsets.keySet()) {
				Set<Anchor> potentialAnchors = offset.getPotentialAnchors(location);
				FactoryModPlugin.debugMessage("Searching Potential anchors: " + potentialAnchors.size());
				for (Anchor potentialAnchor : potentialAnchors) {
					if (structure.exists(potentialAnchor)) {
						FactoryModPlugin.debugMessage("Found anchor, testing create conditions for various property files");
						for (FactoryProperties factoryProperties : offsets.get(offset)) {
							response = createFactory(factoryProperties, potentialAnchor);
							if (response != null && response.getInteractionResult() == InteractionResult.SUCCESS) {
								return response;
							}
						}
					}
				}
			}
		}
		return response;
	}

	/*
	 * Get the possible interactoin material types possible for this manager 
	 */
	public Set<Material> getInteractionMaterials() {
		return interactionMaterials;
	}

	/*
	 * Updates the possible inteaction materials for this manager
	 */
	protected void updateInteractionMaterials() {
		interactionMaterials.clear();
		for (FactoryProperties factoryProperties : allFactoryProperties.values()) {
			interactionMaterials.addAll(factoryProperties.getInteractionMaterials());
		}
	}

	/*
	 * Get all materials potentially a part of a factory
	 */
	public Set<Material> getMaterials() {
		return materials;
	}

	/*
	 * Updates materials potentially used by factories in this manager
	 */
	protected void updateMaterials() {
		materials.clear();
		for (FactoryProperties factoryProperties : allFactoryProperties.values()) {
			materials.addAll(factoryProperties.getStructure().getMaterials());
		}
	}

	protected void updateStructures() {
		for (FactoryProperties factoryProperties : allFactoryProperties.values()) {
			Structure structure = factoryProperties.getStructure();
			if (!structures.containsKey(structure)) {
				structures.put(structure, new HashMap<Offset, Set<FactoryProperties>>());
			}
			Map<Offset, Set<FactoryProperties>> offsets = structures.get(structure);
			if (!offsets.containsKey(factoryProperties.getCreationPoint())) {
				offsets.put(factoryProperties.getCreationPoint(), new HashSet<FactoryProperties>());
			}
			offsets.get(factoryProperties.getCreationPoint()).add(factoryProperties);
		}
	}
	/*
	 * Checks if a factory exists at the given location
	 */

	public boolean factoryExistsAt(Location location) {
		return factoryAtLocation(location) != null;
	}

	/*
	 * Checks if a whole factory exists at the given location
	 */
	public boolean factoryWholeAt(Location factoryLocation) {
		Factory Factory = factoryAtLocation(factoryLocation);
		return Factory != null ? Factory.isWhole() : false;
	}
	
	public FactoryProperties getProperties(String title) {
		return allFactoryProperties.get(title);
	}

	@Override
	public void update() {
		save();
	}
	
	

	/*
	 * Saves the manager
	 */
	public void onDisable() {
		save();
	}

	/*
	 * Saves the general properties of the factory
	 */
	public void save() {
		String filename = savesFilename + ".txt";
		try {
			save(new File(plugin.getDataFolder(), filename));
		} catch (IOException exception) {
			throw new RuntimeException("Failed to save to " + filename, exception);
		}
	}

	/*
	 * Saves the general properties of the factory
	 */
	protected void save(File file) throws IOException {
		try {
			File newFile = new File(file.getAbsolutePath() + ".new");
			File bakFile = new File(file.getAbsolutePath() + ".bak");

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			int version = 2;
			oos.writeInt(2);
			oos.writeInt(factories.size());
			for (Factory factory : factories) {
				oos.writeObject(factory);
			}
			oos.flush();
			fileOutputStream.close();

			if (bakFile.exists()) {
				bakFile.delete();
			}

			if (file.exists() && !file.renameTo(bakFile)) {
				throw new IOException("Failed to rename " + file.getAbsolutePath() + " to " + bakFile.getAbsolutePath());
			}

			if (!newFile.renameTo(file)) {
				throw new IOException("Failed to rename " + newFile.getAbsolutePath() + " to " + file.getAbsolutePath());
			}
		} catch (IOException exception) {
			throw new RuntimeException("Failed to save to " + file.getAbsolutePath(), exception);
		}



	}
	
	/*
	 * Loads the factorys
	 */
	
	public void load() {
		String filename = savesFilename + ".txt";
		load(new File(plugin.getDataFolder(), filename));
	}
	public void load(File file){
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			int version = ois.readInt();
			if(version==1) {
				load1(file);
			}
			else {
				assert(version == 2);
				int count = ois.readInt();
				int i = 0;
				for (i = 0; i < count; i++)
				{
					addFactory((Factory)ois.readObject());
				}

			}
			fileInputStream.close();
		}
		catch (FileNotFoundException exception) {
			FactoryModPlugin.sendConsoleMessage(file.getName() + " does not exist! Creating file!");
		} catch (IOException exception) {
			throw new RuntimeException("Failed to load " + file.getPath(), exception);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Hook for legacy file suppoert
	 */	
	protected abstract void load1(File file);
}
