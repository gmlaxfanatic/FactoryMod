/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages all of the structure files for all of the different types of factories
 * in a central location
 */
public class StructureManager {
	private FactoryModPlugin plugin;
	private Map<String,Structure> structures;
	
	public StructureManager(FactoryModPlugin plugin)
	{
		this.plugin = plugin;
		structures = importStructures(plugin.getConfig().getList("structures", new ArrayList()));
	}
	/*
	 * Imports all schematic files in the schematic directory
	 */
	private Map<String,Structure> importStructures(List uncastFilenames) {
		Map<String,Structure> structures = new HashMap<String,Structure>();
		List<String> filenames=uncastFilenames;
		for(String filename:filenames) {
			plugin.saveResource("schematics/"+filename, true);
			structures.put(filename,Structure.parseSchematic(new File(plugin.getDataFolder(),"schematics/"+filename)));
		}
		return structures;
	}
	/*
	 * Gets the structure associated with the schematic name
	 */
	public Structure getStructure(String name) {
		return structures.get(name);
	}
}
