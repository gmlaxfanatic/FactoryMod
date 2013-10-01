package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.AreaEffect.Area;
import com.github.igotyou.FactoryMod.AreaEffect.ChatArea;
import com.github.igotyou.FactoryMod.AreaEffect.ChatArea.ChatAreaEffect;
import com.github.igotyou.FactoryMod.AreaEffect.PotionArea;
import com.github.igotyou.FactoryMod.AreaEffect.PotionArea.PotionAreaEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea.ReinforcementAreaEffect;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.properties.ContinuousFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;

public class AreaFactoryManager extends ContinuousFactoryManager {

	protected int areaEffectUpdatePeriod;
	protected ChatArea chatArea;
	protected ReinforcementArea reinforcementArea;
	protected PotionArea potionArea;
	protected Set<Area> areas=new HashSet(3);

	public AreaFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		chatArea = new ChatArea(plugin,configurationSection.getInt("chat_area_period"));
		areas.add(chatArea);
		reinforcementArea = new ReinforcementArea(plugin);
		areas.add(reinforcementArea);
		potionArea = new PotionArea(plugin,updatePeriod);
		areas.add(potionArea);
	}
	public void runUpdates() {
		for (Factory factory : factories) {
			factory.update();
		}
		for(Area area:areas) {
			area.updateAffectedPlayers();
		}
		
	}
	
	/*
	 * Gets the chat area effect
	 */
	public ChatAreaEffect getChatAreaEffect(int radius, AreaFactory areaFactory) {
		return chatArea.new ChatAreaEffect(radius, areaFactory);
	}

	/*
	 * Gets a reinforcement area effect
	 */
	public ReinforcementAreaEffect getReinforcementAreaEffect(int radius) {
		return reinforcementArea.new ReinforcementAreaEffect(radius);
	}
	/*
	 * Gets a PotionAreaEffect
	 */
	public PotionAreaEffect getPotionAreaEffect(int radius, PotionEffect potionEffect) {
		return potionArea.new PotionAreaEffect(radius, potionEffect);
	}

	/*
	 * Gets an AreaEffect Factory
	 */
	@Override
	public AreaFactory getFactory(Anchor anchor, ContinuousFactoryProperties properties) {
		return new AreaFactory(anchor, properties.getName());
	}
}
