package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ReinforcementEffect implements AreaEffect{
	private static Map<Factory,Set<Player>> playersByFactory = new HashMap<Factory,Set<Player>>();
	private static Set<Player> affectedPlayers = new HashSet<Player>();
	private int radius;
	
	public ReinforcementEffect(int radius) {
		this.radius = radius;
	}
	
	/*
	 * Couples a factory with a list of affected players
	 */
	public static void apply(Factory factory, Set<Player> players) {
		playersByFactory.put(factory, players);
		affectedPlayers.addAll(players);
	}
	
	/*
	 * Checks if a player is affected by this areaEffect
	 */
	public static boolean isAffected(Player player) {
		return affectedPlayers.contains(player);
	}
	
	/*
	 * Get the readius of this affect
	 */
	@Override
	public int getRadius() {
		return radius;
	}
	
	/*
	 * Disables the effects of a given factory
	 */
	@Override
	public void disable(Factory factory) {
		playersByFactory.remove(factory);
		updateAffectedPlayers();
	}
	
	/*
	 * Updates the affected players from the factories reference
	 */
	private void updateAffectedPlayers() {
		for(Factory factory:playersByFactory.keySet()) {
			affectedPlayers.addAll(playersByFactory.get(factory));
		}
	}
}
