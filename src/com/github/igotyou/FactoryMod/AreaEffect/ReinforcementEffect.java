package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ReinforcementEffect implements AreaEffect{
	private static Map<Factory,Set<Player>> affectedPlayers = new HashMap<Factory,Set<Player>>();
	private int radius;
	
	public ReinforcementEffect(int radius) {
		this.radius = radius;
	}
	
	public void updateEffect() {
		affectedPlayers.clear();
	}
	
	public void apply(Factory factory, Set<Player> players) {
		affectedPlayers.put(factory, players);
	}
	
	@Override
	public int getRadius() {
		return radius;
	}
	
	public void disable(Factory factory) {
		affectedPlayers.remove(factory);
	}
	
	/*
	 * Imports the configuration for this effect
	 */	
	public static void initialize(ConfigurationSection configurationSection) {
		getServer().getPluginManager().registerEvents(new ReinforcementListener(this), FactoryModPlugin.);
	}
}
