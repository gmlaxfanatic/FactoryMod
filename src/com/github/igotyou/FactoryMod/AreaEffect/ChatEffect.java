package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;


public class ChatEffect implements AreaEffect{
	private static int chatIndex=0;
	private static Set<Player> affectedPlayers = new HashSet<Player>(50);
	private int radius;
	
	public ChatEffect(int radius) {
		this.radius = radius;
	}
	
	@Override
	public int getRadius() {
		return radius;
	}
	
	public void updateEffect() {
		affectedPlayers.clear();
	}
	
	@Override
	public void apply(Player player) {
		affectedPlayers.add(player);
	}
	
	public void executeEffect(String chat) {
		for(Player player:affectedPlayers) {
			InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResult.CHAT,chat));
		}
	}
	
	
}
