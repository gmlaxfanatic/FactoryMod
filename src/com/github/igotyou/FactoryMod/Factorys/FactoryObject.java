package com.github.igotyou.FactoryMod.Factorys;


import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import org.bukkit.Location;

import com.github.igotyou.FactoryMod.interfaces.Properties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import org.bukkit.entity.Player;

//original file:
/**
 * MachineObject.java
 * Purpose: Basic object base for machines to extend
 *
 * @author MrTwiggy
 * @version 0.1 1/14/13
 */
//edited version:
/**
 * FactoryObject.java	 
 * Purpose basic object base for factories to extend
 * @author igotyou
 *
 */
public class FactoryObject implements Factory {
	//the diffrent factory types, NOTE: these are not the sub-factory types, these are the main types.
	public enum FactoryCategory
	{
		PRODUCTION,
		PRINTING_PRESS
	}
	
	
	
	protected Anchor anchor;
	protected List<Offset> interactionPoints;
	protected boolean active; // Whether factory is currently active
	protected FactoryCategory factoryCategory; // The type this factory is
	protected String factoryType;//the SUBfactory type(the ones loaded from the config file)
	protected Properties factoryProperties; // The properties of this factory type and tier
	
	/**
	 * Constructor
	 */
	public FactoryObject(Anchor anchor,
		Structure structure,
		List<Offset> interactionPoints,
		boolean active,
		FactoryCategory factoryType,
		Properties properties)
	{
		this.anchor=anchor;
		this.interactionPoints=interactionPoints;
		this.active = active;
		this.factoryCategory = factoryType;
		this.factoryProperties = properties;
	}

	/**
	 * Returns the sub-factory type of the factory. 
	 */
	public String getFactoryType()
	{
		return factoryType;
	}
	
	
	/**
	 * returns if the factory is on or not.
	 */
	public boolean getActive()
	{
		return active;
	}
	public Structure getStructure() {
		return factoryProperties.getStructure();
	}
	/**
	 * returns true if all factory blocks are occupied with the correct blocks
	 */
	public boolean isWhole()
	{
		return getStructure().exists(anchor);
	}
	public Location getLocation() {
		return anchor.location;
	}
	public Orientation getOrientation() {
		return anchor.orientation;
	}
	public boolean exists() {
		return getStructure().exists(anchor);
	}
	public List<Offset> getInteractionPoints() {
		return interactionPoints;
	}
	/*
	 * gets the index of the interaction point at the location
	 * returns -1 if no intearction point exists at that location
	 */
	public int getInteractionPointIndex(Location location) {
		return getStructure().getInteractionPoint(this, location);
	}
	
	public void interactionResponse(Player player, Location location) {
		
	}
	public FactoryCategory getFactoryCategory() {
		return factoryCategory;
	}
	
	public void breakFactory() {
		
	}
	
	public Anchor getAnchor() {
		return anchor;
	}
}