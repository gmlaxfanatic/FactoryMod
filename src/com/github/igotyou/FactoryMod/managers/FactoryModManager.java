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
import com.github.igotyou.FactoryMod.Factorys.FactoryObject.FactoryCatorgory;
import com.github.igotyou.FactoryMod.interfaces.BaseFactoryInterface;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class FactoryModManager 
{
	List<Listener> listeners;
	List<FactoryManager> factoryManagers;
	CraftingManager craftingManager;
	FactoryModPlugin plugin;
	
	/**
	 * Constructor
	 */
	public FactoryModManager(FactoryModPlugin plugin)
	{
		this.plugin = plugin;
		initializeManagers();
		periodicSaving();
	}
	
	/**
	 * Initializes the necassary managers for enabled factorys
	 */
	private void initializeManagers()
	{
		factoryManagers = new ArrayList<FactoryManager>();
		listeners = new ArrayList<Listener>();
		
		initializeProductionManager();
		initializePrintingPressManager();
		initializeCraftingManager();
	}
	

	/**
	 * Initializes the Ore Gin Manager
	 */
	private void initializeProductionManager()
	{
		ProductionManager productionManager = new ProductionManager(plugin);
		factoryManagers.add(productionManager);
		
	}
	/**
	 * Initializes the Printing Press Manager
	 */
	private void initializePrintingPressManager()
	{
		PrintingPressManager printingMan = new PrintingPressManager(plugin);
		factoryManagers.add(printingMan);
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
	 * Returns the BaseFactoryInterface Saves file
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

	public BaseFactoryInterface getFactory(Location location) {
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
	public void remove(BaseFactoryInterface factory) {
		for (FactoryManager factoryManager : factoryManagers)
		{
			factoryManager.removeFactory(factory);
		}	
	}

	/*public InteractionResponse createFactory(Location centralLocation,
			Location inventoryLocation, Location powerLocation) {
		InteractionResponse response = null;
		for (FactoryManager manager : factoryManagers)
		{
			response = manager.createFactory(centralLocation, inventoryLocation, powerLocation);
			if (response.getInteractionResult() == InteractionResult.SUCCESS)
			{
				return response;
			}
		}
		return response;
	}*/
	
	public FactoryManager getManager(FactoryCatorgory factoryType)
	{
		Class managerClass=null;
		if(factoryType==FactoryCatorgory.PRODUCTION)
		{
			managerClass = PrintingPressManager.class;
		}
		if(factoryType==FactoryCatorgory.PRINTING_PRESS)
		{
			managerClass = ProductionManager.class;
		}
		for (FactoryManager manager : factoryManagers)
		{
			if (manager.getClass() == PrintingPressManager.class)
			{
				return (PrintingPressManager) manager;
			}
		}
		return null;			
	}
	
	/**
	 * Returns the appropriate manager depending on the given Manager Type
	 */
	@SuppressWarnings("rawtypes")
	public FactoryManager getManager(Class managerType)
	{
		for (FactoryManager manager : factoryManagers)
		{
			if (managerType.isInstance(manager))
			{
				return manager;
			}
		}
		
		return null;
	}
	
	/*
	 * Handles response to playerInteractionEvent
	 */
	
	public void playerInteractionReponse(Player player, Block block) {
		for(FactoryManager factoryManager:factoryManagers) {
			factoryManager.playerInteractionReponse(player, block);
		}
	}
}
