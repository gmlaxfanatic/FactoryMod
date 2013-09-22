/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.Factorys.SimpleFactory;
import org.bukkit.entity.Player;


public interface AreaEffect {
	
	/*
	 * Gets the radius of the effect
	 */
	public int getRadius();
	/*
	 * Removes the record for the factory
	 */
	public void disable(Factory factory);
}
