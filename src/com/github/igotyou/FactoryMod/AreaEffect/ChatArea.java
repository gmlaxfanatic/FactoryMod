package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;

public class ChatArea {

	private static Map<Factory, Set<Player>> playersByFactory = new HashMap<Factory, Set<Player>>();
	private static Set<Player> affectedPlayers = new HashSet<Player>();
	private static int chatIndex = 0;

	public ChatArea() {
	}

	/*
	 * Reapplies the affect onto the affected players
	 */
	public void updatePlayers() {
		for (Factory factory : playersByFactory.keySet()) {
			List<String> message = ((AreaFactory) factory).getChatEffectData();
			for (Player player : playersByFactory.get(factory)) {
				player.sendMessage(ChatPaginator.wordWrap(message.get(chatIndex % message.size()), ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH));
			}
		}
		chatIndex++;
	}

	/*
	 * 
	 */
	
	public class ChatAreaEffect implements AreaEffect {

		private int radius;

		public ChatAreaEffect(int radius) {
			this.radius = radius;
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
		 * Couples a factory with a list of affected players
		 */
		public void apply(Factory factory, Set<Player> players) {
			playersByFactory.put(factory, players);
			affectedPlayers.addAll(players);
		}

		/*
		 * Checks if a player is affected by this areaEffect
		 */
		public boolean isAffected(Player player) {
			return affectedPlayers.contains(player);
		}

		/*
		 * Updates the affected players from the factories reference
		 */
		private void updateAffectedPlayers() {
			for (Factory factory : playersByFactory.keySet()) {
				affectedPlayers.addAll(playersByFactory.get(factory));
			}
		}
	}
}
