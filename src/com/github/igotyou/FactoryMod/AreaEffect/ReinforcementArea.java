package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.listeners.ReinforcementEffectListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ReinforcementArea extends Area {

	public ReinforcementArea(Plugin plugin) {
		try {
			Bukkit.getServer().getPluginManager().registerEvents(new ReinforcementEffectListener(this), plugin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ReinforcementAreaEffect extends AreaEffect {

		public ReinforcementAreaEffect(int radius) {
			super(radius);
		}
	}
}
