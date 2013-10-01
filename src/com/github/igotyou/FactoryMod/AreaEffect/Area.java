package com.github.igotyou.FactoryMod.AreaEffect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;

public class Area {

	protected Map<AreaEffect, Set<Player>> playersByEffect = new HashMap<AreaEffect, Set<Player>>();
	protected Set<Player> affectedPlayers = new HashSet<Player>();

	public Area() {
	}

	/*
	 * Updates the affected players from the factories reference
	 */
	public void updateAffectedPlayers() {
		for (Set<Player> players : playersByEffect.values()) {
			affectedPlayers.addAll(players);
		}
	}

	/*
	 * Reapplies the affect onto the affected players
	 */
	public void updateEffects() {
	}

	/*
	 * Checks if a player is affected by this areaEffect
	 */
	public boolean isAffected(Player player) {
		return affectedPlayers.contains(player);
	}

	public class AreaEffect {

		protected final int radius;

		public AreaEffect(int radius) {
			this.radius = radius;
		}

		/*
		 * Gets the radius of the effect
		 */
		public int getRadius() {
			return radius;
		}
		/*
		 * Applies the effect to a set of players
		 */

		public void apply(Set<Player> players) {
			playersByEffect.put(this, players);
			affectedPlayers.addAll(players);
		}
		
		/*
		 * Removes this effect from the players
		 */
		public void disable(){
			playersByEffect.remove(this);
			updateAffectedPlayers();
		}
	}
}
