package com.github.igotyou.FactoryMod.Factorys;


import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import org.bukkit.Location;

import com.github.igotyou.FactoryMod.interfaces.Properties;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import com.github.igotyou.FactoryMod.utility.Structure.Orientation;
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
	public enum FactoryCatorgory
	{
		PRODUCTION,
		PRINTING_PRESS
	}
	
	
	
	protected Location location;//Anchor position of the factory structure
	protected Orientation orientation;//describes oritentation of the factory structure
	protected Structure structure;//The block structure of the factory
	protected List<Offset> interactionPoints;
	protected boolean active; // Whether factory is currently active
	protected FactoryCatorgory factoryCatorgory; // The type this factory is
	protected String factoryType;//the SUBfactory type(the ones loaded from the config file)
	protected Properties factoryProperties; // The properties of this factory type and tier
	
	/**
	 * Constructor
	 */
	public FactoryObject(Location location,
		Structure structure,
		Orientation orientation,
		List<Offset> interactionPoints,
		boolean active,
		FactoryCatorgory factoryType,
		String subFactoryType)
	{
		this.location=location;
		this.structure=structure;
		this.orientation=orientation;
		this.interactionPoints=interactionPoints;
		this.active = active;
		this.factoryCatorgory = factoryType;
		this.factoryType = subFactoryType;
		updateProperties();
	}

	/**
	 * Updates the current properties for the factory.
	 */
	public void updateProperties()
	{
		factoryProperties = FactoryModPlugin.getManager().getManager(factoryCatorgory).getProperties(factoryType);
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
	
	/**
	 * returns true if all factory blocks are occupied with the correct blocks
	 */
	public boolean isWhole()
	{
		return structure.exists(location, orientation);
	}
	public Location getLocation() {
		return location;
	}
	public Structure getStructure()
	{
		return structure;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	public boolean exists() {
		return structure.exists(location, orientation);
	}
	public List<Offset> getInteractionPoints() {
		return interactionPoints;
	}
	/*
	 * gets the index of the interaction point at the location
	 * returns -1 if no intearction point exists at that location
	 */
	public int getInteractionPointIndex(Location location) {
		return structure.getInteractionPoint(this, location);
	}
	
	public void interactionResponse(Player player, int interactionPoint) {
		
	}
}