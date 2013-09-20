/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Brian
 */
public class AreaFactoryProperties extends BaseFactoryProperties{
	
	Map<Integer,Set<AreaEffect>> areaEffects;
	protected int energyTime;
	protected ItemList<NamedItemStack> fuel;
	
	public AreaFactoryProperties(String factoryID, Structure structure, List<Offset> interactionPoints, ItemList<NamedItemStack> inputs, List<ProductionRecipe> recipes,
			ItemList<NamedItemStack> fuel, int energyTime, String name) {
		super(factoryID, structure, interactionPoints, name);
		this.fuel=fuel;
		this.energyTime=energyTime;
	}
		
	public int getEnergyTime()
	{
		return energyTime;
	}
	
	public ItemList<NamedItemStack> getFuel() {
		return fuel;
	}
	
	public Offset getPowerSourceOffset() {
		return interactionPoints.get(0);		
	}
	
	public Offset getCreationPoint() {
		return interactionPoints.get(0);
	}
	
	public Map<Integer,Set<AreaEffect>> getAreaEffects() {
		return areaEffects;
	}
	
	/*
	 * Gets a set of all of the area effects
	 */
	
	public Set<AreaEffect> getAllAreaEffects() {
		Set<AreaEffect> allAreaEffects = new HashSet<AreaEffect>();
		for(Set<AreaEffect> areaEffect:areaEffects.values()) {
			allAreaEffects.addAll(areaEffect);
		}
		return allAreaEffects;
	}
	
}
