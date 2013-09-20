
package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class AreaPotionEffect extends PotionEffect implements AreaEffect{
	private final int radius;
	
	public AreaPotionEffect (PotionEffectType type, int duration, int amplifier, boolean ambient) {
		super(type,duration,amplifier,ambient);
		this.radius=1;
	}
	public AreaPotionEffect (int radius, PotionEffectType type, int duration, int amplifier, boolean ambient) {
		super(type,duration,amplifier,ambient);
		this.radius=radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void apply(Player player) {
		this.apply(player);
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
