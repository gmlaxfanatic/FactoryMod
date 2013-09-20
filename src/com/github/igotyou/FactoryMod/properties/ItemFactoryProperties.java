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
public class ItemFactoryProperties extends BaseFactoryProperties{

	protected int energyTime;
	protected ItemList<NamedItemStack> fuel;
	protected int repair;

	public ItemFactoryProperties(String factoryID, Structure structure, List<Offset> interactionPoints, ItemList<NamedItemStack>  fuel, int repair, int energyTime, String name) {
		super(factoryID, structure, interactionPoints, name);
		this.repair=repair;
		this.fuel=fuel;
		this.energyTime=energyTime;
	}
	
	public int getEnergyTime()
	{
		return energyTime;
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
