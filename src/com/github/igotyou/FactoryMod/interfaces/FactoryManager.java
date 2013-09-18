package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.Factorys.ItemFactory;
import java.io.File;
import java.io.IOException;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import java.util.Set;
import org.bukkit.Material;

public interface FactoryManager
{

/**
* Saves the machine objects list of this manager to file
*/
	public void save(File file) throws IOException;

/**
* Loads machine objects list of this manager from file
*/
	public void load(File file) throws IOException;

/**
* Updates all the machines from this manager's machine object list
*/
	public void updateFactorys();

/**
* Attempts to create a new factory for this manager 
*/
	public InteractionResponse createFactory(Location location);

/**
* Returns the machine (if any exists) at the given location from this manager
*/
	public Factory factoryAtLocation(Location factoryLocation);

/**
* Returns whether a machine exists at the given location
*/
	public boolean factoryExistsAt(Location factoryLocation);
	
/**
* Returns whether a machine is whole at the given location
*/
	public boolean factoryWholeAt(Location factoryLocation);

/**
* Removes the given machine from the object list
*/
	public void removeFactory(Factory factory);

/**
* Returns the saves file name for this manager
*/
	public String getSavesFileName();
	
/**
 * Returns the properties of a particular factory
 */
	public FactoryProperties getProperties(String title);
/*
 * Get all materials potentially a part of a factory
 */
	public Set<Material> getMaterials();
/*
 * Returns a set of interaction materials associated with factories used in this manager
 */
	public Set<Material> getInteractionMaterials();
}