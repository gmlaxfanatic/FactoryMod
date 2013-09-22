package com.github.igotyou.FactoryMod.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory.FactoryCategory;
import com.github.igotyou.FactoryMod.Factorys.ItemFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import static com.untamedears.citadel.Utility.getReinforcement;
import static com.untamedears.citadel.Utility.isReinforced;
import com.untamedears.citadel.entity.PlayerReinforcement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class FactoryModManager 
{
	List<Listener> listeners;
	List<FactoryManager> factoryManagers;
	Map<FactoryCategory,FactoryManager> categoryToManager=new HashMap<FactoryCategory,FactoryManager>();
	CraftingManager craftingManager;
	StructureManager structureManager;
	FactoryModPlugin plugin;
	
	/**
	 * Constructor
	 */
	public FactoryModManager(FactoryModPlugin plugin)
	{
		this.plugin = plugin;		
	}
	
	/**
	 * Initializes the necassary managers for enabled factorys
	 */
	public void initializeManagers()
	{
		factoryManagers = new ArrayList<FactoryManager>();
		listeners = new ArrayList<Listener>();
		
		initializeStructureManager();
		initializeCraftingManager();
		initializeProductionManager();
		initializePrintingManager();
		periodicSaving();
	}
	

	/**
	 * Initializes the Production Manager
	 */
	private void initializeStructureManager()
	{
		structureManager = new StructureManager(plugin);
	}
	
	/**
	 * Initializes the Production Manager
	 */
	private void initializeProductionManager()
	{
		ProductionFactoryManager productionManager = new ProductionFactoryManager(plugin, plugin.getConfig().getConfigurationSection("production"));
		factoryManagers.add(productionManager);
		categoryToManager.put(FactoryCategory.PRODUCTION,productionManager);
	}
	/**
	 * Initializes the Printing  Manager
	 */
	private void initializePrintingManager()
	{
		PrintingFactoryManager printingManager = new PrintingFactoryManager(plugin, plugin.getConfig().getConfigurationSection("printing"));
		factoryManagers.add(printingManager);
		categoryToManager.put(FactoryCategory.PRINTING,printingManager);
	}
	
	/**
	 * Initializes the Crafting Manager
	 */
	private void initializeCraftingManager()
	{
		craftingManager = new CraftingManager(plugin);
	}
	
	/**
	 * When plugin disabled, this is called.
	 */
	public void onDisable()
	{
		saveFactoryManagers();
	}
	/**
	 * Returns the ItemFactory Saves file
	 */
	public File getSavesFile(String fileName)
	{
		return new File(plugin.getDataFolder(), fileName + ".txt");
	}
	/**
	 * Saves all managers
	 */
	private void saveFactoryManagers()
	{
		for (FactoryManager manager : factoryManagers)
		{
			save(manager, getSavesFile(manager.getSavesFileName()));
		}
	}
	
	/**
	 * Loads all managers
	 */
	public void loadFactoryManagers()
	{
		for (FactoryManager manager : factoryManagers)
		{
			load(manager, getSavesFile(manager.getSavesFileName()));
		}
	}
	
	/**
	 * Load file
	 */
	private static void load(FactoryManager factoryManager, File file) 
	{
		try
		{
			factoryManager.load(file);
		}
		catch (FileNotFoundException exception)
		{
			FactoryModPlugin.sendConsoleMessage(file.getName() + " does not exist! Creating file!");
		}
		catch (IOException exception)
		{
			throw new RuntimeException("Failed to load " + file.getPath(), exception);
		}
		
		try
		{
			factoryManager.save(file);
		}
		catch (IOException exception)
		{
			throw new RuntimeException("Failed to create " + file.getPath(), exception);
		}
	}

	/**
	 * Save file
	 */
	private static void save(FactoryManager manager, File file) 
	{	
		try
		{
			File newFile = new File(file.getAbsolutePath() + ".new");
			File bakFile = new File(file.getAbsolutePath() + ".bak");
			
			manager.save(newFile);
			
			if (bakFile.exists())
			{
				bakFile.delete();
			}
			
			if (file.exists() && !file.renameTo(bakFile))
			{
				throw new IOException("Failed to rename " + file.getAbsolutePath() + " to " + bakFile.getAbsolutePath());
			}
			
			if (!newFile.renameTo(file))
			{
				throw new IOException("Failed to rename " + newFile.getAbsolutePath() + " to " + file.getAbsolutePath());
			}
		}
		catch (IOException exception)
		{
			throw new RuntimeException("Failed to save to " + file.getAbsolutePath(), exception);
		}
	}
	
	public StructureManager getStructureManager() {
		return structureManager;
	}
	
	/**
	 * Save Factories to file every SAVE_CYCLE minutes.
	 */
	private void periodicSaving()
	{
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override  
			public void run()
			{
				FactoryModPlugin.sendConsoleMessage("Saving Factory data...");
				saveFactoryManagers();
			}
		}, (FactoryModPlugin.SAVE_CYCLE), 
		FactoryModPlugin.SAVE_CYCLE);
	}
	
	/**
	 * Returns whether a factory exists at given location in any manager
	 */
	public boolean factoryExistsAt(Location location)
	{
		for (FactoryManager manager : factoryManagers)
		{
			if (manager.factoryExistsAt(location))
			{
				return true;
			}
		}	
		return false;
	}

	/**
	 * Returns whether a factory is whole at given location in any manager
	 */
	public boolean factoryWholeAt(Location location)
	{
		for (FactoryManager manager : factoryManagers)
		{
			if (manager.factoryWholeAt(location))
			{
				return true;
			}
		}	
		return false;
	}	

	public Factory getFactory(Location location) {
		for (FactoryManager manager : factoryManagers)
		{
			if (manager.factoryExistsAt(location))
			{
				return manager.factoryAtLocation(location);
			}
		}	
		return null;
	}
	/*
	 * Removes a factory at the given location if it exists
	 */
	public void remove(ItemFactory factory) {
		for (FactoryManager factoryManager : factoryManagers)
		{
			factoryManager.removeFactory(factory);
		}	
	}
	
	public FactoryManager getManager(FactoryCategory factoryCategory)
	{
		if(categoryToManager.containsKey(factoryCategory)) {
			return categoryToManager.get(factoryCategory);
		}
		else {
			return null;
		}
	}

	
	/*
	 * Handles response to playerInteractionEvent
	 */
	
	public void playerInteractionReponse(Player player, Block block) {
		//Check which managers contain relevant interaction blocks
		Set<FactoryManager> possibleManagers=new HashSet();
		for(FactoryManager factoryManager:factoryManagers) {
			FactoryModPlugin.debugMessage(factoryManager.getInteractionMaterials().toString());
			if(factoryManager.getInteractionMaterials().contains(block.getType())) {
				possibleManagers.add(factoryManager);
			}
		}
		if(possibleManagers.isEmpty()) {
			FactoryModPlugin.sendConsoleMessage("Not an interaction block: "+block.getTypeId());
			return;
		}
		//Check that the player is able ot interact with the block
		if ((FactoryModPlugin.CITADEL_ENABLED && isReinforced(block)) && !(((PlayerReinforcement) getReinforcement(block)).isAccessible(player))) {
			InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResponse.InteractionResult.FAILURE,"You do not have permission to use this factory!" ));
			FactoryModPlugin.debugMessage("Blocked by interaction by citadel");
			return;
		}
		//Check if a factory exists at the location and have it respond
		Factory factory = factoryAtLocation(block.getLocation());
		if(factory!=null) {
			FactoryModPlugin.debugMessage("Factory at location");
			factory.interactionResponse(player, block.getLocation());
		}
		else {
			InteractionResponse response=new InteractionResponse(InteractionResult.IGNORE, "Error");
			for(FactoryManager factoryManager:possibleManagers) {
				//Atempt to create a factory given the location as the creation point 
				response = factoryManager.createFactory(block.getLocation());
				if(response.getInteractionResult()==InteractionResult.SUCCESS) {
					InteractionResponse.messagePlayerResult(player, response);
					FactoryModPlugin.debugMessage("Factory Created");
					return;
				}
			}
			InteractionResponse.messagePlayerResult(player, response);
		}
	}
	
	/*
	 * Get a factory at the given location
	 */
	public Factory factoryAtLocation(Location factoryLocation) 
	{
		for (FactoryManager manager : factoryManagers)
		{
			return manager.factoryAtLocation(factoryLocation);
		}
		return null;
	}
	/*
	 * Checks if Materail is part of any factory
	 */
	public boolean isPotentialFactoryBlock(Material material) {
		for(FactoryManager factoryManager:factoryManagers) {
			if(factoryManager.getMaterials().contains(material)) {
				return true;
			}
		}
		return false;
	}
	
	public void blockBreakResponse(Location location) {
		Factory factory = getFactory(location);
		if(factory != null) {
			factory.blockBreakResponse();
		}

	}
}
