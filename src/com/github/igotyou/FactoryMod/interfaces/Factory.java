/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.utility.Structure;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Brian
 */
public interface Factory {
		
	public Location getLocation();
	
	public Structure getStructure();
	
	public Structure.Orientation getOrientation();

	public boolean exists();
	
	public boolean isWhole();
	
	public int getInteractionPointIndex(Location location);
	
	public void interactionResponse(Player player, int i);
}
