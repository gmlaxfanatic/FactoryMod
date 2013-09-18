/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.interfaces;

import org.bukkit.entity.Player;


public interface AreaEffect {
	
	/*
	 * Applies the given effect to a player
	 */
	public void apply(Player player);
	/*
	 * Gets the radius of the effect
	 */
	public int getRadius();
}
