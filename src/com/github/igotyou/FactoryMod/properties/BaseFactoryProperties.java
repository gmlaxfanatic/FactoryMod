/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;

/**
 *
 * @author Brian
 */
public abstract class BaseFactoryProperties implements FactoryProperties {

	protected String factoryID; //Unique name of the factory
	protected List<Offset> interactionPoints;
	protected String name;
	protected Structure structure;

	public BaseFactoryProperties(String factoryID, Structure structure, List<Offset> interactionPoints, String name) {
		this.factoryID=factoryID;
		this.structure=structure;
		this.interactionPoints=interactionPoints;

		this.name=name;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public Structure getStructure() {
		return structure;
	}
	
	@Override
	public List<Offset> getInteractionPoints() {
		return interactionPoints;
	}
	
	@Override
	public Set<Material> getInteractionMaterials() {
		return structure.materialsOfOffsets(interactionPoints);
	}
	
}
