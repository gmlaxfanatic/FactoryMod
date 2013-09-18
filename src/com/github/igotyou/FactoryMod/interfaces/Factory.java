/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
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
	
	public Orientation getOrientation();

	public boolean exists();
	
	public boolean isWhole();
	
	public void interactionResponse(Player player, Location location);
	
	public BaseFactory.FactoryCategory getFactoryCategory();
	
	public void blockBreakResponse();
	
	public abstract void update();
}
