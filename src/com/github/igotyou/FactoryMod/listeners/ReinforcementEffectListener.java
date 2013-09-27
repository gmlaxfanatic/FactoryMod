package com.github.igotyou.FactoryMod.listeners;

import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea;
import com.untamedears.citadel.events.CreateReinforcementEvent;

import com.github.igotyou.FactoryMod.managers.FactoryModManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReinforcementEffectListener implements Listener {

	private FactoryModManager factoryManager;
	private ReinforcementArea reinforcementArea;

	public ReinforcementEffectListener(FactoryModManager factoryManager, ReinforcementArea reinforcementArea) {
		this.factoryManager = factoryManager;
		this.reinforcementArea = reinforcementArea;
	}

	@EventHandler
	public void onCreateReinforcementEvent(CreateReinforcementEvent e) {
		if (reinforcementArea.isAffected(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
