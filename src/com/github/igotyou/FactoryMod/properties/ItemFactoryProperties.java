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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;

/**
 *
 * @author Brian
 */
public class ItemFactoryProperties implements FactoryProperties{
	protected String factoryID;//Unique name of the factory
	protected Structure structure;//=FactoryModPlugin.getManager().getStructureManager().getStructure("ItemFactory");//=FactoryModPlugin.getManager().getStructureManager().getStructure("ItemFactory");;
	protected List<Offset> interactionPoints;// = Arrays.asList(new Offset(0,0,0),new Offset(1,0,0),new Offset(2,0,0));
	protected ItemList<NamedItemStack> fuel;
	protected int repair;
	protected int energyTime;
	protected String name;

	public ItemFactoryProperties(String factoryID, Structure structure, List<Offset> interactionPoints, ItemList<NamedItemStack>  fuel, int repair, int energyTime, String name) {
		this.factoryID=factoryID;
		this.structure=structure;
		this.interactionPoints=interactionPoints;
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
	
	public Offset getInventoryOffset() {
		return interactionPoints.get(0);
	}
	
	public Offset getCenterOffset() {
		return interactionPoints.get(1);
	}
	
	public Offset getPowerSourceOffset() {
		return interactionPoints.get(2);
	}
}
