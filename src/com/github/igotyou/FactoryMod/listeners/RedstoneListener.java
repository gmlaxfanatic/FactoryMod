package com.github.igotyou.FactoryMod.listeners;

import static com.untamedears.citadel.Utility.getReinforcement;
import static com.untamedears.citadel.Utility.isReinforced;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.material.Attachable;
import org.bukkit.material.Lever;
import org.bukkit.material.MaterialData;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.managers.FactoryModManager;
import com.github.igotyou.FactoryMod.managers.ProductionManager;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.untamedears.citadel.entity.PlayerReinforcement;

public class RedstoneListener implements Listener {
	private FactoryModManager factoryMan;
	//this is a lazy fix...
	private ProductionManager productionMan;
	
	/**
	 * Constructor
	 */
	public RedstoneListener(FactoryModManager factoryManager, ProductionManager productionManager)
	{
		this.factoryMan = factoryManager;
		this.productionMan = productionManager;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void leverPlaced(BlockPlaceEvent e) {
		if (e.getBlock().getType() != Material.LEVER) {
			return;
		}
		
		Block clicked = e.getBlockAgainst();
		//is there a factory there? and if it has all its blocks
		if (factoryMan.factoryExistsAt(clicked.getLocation())&&factoryMan.factoryWholeAt(clicked.getLocation()))
		{
			//if the player is allowed to interact with that block?
			if ((!FactoryModPlugin.CITADEL_ENABLED || FactoryModPlugin.CITADEL_ENABLED && !isReinforced(clicked)) || 
					(((PlayerReinforcement) getReinforcement(clicked)).isAccessible(e.getPlayer())))
			{
				// Allowed
			}
			//if the player is NOT allowed to interact with the clicked block
			else
			{
				e.setCancelled(true);
			}
		}
	}
	

	/**
	 * Called when a block is charged.
	 * When the furnace block is powered, starts the factory and toggles on any attached levers.
	 * On completion, toggles off any attached levers.
	 */
    @EventHandler()
	public void redstoneChange(BlockRedstoneEvent e)
	{	
		// Only trigger on transition from 0 to positive
		if (e.getOldCurrent() > 0 || e.getNewCurrent() == 0) {
			return;
		}
		
		// Allow this to be disabled with config
		if (!FactoryModPlugin.REDSTONE_START_ENABLED) {
			return;
		}
		
		Block rsBlock = e.getBlock();
		BlockFace[] directions = null;
		if (rsBlock.getType() == Material.REDSTONE_WIRE) {
			directions = ProductionFactory.REDSTONE_FACES;
		} else if (rsBlock.getType() == Material.WOOD_BUTTON) {
			directions = new BlockFace[] {((Attachable) rsBlock.getState().getData()).getAttachedFace()};
		} else if (rsBlock.getType() == Material.STONE_BUTTON) {
			directions = new BlockFace[] {((Attachable) rsBlock.getState().getData()).getAttachedFace()};
		} else if (rsBlock.getType() == Material.LEVER) {
			directions = new BlockFace[] {((Attachable) rsBlock.getState().getData()).getAttachedFace()};
		} else {
			return; // Don't care
		}
		
		
		for (BlockFace direction : directions) {
			Block block = rsBlock.getRelative(direction);
			
			//Is the block part of a factory?
			if(block.getType() == Material.FURNACE || block.getType() == Material.BURNING_FURNACE)
			{
				if (factoryMan.factoryExistsAt(block.getLocation()))
				{					
					//Is the factory a production factory?
					if (productionMan.factoryExistsAt(block.getLocation()))
					{
						ProductionFactory factory = (ProductionFactory) productionMan.getFactory(block.getLocation());
						
						Block lever = factory.findActivationLever();
						if (lever == null) {
							// No lever - don't respond to redstone
							return;
						}
						
						if (!factory.getActive()) {
							// Try to start the factory
							factory.togglePower();
						}
					}
				}
			}
		}
	}
}
