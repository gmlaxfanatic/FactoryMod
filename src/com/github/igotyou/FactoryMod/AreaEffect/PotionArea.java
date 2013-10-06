package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class PotionArea extends Area {

	public PotionArea(Plugin plugin, int updatePeriodInTicks) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				updateEffects();
			}
		}, 0L, updatePeriodInTicks);
	}


	/*
	 * Reapplies the affect onto the affected players
	 */
	@Override
	public void updateEffects() {
		for (AreaEffect areaEffect : playersByEffect.keySet()) {
			PotionEffect potionEffect = ((PotionAreaEffect) areaEffect).getPotionEffect();
			for (Player player : playersByEffect.get(areaEffect)) {
				potionEffect.apply(player);
			}
		}
	}

	/*
	 * Imports a Reinforcement AreaEffect from the configuration
	 */
	@Override
	public PotionAreaEffect fromConfig(ConfigurationSection configurationSection, AreaFactory areaFactory) {
		AreaEffect areaEffect =super.fromConfig(configurationSection, areaFactory);
		PotionEffect potionEffect = NamedItemStack.potionEffectFromConfig(configurationSection);
		return new PotionAreaEffect(areaEffect.getRadius(),potionEffect);

	}

	public class PotionAreaEffect extends AreaEffect {

		private final PotionEffect potionEffect;

		public PotionAreaEffect(int radius, PotionEffect potionEffect) {
			super(radius);
			this.potionEffect = potionEffect;
		}

		protected PotionEffect getPotionEffect() {
			return potionEffect;
		}
	}
}
