package com.github.igotyou.FactoryMod.listeners;

import static com.untamedears.citadel.Utility.isReinforced;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.BaseFactoryInterface;
import com.github.igotyou.FactoryMod.managers.FactoryModManager;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;

public class FactoryModListener implements Listener
{
	private FactoryModManager factoryMan;
	
	/**
	 * Constructor
	 */
	public FactoryModListener(FactoryModManager factoryManager)
	{
		this.factoryMan = factoryManager;
	}
	
	private boolean isPotentialFactoryBlock(Block block) {
		return block.getType() == FactoryModPlugin.CENTRAL_BLOCK_MATERIAL || block.getType() == Material.IRON_BLOCK || block.getType() == Material.CHEST||
				block.getType() == Material.FURNACE || block.getType() == Material.BURNING_FURNACE;
	}
	
	/**
	 * Called when a block is broken
	 * If the block that is destroyed is part of a factory, call the required methods.
	 */
	@EventHandler
	public void blockBreakEvent(BlockBreakEvent e)
	{
		Block block = e.getBlock();
		//Is the block part of a factory?
		if(isPotentialFactoryBlock(block))
		{
			if (factoryMan.factoryExistsAt(block.getLocation()))
			{
				//if the blocks is not reinforced destroy it
				if ((FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || !FactoryModPlugin.CITADEL_ENABLED)
				{
					destroyFactoryAt(block);
				}
			}
		}
	}
	
	private void destroyFactoryAt(Block block) {
		//Is the factory a production factory?
		if (factoryMan.factoryExistsAt(block.getLocation()))
		{
			BaseFactoryInterface factory = factoryMan.getFactory(block.getLocation());
			factory.destroy(block.getLocation());
			if(FactoryModPlugin.DESTRUCTIBLE_FACTORIES)
			{
				factoryMan.removeFactory(factory);
			}
		}
	}
	
	/**
	 * Called when a entity explodes(creeper,tnt etc.)
	 * Nearly the same as blockBreakEvent
	 */
	@EventHandler
	public void explosionListener(EntityExplodeEvent e)
	{
		List<Block> blocks = e.blockList();
		for (Block block : blocks)
		{
			if(isPotentialFactoryBlock(block))
			{
				if (factoryMan.factoryExistsAt(block.getLocation()))
				{
					BaseFactoryInterface factory = factoryMan.getFactory(block.getLocation());
					if ((FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || !FactoryModPlugin.CITADEL_ENABLED)
					{
						destroyFactoryAt(block);
					}
				}
			}
		}
	}
	
	/**
	 * Called when a block burns
	 * Nearly the same as blockBreakEvent
	 */
	@EventHandler
	public void burnListener(BlockBurnEvent e)
	{
		Block block = e.getBlock();
		if (factoryMan.factoryExistsAt(block.getLocation()))
		{
			if (factoryMan.factoryExistsAt(block.getLocation()))
			{
				destroyFactoryAt(block);
			}
		}
	}
	
	/**
	 * Called when a player left or right clicks.
	 * Takes care of cycling recipes turning factory's on and off, etc.
	 */
	@EventHandler
	public void playerInteractionEvent(PlayerInteractEvent e)
	{
		Block clicked = e.getClickedBlock();
		Player player = e.getPlayer();
		
		//if the player left clicked a block
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			//If the player was holding a item matching the interaction material
			if (player.getItemInHand().getType() == FactoryModPlugin.FACTORY_INTERACTION_MATERIAL)
			{
				factoryMan.playerInteractionReponse(player,clicked);			
				
			}
		}
	}
	private Location westLoc(Location loc)
	{
		Location newLoc = loc.clone();
		newLoc.add(-1, 0, 0);
		return newLoc;
	}
	private Location eastLoc(Location loc)
	{
		Location newLoc = loc.clone();
		newLoc.add(1, 0, 0);
		return newLoc;
	}
	private Location northLoc(Location loc)
	{
		Location newLoc = loc.clone();
		newLoc.add(0, 0, -1);
		return newLoc;
	}
	private Location southLoc(Location loc)
	{
		Location newLoc = loc.clone();
		newLoc.add(0, 0, 1);
		return newLoc;
	}
	
	private InteractionResponse createFactory(Location loc, Player player)
	{
		Location northLocation = northLoc(loc);
		Location southLocation = southLoc(loc);
		Location eastLocation = eastLoc(loc);
		Location westLocation = westLoc(loc);
		 
		Block northBlock = northLocation.getBlock();
		Block southBlock = southLocation.getBlock();
		Block eastBlock = eastLocation.getBlock();
		Block westBlock = westLocation.getBlock();
		 
		Material northType = northBlock.getType();
		Material southType = southBlock.getType();
		Material eastType = eastBlock.getType();
		Material westType = westBlock.getType();
 
		//For each two orientations check if a factory exists at any of the locations
		//For each of the four permutations check if the correct blocks are present
		//This still allows a double chest to be shared between two factories, which may lead to undesirable behavior
		InteractionResponse response=new InteractionResponse(InteractionResult.FAILURE, "Blocks are not arranged correctly for a factory.");
		if(! factoryMan.factoryExistsAt(westLocation) && ! factoryMan.factoryExistsAt(eastLocation))
		{
			if((westType.getId()== 61 || westType.getId() == 62) && eastType.getId()== 54)
			{
				return factoryMan.createFactory(loc, eastLocation, westLocation);
			}
			else if ((eastType.getId()== 61 || eastType.getId()== 62) && westType.getId()== 54)
			{
				return factoryMan.createFactory(loc, westLocation, eastLocation);
			}
		}
		else
		{
			response=new InteractionResponse(InteractionResult.FAILURE, "There is already a factory there!");
		}
		if(! factoryMan.factoryExistsAt(southLocation) && !factoryMan.factoryExistsAt(northLocation))
		{
			if((northType.getId()== 61 || northType.getId()== 62) && southType.getId()== 54)
			{
				return factoryMan.createFactory(loc, southLocation, northLocation);
			}
			else if((southType.getId()== 61 || southType.getId()== 62) && northType.getId()== 54)
			{
				return factoryMan.createFactory(loc, northLocation, southLocation);
			}
		}
		else
		{
			response=new InteractionResponse(InteractionResult.FAILURE, "There is already a factory there!");
		}
		return response;
	 }
	
}
