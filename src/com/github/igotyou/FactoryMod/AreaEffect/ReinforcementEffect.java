package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ReinforcementEffect implements AreaEffect{
	private static Set<Player> affectedPlayers = new HashSet<Player>(50);
	private int radius;
	
	public ReinforcementEffect(int radius) {
		this.radius = radius;
	}
	
	public void updateEffect() {
		affectedPlayers.clear();
	}
	
	@Override
	public void apply(Player player) {
		affectedPlayers.add(player);
	}
	
	@Override
	public int getRadius() {
		return radius;
	}
	
	public static void executeEffect(Player player) {
		if(affectedPlayers.contains(player)) {
			//TODO: Cancel reinforcement placement
		}
	}
}
