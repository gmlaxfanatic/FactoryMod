package com.github.igotyou.FactoryMod.AreaEffect;

import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.ChatPaginator;

public class ChatArea extends Area {

	private int chatIndex = 0;

	public ChatArea(Plugin plugin, int updatePeriodInTicks) {
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
			List<String> message = ((ChatAreaEffect) areaEffect).getMessage();
			for (Player player : playersByEffect.get(areaEffect)) {
				player.sendMessage(ChatPaginator.wordWrap(message.get(chatIndex % message.size()), ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH));
			}
		}
		chatIndex++;
	}

	/*
	 * Imports a Reinforcement AreaEffect from the configuration
	 */
	@Override
	public AreaEffect fromConfig(ConfigurationSection configurationSection, AreaFactory areaFactory) {
		AreaEffect areaEffect = super.fromConfig(configurationSection, areaFactory);
		return new ChatAreaEffect(areaEffect.getRadius(), areaFactory);

	}
	
	/*
	 * A chat effect object
	 */
	public class ChatAreaEffect extends AreaEffect {

		protected final AreaFactory areaFactory;

		public ChatAreaEffect(int radius, AreaFactory areaFactory) {
			super(radius);
			this.areaFactory = areaFactory;
		}

		public List<String> getMessage() {
			return areaFactory.getChatEffectData();
		}
	}
}
