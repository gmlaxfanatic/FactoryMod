/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brian
 */
public class StructureManager {
	private FactoryModPlugin plugin;
	private Map<String,Structure> structures;
	
	public StructureManager(FactoryModPlugin plugin)
	{
		this.plugin = plugin;
		structures = importStructures(plugin.getConfig().getString("schematic_directory","schematics"));
	}
	/*
	 * Imports all schematic files in teh schematic directory
	 */
	private Map<String,Structure> importStructures(String directory) {
		Map<String,Structure> structures = new HashMap<String,Structure>();
		File[] files = new File(directory).listFiles();
		for (File file : files) {
		    if (file.isFile() && file.getName().endsWith(".schematic")) {
			try { 
			    structures.put(file.getName().split(".")[0],Structure.parseSchematic(file.getCanonicalPath()));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		    }
		}
		return structures;
	}
	/*
	 * Gets the structure associated with the schemictic name
	 */
	public Structure getStructure(String name) {
		return structures.get(name);
	}
}
