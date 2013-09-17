package com.github.igotyou.FactoryMod.utility;

import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
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
		return new Offset(x*(orientation==Orientation.NE || orientation==Orientation.NW ? 1 : -1),y,
			z*(orientation==Orientation.NE || orientation==Orientation.SE ? 1 : -1));
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
		for(Orientation orientation:Orientation.values()) {
			Offset orientatedOffset = this.orient(orientation);
			anchors.add(new Anchor(orientation,location.clone().add(orientatedOffset.toVector())));
		}
		return anchors;
	}
}
