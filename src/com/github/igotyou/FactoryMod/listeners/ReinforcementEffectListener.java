package com.github.igotyou.FactoryMod.listeners;

import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea;
import com.untamedears.citadel.events.CreateReinforcementEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReinforcementEffectListener implements Listener {

	private ReinforcementArea reinforcementArea;

	public ReinforcementEffectListener(ReinforcementArea reinforcementArea) {
		this.reinforcementArea = reinforcementArea;
	}

	@EventHandler
	public void onCreateReinforcementEvent(CreateReinforcementEvent e) {
		if (reinforcementArea.isAffected(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
