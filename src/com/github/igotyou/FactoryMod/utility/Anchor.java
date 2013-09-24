/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.utility;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 * Represents a location with a orientation from which offsets
 * can be calculated to achieve absolute positions
 */
public class Anchor implements Serializable{
	//Describes the oritentation of the structure
	public enum Orientation
	{
		NE(0),
		SE(1),
		SW(2),
		NW(3);
		
		public final int id;
		public static final Orientation[] byId = {Orientation.NE,Orientation.SE,Orientation.SW,Orientation.NW};
		
		private Orientation(int id) {
			this.id=id;
		}
		
		public static Orientation getOrientation(int id) {
			return id<byId.length ? byId[id] : null;
		}		
	}
	
	public final Orientation orientation;
	public final int x;
	public final int y;
	public final int z;
	public final String worldName;
	private transient Location location;
	
	
	public Anchor(Orientation orientation, Location location) {

		this.orientation=orientation;
		x=location.getBlockX();
		y=location.getBlockY();
		z=location.getBlockZ();
		worldName=location.getWorld().getName();
	}
	
	/*
	 * gets the location of the objest
	 */
	public Location getLocation() {
		if(location==null) {
			location = new Location(Bukkit.getServer().getWorld(worldName),x,y,z);
		}
		return location;
	}
	
	/*
	 * Gets either a negative or positive increment of X depending on its orientation
	 */
	public int getXModifier() {
		return orientation==Orientation.NE || orientation==Orientation.NW ? 1 : -1;
	}
	
	/*
	 * Gets either a negative or positive increment of X depending on its orientation
	 */
	public int getZModifier() {
		return orientation==Orientation.NE || orientation==Orientation.SE ? 1 : -1;
	}
	
	/*
	 * Returns an location the offset given this anchor
	 */
	public Location getLocationOfOffset(Offset offset) {
		Offset orientedOffset = offset.orient(orientation);
		return location.clone().add(orientedOffset.x, orientedOffset.y, orientedOffset.z);
	}

	/*
	 * Gets the block located in the world given the offset and this anchor
	 */
	public Block getBlock(Offset offset) {
		return getLocationOfOffset(offset).getBlock();
	}
		
	/*
	 * Check if location is contained within a bounding box at this anchor
	 */
	public boolean containedIn(Location testLocation, int[] dimensions) {
		if((testLocation.getBlockX()-location.getBlockX())<dimensions[0]*getXModifier()) {
			if((testLocation.getBlockZ()-location.getBlockZ())<dimensions[2]*getZModifier()) {
				if(0<=(testLocation.getBlockZ()-location.getBlockZ()) && (testLocation.getBlockZ()-location.getBlockZ())<dimensions[1]) {
					return true;
				}
			}
		}
		return false;
	}
}
