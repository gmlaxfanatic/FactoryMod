/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import com.github.igotyou.FactoryMod.interfaces.BaseFactoryInterface;
import com.github.igotyou.FactoryMod.interfaces.Properties;
import com.github.igotyou.FactoryMod.properties.BaseFactoryProperties;
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
public class BaseFactoryManager {
	
	protected FactoryModPlugin plugin;
	protected List<BaseFactory> factories;
	protected Set<Material> interactionMaterials;
	protected long repairTime;
	/*
	 * For efficiency in factory creation Each unique structure points to a creation point
	 * used by that structure, which in turn points to the properites which use those offsets
	 */
	
	protected Map<Structure,Map<Offset,Properties>> structures=new HashMap<Structure,Map<Offset,Properties>>();
	
	public BaseFactoryManager(FactoryModPlugin plugin)
	{
		this.plugin=plugin;
		factories = new ArrayList<BaseFactory>();
		interactionMaterials=new HashSet<Material>();
	}
	
	public void updateFactorys() 
	{
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
		{
			@Override
			public void run()
			{
				for (BaseFactory baseFactory:factories)
				{
					baseFactory.update();
				}
			}
		}, 0L, FactoryModPlugin.UPDATE_CYCLE);
	}

	public BaseFactory factoryAtLocation(Location factoryLocation) 
	{
		for (BaseFactory baseFactory : factories)
		{
			
			if(baseFactory.getStructure().locationContainedInStructure(baseFactory.getAnchor(),factoryLocation)) {
				return baseFactory;
			}
		}
		return null;
	}
	
	public boolean factoryExistsAt(Location location) {
		return factoryAtLocation(location)!=null;
	}
	public boolean factoryWholeAt(Location factoryLocation) 
	{
		BaseFactory baseFactory = factoryAtLocation(factoryLocation);
		return baseFactory != null ? baseFactory.isWhole() : false;
	}
	public void removeFactory(BaseFactoryInterface factory) 
	{
		factories.remove((BaseFactory)factory);
	}
		
	public void updateRepair(long time)
	{
		for (BaseFactory baseFactory: factories)
		{
			baseFactory.updateRepair(time/((double)FactoryModPlugin.REPAIR_PERIOD));
		}
		long currentTime=System.currentTimeMillis();
		Iterator<BaseFactory> itr=factories.iterator();
		while(itr.hasNext())
		{
			BaseFactory baseFactory=itr.next();
			if(currentTime>(baseFactory.getTimeDisrepair()+FactoryModPlugin.DISREPAIR_PERIOD))
			{
				itr.remove();
			}
		}
	}
		
	public InteractionResponse addFactory(BaseFactoryInterface factory) 
	{
		if(factory.exists()) {
			factories.add((BaseFactory) factory);
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
			for(Entry<Offset,Properties> entry:structures.get(structure).entrySet()) {
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
	
	public InteractionResponse createFactory(Properties properties, Anchor anchor) {
		return null;
	}
	
	public Set<Material> getInteractionMaterials() {
		return BaseFactoryProperties.structure.materialsOfOffsets(BaseFactoryProperties.interactionPoints);
	}
	
	public Set<Material> getMaterials() {
		return BaseFactoryProperties.structure.getMaterials();
	}
}
