/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Superclass for specific BaseFactory managers to extend
 */

public abstract class BaseFactoryManager implements FactoryManager {
	
	protected FactoryModPlugin plugin;
	protected List<BaseFactory> baseFactories;
	protected Map<String,FactoryProperties> allFactoryProperties;
	protected long repairTime;
	//Initally generated set of possible materials and interaction
	//materials to speed up responses to interactions and construction.
	protected Set<Material> materials;
	protected Set<Material> interactionMaterials;
	//For efficiency in factory creation Each unique structure points to a creation point
	//used by that structure, which in turn points to the properites which use those offsets
	protected Map<Structure,Map<Offset,FactoryProperties>> structures=new HashMap<Structure,Map<Offset,FactoryProperties>>();

	public BaseFactoryManager(FactoryModPlugin plugin)
	{
		this.plugin=plugin;
		baseFactories = new ArrayList<BaseFactory>();
		interactionMaterials=new HashSet<Material>();
	}
	
	public void updateFactorys() 
	{
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
		{
			@Override
			public void run()
			{
				for (BaseFactory baseFactory:baseFactories)
				{
					baseFactory.update();
				}
			}
		}, 0L, FactoryModPlugin.UPDATE_CYCLE);
	}
	/*
	 * Finds a factory whose three dimensional binding box is contains the 
	 * given location. If no factory is found null is returned
	 */
	public Factory factoryAtLocation(Location factoryLocation) 
	{
		for (BaseFactory baseFactory : baseFactories)
		{			
			if(baseFactory.getAnchor().containedIn(factoryLocation, baseFactory.getStructure().getDimensions())) {
				return baseFactory;
			}
		}
		return null;
	}
	
	/*
	 * Removes all record of the factory from the manager
	 */
	public void removeFactory(Factory factory) 
	{
		baseFactories.remove(factory);
	}
	
	/*
	 * Add the given factory to the list of factories
	 */
	public void addFactory(BaseFactory factory) 
	{
		baseFactories.add(factory);
	}
	

	
	/*
	 * Atempt to create a factory given the location as the creation point.
	 * Checks that a potential structure exists, if it does it calls a 
	 * subclass to attempt to create a factory, the subclass then checks for
	 * special subconditions for the factory creation
	 */
	public abstract InteractionResponse createFactory(FactoryProperties properties, Anchor anchor);
	
	public InteractionResponse createFactory(Location location){
		InteractionResponse response = new InteractionResponse(InteractionResult.IGNORE,"Not a viable structure");
		for(Structure structure:structures.keySet()) {
			for(Entry<Offset,FactoryProperties> entry:structures.get(structure).entrySet()) {
				Set<Anchor> potentialAnchors = entry.getKey().getPotentialAnchors(location);
				for(Anchor potentialAnchor:potentialAnchors) {
					if(structure.exists(potentialAnchor)) {
						response = createFactory(entry.getValue(), potentialAnchor);
						if(response != null && response.getInteractionResult()==InteractionResult.SUCCESS) {
							return response;
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
		for(FactoryProperties factoryProperties:allFactoryProperties.values()) {
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
		for(FactoryProperties factoryProperties:allFactoryProperties.values()) {
			materials.addAll(factoryProperties.getStructure().getMaterials());
		}
	}
	/*
	 * Checks if a factory exists at the given location
	 */
	public boolean factoryExistsAt(Location location) {
		return factoryAtLocation(location)!=null;
	}
	
	/*
	 * Checks if a whole factory exists at the given location
	 */
	public boolean factoryWholeAt(Location factoryLocation) 
	{
		Factory Factory = factoryAtLocation(factoryLocation);
		return Factory != null ? Factory.isWhole() : false;
	}
}
