package com.github.igotyou.FactoryMod.interfaces;

import java.util.Set;
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
	/*
	 * Applys the effect to the player from the given factory
	 */
	public void apply(Factory factory, Set<Player> players);
}
