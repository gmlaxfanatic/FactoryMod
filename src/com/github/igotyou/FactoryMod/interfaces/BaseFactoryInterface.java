package com.github.igotyou.FactoryMod.interfaces;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import org.bukkit.entity.Player;
//original file:
/**
 * Machine.java
 * Purpose: An interface for machines to implement with basic functionality
 *
 * @author MrTwiggy
 * @version 0.1 1/14/13
 */
//edited version:
/**
* BaseFactoryInterface.java	 
* Purpose: An interface for factorys to implement with basic functionality
* @author igotyou
*/
public interface BaseFactoryInterface extends Factory
{
	/**
	 * Updates the machine
	 */
	public void update();
	
	/**
	 * Powers on the machine
	 */
	public void powerOn();
	
	/**
	 * Powers off the machine
	 */
	public void powerOff();
	
	/**
	 * Toggles the current power state and returns interaction response
	 */
	public List<InteractionResponse> togglePower();
	public List<InteractionResponse> getChestResponse();
	public List<InteractionResponse> getCentralBlockResponse();
	
	/**
	 * Returns the location of the machine
	 */
	public Location getCenterLocation();
	
	public Location getInventoryLocation();
	
	public Location getPowerSourceLocation();
}