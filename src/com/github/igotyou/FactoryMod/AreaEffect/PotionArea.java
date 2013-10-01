package com.github.igotyou.FactoryMod.AreaEffect;

import org.bukkit.Bukkit;
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
