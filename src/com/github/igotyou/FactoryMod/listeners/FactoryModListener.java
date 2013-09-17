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
import com.github.igotyou.FactoryMod.interfaces.ItemFactoryInterface;
import com.github.igotyou.FactoryMod.managers.FactoryModManager;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;

public class FactoryModListener implements Listener
{
	private FactoryModManager factoryManager;
	
	/**
	 * Constructor
	 */
	public FactoryModListener(FactoryModManager factoryManager)
	{
		this.factoryManager = factoryManager;
	}

	/**
	 * Called when a block is broken
	 * If the block that is destroyed is part of a factory, call the required methods.
	 */
	@EventHandler
	public void blockBreakEvent(BlockBreakEvent e)
	{
		Block block = e.getBlock();
		if ((FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || !FactoryModPlugin.CITADEL_ENABLED)
		{
			if(factoryManager.isPotentialFactoryBlock(block.getType()))
			{
				factoryManager.breakFactoryAt(block.getLocation());
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
			if ((FactoryModPlugin.CITADEL_ENABLED && !isReinforced(block)) || !FactoryModPlugin.CITADEL_ENABLED)
			{
				if(factoryManager.isPotentialFactoryBlock(block.getType()))
				{
					factoryManager.breakFactoryAt(block.getLocation());
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
		if(factoryManager.isPotentialFactoryBlock(block.getType()))
		{
			factoryManager.breakFactoryAt(block.getLocation());
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
				factoryManager.playerInteractionReponse(player,clicked);			
				
			}
		}
	}
}
