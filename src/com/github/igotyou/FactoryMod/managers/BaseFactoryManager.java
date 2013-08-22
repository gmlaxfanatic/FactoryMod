/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import com.github.igotyou.FactoryMod.interfaces.BaseFactoryInterface;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import static com.untamedears.citadel.Utility.getReinforcement;
import static com.untamedears.citadel.Utility.isReinforced;
import com.untamedears.citadel.entity.PlayerReinforcement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 *
 * @author Brian
 */
public class BaseFactoryManager {
	
	protected FactoryModPlugin plugin;
	protected List<BaseFactory> factories;
	
	public BaseFactoryManager(FactoryModPlugin plugin)
	{
		this.plugin=plugin;
		factories = new ArrayList<BaseFactory>();
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
			
			if(baseFactory.getStructure().locationContainedInStructure(baseFactory.getLocation(),baseFactory.getOrientation(),factoryLocation)) {
				return baseFactory;
			}
		}
		return null;
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
		Set<Material> materials=new HashSet<Material>();
		for(BaseFactory baseFactory:factories) {
			materials.addAll(baseFactory.getStructure().materialsOfOffsets(baseFactory.getInteractionPoints()));
		}
		return materials;
	}
	
	public InteractionResponse createFactory(Location location){
		return new InteractionResponse(InteractionResult.FAILURE,"");
	}

	/*
	 * Handles response to playerInteractEvent
	 */
	
	public void playerInteractionReponse(Player player, Block block) {
		//Checks if material is one of the interaction materials for the given factories
		if (factoryInteractionMaterials().contains(block.getType()))
		{
			BaseFactoryInterface baseFactory = factoryAtLocation(block.getLocation());
			//is there a baseFactory at the block location?
			if (baseFactory!=null)
			{
				int interactionPointIndex = baseFactory.getInteractionPointIndex(block.getLocation());
				//If this is an interaction point?
				if(interactionPointIndex!=-1) {
					//if the baseFactory has all its blocks
					if(baseFactory.isWhole())
					{
						//if the player is allowed to interact with that block.
						if ((!FactoryModPlugin.CITADEL_ENABLED || FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || 
								(((PlayerReinforcement) getReinforcement(block)).isAccessible(player)))
						{
							//Send baseFactory response
							baseFactory.interactionResponse(player,interactionPointIndex);
						}
						//if the player does NOT have acssess to the block that was block
						else
						{
							//return a error message
							InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResponse.InteractionResult.FAILURE,"You do not have permission to use this factory!" ));
						}
					}
					else
					{
						InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResponse.InteractionResult.FAILURE,"Factory blocks are misplaced!" ));
					}


					//if no baseFactory exists at the block location
				}
			}
			else
			{
				//if the player is allowed to interact with that block.
				if ((!FactoryModPlugin.CITADEL_ENABLED || FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || 
						(((PlayerReinforcement) getReinforcement(block)).isAccessible(player)))
				{
					InteractionResponse.messagePlayerResult(player, createFactory(block.getLocation()));
				}
				//if the player does NOT have acssess to the block that was block
				else
				{
					//return a error message
					InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResponse.InteractionResult.FAILURE,"You do not have permission to use this factory!" ));
				}
			}
		}
	}

}
