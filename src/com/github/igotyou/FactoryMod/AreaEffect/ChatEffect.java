package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.Factorys.SimpleFactory;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;


public class ChatEffect implements AreaEffect{
	private static Map<Factory,Set<Player>> playersByFactory = new HashMap<Factory,Set<Player>>();
	private static Set<Player> affectedPlayers = new HashSet<Player>();
	private static int chatIndex=0;
	private int radius;
	
	
	public ChatEffect(int radius) {
		this.radius = radius;
	}
	
	/*
	 * Couples a factory with a list of affected players
	 */
	public static void apply(SimpleFactory factory, Set<Player> players) {
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
	 * Reapplies the affect onto the affected players
	 */
	public static void updatePlayers() {
		for(Factory factory:playersByFactory.keySet()) {
			List<String> message=((SimpleFactory) factory).getChatEffectData();
			for(Player player:playersByFactory.get(factory)) {
				player.sendMessage(ChatPaginator.wordWrap(message.get(chatIndex%message.size()), ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH));
			}
		}
		chatIndex++;
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
