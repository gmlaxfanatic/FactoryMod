package com.github.igotyou.FactoryMod.interfaces;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import java.util.List;

public interface ItemFactoryInterface extends Factory
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