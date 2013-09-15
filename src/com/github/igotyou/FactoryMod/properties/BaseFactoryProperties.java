/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
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
public class BaseFactoryProperties {
	public static Structure structure=FactoryModPlugin.getManager().getStructureManager().getStructure("BaseFactory");;
	public static List<Offset> interactionPoints;
	protected ItemList<NamedItemStack> fuel;
	protected int repair;
	private int energyTime;
	private String name;
		
	public BaseFactoryProperties(ItemList<NamedItemStack>  fuel, int repair, int energyTime, String name) {
		this.structure=FactoryModPlugin.getManager().getStructureManager().getStructure("BaseFactory");
		this.fuel=fuel;
		this.repair=repair;
		this.energyTime=energyTime;
		this.name=name;
	}
	
	public int getEnergyTime()
	{
		return energyTime;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Structure getStructure() {
		return structure;
	}
	
	public List<Offset> getInteractionPoints() {
		return interactionPoints;
	}
	
	public Set<Material> getInteractionMaterials() {
		return structure.materialsOfOffsets(interactionPoints);
	}
	
		
	public Offset getCreationPoint() {
		return interactionPoints.get(1);
	}
}
