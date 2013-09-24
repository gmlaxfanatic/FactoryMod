package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
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

	public enum FactoryCategory {

		PRODUCTION,
		PRINTING
	}
	protected Anchor anchor;
	protected FactoryCategory factoryCategory; // The category of this factory
	protected String factoryType;//the factory type(the ones loaded from the config file)
	private transient FactoryProperties factoryProperties;

	public BaseFactory(Anchor anchor,
		FactoryCategory factoryCategory,
		String factoryType) {
		this.anchor = anchor;
		this.factoryCategory = factoryCategory;
		this.factoryType = factoryType;
	}

	@Override
	public Structure getStructure() {
		return getFactoryProperties().getStructure();
	}

	/**
	 * Returns the sub-factory type of the factory.
	 */
	public String getFactoryType() {
		return factoryType;
	}

	/**
	 * returns true if all factory blocks are occupied with the correct
	 * blocks
	 */
	@Override
	public boolean isWhole() {
		return getStructure().exists(anchor);
	}

	@Override
	public Anchor getAnchor() {
		return anchor;
	}

	@Override
	public Location getLocation() {
		return anchor.getLocation();
	}

	@Override
	public Orientation getOrientation() {
		return anchor.orientation;
	}

	@Override
	public boolean exists() {
		return getStructure().exists(anchor);
	}

	public List<Offset> getInteractionPoints() {
		return getFactoryProperties().getInteractionPoints();
	}

	@Override
	public void interactionResponse(Player player, Location location) {
	}

	@Override
	public FactoryCategory getFactoryCategory() {
		return factoryCategory;
	}

	protected FactoryProperties getFactoryProperties() {
		if(factoryProperties==null) {
			factoryProperties = FactoryModPlugin.getManager().getManager(factoryCategory).getProperties(factoryType);
		}
		return factoryProperties;
	}
}