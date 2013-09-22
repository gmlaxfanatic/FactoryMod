package com.github.igotyou.FactoryMod;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;

import com.github.igotyou.FactoryMod.listeners.FactoryModListener;
import com.github.igotyou.FactoryMod.listeners.RedstoneListener;
import com.github.igotyou.FactoryMod.listeners.ReinforcementEffectListener;
import com.github.igotyou.FactoryMod.managers.FactoryModManager;


public class FactoryModPlugin extends JavaPlugin
{

	private static boolean debug=true;
	
	public static FactoryModManager manager;
	
	public static final String PLUGIN_NAME = "[FactoryMod] "; //Name of plugin
	public static final String PRODUCTION_FACTORY_SAVES_FILE = "productionSaves"; // The production saves file name
	public static final int TICKS_PER_SECOND = 20; //The number of ticks per second
	public static final String PRINTING_FACTORY_SAVE_FILE = "pressSaves";
	public static int SAVE_CYCLE;
	public static boolean RETURN_BUILD_MATERIALS;
	public static boolean CITADEL_ENABLED;
	public static Material FACTORY_INTERACTION_MATERIAL;
	public static boolean DISABLE_EXPERIENCE;
	public static long DISREPAIR_PERIOD;
	public static long REPAIR_PERIOD;
	public static boolean REDSTONE_START_ENABLED;
	public static boolean LEVER_OUTPUT_ENABLED;
	
	public void onEnable()
	{
		//load the config.yml
		initConfig();
		//create the main manager
		manager = new FactoryModManager(this);
		manager.initializeManagers();
		manager.loadFactoryManagers();
		//register the events(this should be moved...)
		registerEvents();
	}
	
	public void onDisable()
	{
		//call the disable method, this will save the data etc.
		manager.onDisable();
	}
	
	public void registerEvents()
	{
		try
		{
			getServer().getPluginManager().registerEvents(new FactoryModListener(manager), this);
			getServer().getPluginManager().registerEvents(new RedstoneListener(manager),this);
			getServer().getPluginManager().registerEvents(new ReinforcementEffectListener(manager), this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initConfig()
	{
		FileConfiguration config = getConfig();
		if(getConfig().getDefaults().getBoolean("general.copy_defaults", true))
		{
			saveResource("config.yml",true);
		}
		this.saveDefaultConfig();
		reloadConfig();
		config = getConfig();
		//Loads global parameters
		ConfigurationSection generalConfiguration=config.getConfigurationSection("general");
		//how often should the managers save?
		SAVE_CYCLE = generalConfiguration.getInt("save_cycle",15)*60*20;
		//what's the material of the center block of factorys?
		//CENTRAL_BLOCK_MATERIAL = Material.getMaterial(generalConfiguration.getString("central_block"));
		//Return the build materials upon destruction of factory.
		RETURN_BUILD_MATERIALS = generalConfiguration.getBoolean("return_build_materials",false);
		//is citadel enabled?
		CITADEL_ENABLED = generalConfiguration.getBoolean("citadel_enabled",true);
		//what's the tool that we use to interact with the factorys?
		FACTORY_INTERACTION_MATERIAL = Material.getMaterial(generalConfiguration.getString("factory_interaction_material","STICK"));
		//Check if XP drops should be disabled
		DISABLE_EXPERIENCE=generalConfiguration.getBoolean("disable_experience",false);
		//Period of days before a factory is removed after it falls into disrepair
		DISREPAIR_PERIOD= generalConfiguration.getLong("disrepair_period",14)*24*60*60*1000;
		//The length of time it takes a factory to go to 0% health
		REPAIR_PERIOD = generalConfiguration.getLong("repair_period",28)*24*60*60*1000;
		//Disable recipes which result in the following items
		//Do we output the running state with a lever?
		LEVER_OUTPUT_ENABLED = generalConfiguration.getBoolean("lever_output_enabled",true);
		//Do we allow factories to be started with redstone?
		REDSTONE_START_ENABLED = generalConfiguration.getBoolean("redstone_start_enabled",true);
	}
	
	public static void sendConsoleMessage(String message) 
	{
		Bukkit.getLogger().info(FactoryModPlugin.PLUGIN_NAME + message);	
	}
	public static void debugMessage(String message) {
		if(debug) {
			sendConsoleMessage(message);
		}
	}

	
	public static FactoryModManager getManager()
	{
		return manager;
	}
}
