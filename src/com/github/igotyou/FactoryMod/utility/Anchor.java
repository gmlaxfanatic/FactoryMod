/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.utility;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 *
 * @author Brian
 */
public class Anchor {
	//Describes the oritentation of the structure
	public enum Orientation
	{
		NE(0),
		SE(1),
		SW(2),
		NW(3);
		
		public final int id;
		
		private Orientation(int id) {
			this.id=id;
		}
		
		public static Orientation getOrientation(int id) {
			for(Orientation orientation:Orientation.values()) {
				if (orientation.id==id) {
					return orientation;
				}
			}
			return null;
		}
		
	}
	public final Orientation orientation;
	public final Location location;
	
	public Anchor(Orientation orientation, Location location) {

		this.orientation=orientation;
		this.location=location;
	}
	
	/*
	 * Gets the block located in the structure given the offset
	 */
	public Block getBlock(Offset offset) {
		return offset.orient(orientation).toLocation(location).getBlock();
	}
	
	/*
	 * Gets either a negative or positive increment of X depending on its orientation
	 */
	public int getXIncrement() {
		return orientation==Orientation.NE || orientation==Orientation.NW ? 1 : -1;
	}
	
	/*
	 * Gets either a negative or positive increment of X depending on its orientation
	 */
	public int getZIncrement() {
		return orientation==Orientation.NE || orientation==Orientation.SE ? 1 : -1;
	}
	
	public Location getLocationOfOffset(Offset offset) {
		Offset orientedOffset = offset.orient(orientation);
		return location.clone().add(orientedOffset.x, orientedOffset.y, orientedOffset.z);
	}
}
