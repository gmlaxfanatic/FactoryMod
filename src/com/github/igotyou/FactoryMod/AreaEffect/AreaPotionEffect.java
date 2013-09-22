
package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class AreaPotionEffect extends PotionEffect implements AreaEffect{
	private final int radius;
	private static Map<Factory,Set<Player>> playersByFactory = new HashMap<Factory,Set<Player>>();
	private static Set<Player> affectedPlayers = new HashSet<Player>();
	
	public AreaPotionEffect (int radius, PotionEffectType type, int duration, int amplifier, boolean ambient) {
		super(type,duration,amplifier,ambient);
		this.radius=radius;
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
	 * Reapplies the affect onto the affected players
	 */
	public static void updatePlayers() {
		
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
		
	public static Set<AreaPotionEffect> areaEffectsFromConfig(ConfigurationSection configurationSection) {
		Set<AreaPotionEffect> areaEffects = new HashSet<AreaPotionEffect>();
		if(configurationSection!=null)
		{
			for(String areaEffectName:configurationSection.getKeys(false))
			{
				areaEffects.add(AreaPotionEffect.fromConfig(configurationSection.getConfigurationSection(areaEffectName)));
			}
		}
		return areaEffects;
	}
	
	public static AreaPotionEffect fromConfig(ConfigurationSection configurationSection) {
		PotionEffect potionEffect=potionEffectFromConfig(configurationSection);
		int radius = configurationSection.getInt("radius", 1);
		return new AreaPotionEffect(radius,potionEffect.getType(),potionEffect.getDuration(),potionEffect.getAmplifier(),potionEffect.isAmbient());
	}
	public static PotionEffect potionEffectFromConfig(ConfigurationSection configurationSection) {
		PotionEffectType effectType = PotionEffectType.getByName(configurationSection.getString("PotionEffect1"));
		int duration = configurationSection.getInt("duration");
		int amplifier = configurationSection.getInt("amplifier");
		boolean ambient = configurationSection.getBoolean("ambient");
		return new PotionEffect(effectType,duration,amplifier,ambient);
	}
}
