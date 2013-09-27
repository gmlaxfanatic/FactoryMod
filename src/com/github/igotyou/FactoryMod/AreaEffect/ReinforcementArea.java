package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;

public class ReinforcementArea {

	private static Map<Factory, Set<Player>> playersByFactory = new HashMap<Factory, Set<Player>>();
	private static Set<Player> affectedPlayers = new HashSet<Player>();

	public ReinforcementArea() {
	}

	public class ReinforcementAreaEffect implements AreaEffect {

		private int radius;

		public ReinforcementAreaEffect(int radius) {
			this.radius = radius;
		}
		/*
		 * Couples a factory with a list of affected players
		 */

		public void apply(Factory factory, Set<Player> players) {
			playersByFactory.put(factory, players);
			affectedPlayers.addAll(players);
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
	}

	/*
	 * Updates the affected players from the factories reference
	 */
	private void updateAffectedPlayers() {
		for (Factory factory : playersByFactory.keySet()) {
			affectedPlayers.addAll(playersByFactory.get(factory));
		}
	}

	/*
	 * Checks if a player is affected by this areaEffect
	 */
	public boolean isAffected(Player player) {
		return affectedPlayers.contains(player);
	}
}
