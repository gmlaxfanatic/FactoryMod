/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ItemFactory;
import com.github.igotyou.FactoryMod.interfaces.ItemFactoryInterface;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.ItemFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 *
 * @author Brian
 */
public class ItemFactoryManager {
	
	protected FactoryModPlugin plugin;
	protected List<ItemFactory> itemFactories;
	protected Set<Material> interactionMaterials;
	protected long repairTime;
	/*
	 * For efficiency in factory creation Each unique structure points to a creation point
	 * used by that structure, which in turn points to the properites which use those offsets
	 */
	
	protected Map<Structure,Map<Offset,FactoryProperties>> structures=new HashMap<Structure,Map<Offset,FactoryProperties>>();
	
	public ItemFactoryManager(FactoryModPlugin plugin)
	{
		this.plugin=plugin;
		itemFactories = new ArrayList<ItemFactory>();
		interactionMaterials=new HashSet<Material>();
	}
	
	public void updateFactorys() 
	{
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
		{
			@Override
			public void run()
			{
				for (ItemFactory itemFactory:itemFactories)
				{
					itemFactory.update();
				}
			}
		}, 0L, FactoryModPlugin.UPDATE_CYCLE);
	}

	public ItemFactory factoryAtLocation(Location factoryLocation) 
	{
		for (ItemFactory itemFactory : itemFactories)
		{			
			if(itemFactory.getAnchor().containedIn(factoryLocation, itemFactory.getStructure().getDimensions())) {
				return itemFactory;
			}
		}
		return null;
	}
	
	public boolean factoryExistsAt(Location location) {
		return factoryAtLocation(location)!=null;
	}
	public boolean factoryWholeAt(Location factoryLocation) 
	{
		ItemFactory itemFactory = factoryAtLocation(factoryLocation);
		return itemFactory != null ? itemFactory.isWhole() : false;
	}
	public void removeFactory(ItemFactoryInterface factory) 
	{
		itemFactories.remove((ItemFactory)factory);
	}
		
	public void updateRepair(long time)
	{
		for (ItemFactory itemFactory: itemFactories)
		{
			itemFactory.updateRepair(time/((double)FactoryModPlugin.REPAIR_PERIOD));
		}
		long currentTime=System.currentTimeMillis();
		Iterator<ItemFactory> itr=itemFactories.iterator();
		while(itr.hasNext())
		{
			ItemFactory itemFactory=itr.next();
			if(currentTime>(itemFactory.getTimeDisrepair()+FactoryModPlugin.DISREPAIR_PERIOD))
			{
				itr.remove();
			}
		}
	}
		
	public InteractionResponse addFactory(ItemFactoryInterface factory) 
	{
		if(factory.exists()) {
			itemFactories.add((ItemFactory) factory);
			return new InteractionResponse(InteractionResponse.InteractionResult.SUCCESS, "");
		}
		else
		{
			return new InteractionResponse(InteractionResponse.InteractionResult.FAILURE, "");
		}
	}
	
	/*
	 * Get the material types possible for  
	 */
	public Set<Material> factoryInteractionMaterials() {
		return interactionMaterials;
	}
	
	/*
	 * Atempt to create a factory given the location as the creation point 
	 */
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
	
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		return null;
	}
	
	public Set<Material> getInteractionMaterials() {
		Set<Material> interactionMaterials = new HashSet<Material>();
		for(ItemFactory itemFactory:itemFactories) {
			interactionMaterials.addAll(itemFactory.getStructure().materialsOfOffsets(ItemFactoryProperties.interactionPoints));
		}
		return interactionMaterials;
	}
	
	public Set<Material> getMaterials() {
		Set<Material> materials = new HashSet<Material>();
		for(ItemFactory itemFactory:itemFactories) {
			materials.addAll(itemFactory.getStructure().getMaterials());
		}
		return materials;
	}
}
