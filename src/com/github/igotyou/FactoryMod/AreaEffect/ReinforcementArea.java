package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.untamedears.citadel.events.CreateReinforcementEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ReinforcementArea extends Area {

	public ReinforcementArea(Plugin plugin) {
		try {
			Bukkit.getServer().getPluginManager().registerEvents(new ReinforcementEffectListener(), plugin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Imports a Reinforcement AreaEffect from the configuration
	 */
	public ReinforcementAreaEffect fromConfig(ConfigurationSection configurationSection, AreaFactory areaFactory) {
		return new ReinforcementAreaEffect(super.fromConfig(configurationSection, areaFactory).getRadius());

	}

	public class ReinforcementAreaEffect extends AreaEffect {

		public ReinforcementAreaEffect(int radius) {
			super(radius);
		}
	}

	/*
	 * Listens for reinforcement events and cancels them if the player is currently affected
	 */
	public class ReinforcementEffectListener implements Listener {

		public ReinforcementEffectListener() {
		}

		@EventHandler
		public void onCreateReinforcementEvent(CreateReinforcementEvent e) {
			if (isAffected(e.getPlayer())) {
				e.setCancelled(true);
			}
		}
	}
}
