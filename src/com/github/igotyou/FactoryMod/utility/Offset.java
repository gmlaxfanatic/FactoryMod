package com.github.igotyou.FactoryMod.utility;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

/**
 *
 * Represents a location within a structure
 */
public class Offset {
	
	public final int x;
	public final int y;
	public final int z;
	
	public Offset(int x, int y, int z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	/*
	 * Rotates the positive offsets given an orientation
	 */
	public Offset orient(Orientation orientation) {
		int newX=x*(orientation==Orientation.NW || orientation==Orientation.SE ? 0 : (orientation==Orientation.NE ? 1: -1))+
			z*(orientation==Orientation.NE || orientation==Orientation.SW ? 0 : (orientation==Orientation.NW ? 1 : -1));
		int newZ=x*(orientation==Orientation.NE || orientation==Orientation.SW ? 0 : (orientation==Orientation.NW ? 1 : -1))+
			z*(orientation==Orientation.NW || orientation==Orientation.SE ? 0 : (orientation==Orientation.NE ? 1: -1));
		return new Offset(newX,y,newZ);
	}

	/*
	 * Converts the offset to a bukkit vector
	 */
	public Vector toVector() {
		return new Vector(x,y,z);
	}
	
	/*
	 * Returns the four anchor spots possible given a location
	 */
	public Set<Anchor> getPotentialAnchors(Location location) {
		Set<Anchor> anchors = new HashSet<Anchor>();
		FactoryModPlugin.debugMessage("Potential anchors for " +location.toString());
		for(Orientation orientation:Orientation.values()) {
			Offset orientatedOffset = this.orient(orientation);
			anchors.add(new Anchor(orientation,location.clone().subtract(orientatedOffset.toVector())));
			FactoryModPlugin.debugMessage("Orientation "+orientation.name()+". "+new Anchor(orientation,location.clone().subtract(orientatedOffset.toVector())).location.toString());
		}
		return anchors;
	}
	
	/*
	 * Imports an offset from the configuration file
	 */
	public static Offset fromConfig(ConfigurationSection configurationSection) {
		return new Offset(configurationSection.getInt("x", 0),configurationSection.getInt("y", 0),configurationSection.getInt("z", 0));
	}
}
