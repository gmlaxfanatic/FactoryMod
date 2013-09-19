package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.interfaces.Factory;
import org.bukkit.Location;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import org.bukkit.entity.Player;

public abstract class BaseFactory implements Factory {
	//the diffrent factory types, NOTE: these are not the sub-factory types, these are the main types.
	public enum FactoryCategory
	{
		PRODUCTION,
		PRINTING
	}
	
	protected Anchor anchor;
	protected FactoryCategory factoryCategory; // The category of this factory
	protected String factoryType;//the factory type(the ones loaded from the config file)
	protected FactoryProperties factoryProperties; // The properties of this factory type and tier
	
	public BaseFactory(Anchor anchor,
		FactoryCategory factoryType,
		FactoryProperties properties)
	{
		this.anchor=anchor;
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
	public Anchor getAnchor() {
		return anchor;
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
		return factoryProperties.getInteractionPoints();
	}
	public void interactionResponse(Player player, Location location) {
		
	}
	public FactoryCategory getFactoryCategory() {
		return factoryCategory;
	}
}