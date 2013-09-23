package com.github.igotyou.FactoryMod.listeners;

import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementEffect;
import com.untamedears.citadel.events.CreateReinforcementEvent;

import com.github.igotyou.FactoryMod.managers.FactoryModManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReinforcementEffectListener implements Listener {

	private FactoryModManager factoryManager;

	public ReinforcementEffectListener(FactoryModManager factoryManager) {
		this.factoryManager = factoryManager;
	}

	@EventHandler
	public void onCreateReinforcementEvent(CreateReinforcementEvent e) {
		if(ReinforcementEffect.isAffected(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
