
package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
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
			for(String areaEffectType:configurationSection.getKeys(false))
			{
				try {
					areaEffects.add(AreaPotionEffect.fromConfig(configurationSection.getConfigurationSection(areaEffectType),areaEffectType));
				}
				catch (IllegalArgumentException e) {
					FactoryModPlugin.sendConsoleMessage("Error importing area effect "+areaEffectType);
				}
			}
		}
		return areaEffects;
	}
	
	public static AreaPotionEffect fromConfig(ConfigurationSection configurationSection, String potionEffectTypeName) throws IllegalArgumentException{
		PotionEffectType potionEffectType = PotionEffectType.getByName(potionEffectTypeName);
		if(configurationSection==null || potionEffectType==null) {
			throw new IllegalArgumentException();
		}
		int radius = configurationSection.getInt("radius");
		int duration = configurationSection.getInt("duration", 60);
		int amplifier = configurationSection.getInt("amplifier", 1);
		boolean ambient = configurationSection.getBoolean("ambient", false);
		return new AreaPotionEffect(radius,potionEffectType,duration,amplifier,ambient);
	}
}
