/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.AreaEffect.ChatArea;
import com.github.igotyou.FactoryMod.AreaEffect.ChatArea.ChatAreaEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementArea.ReinforcementAreaEffect;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.AreaFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.properties.ContinousFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import org.bukkit.configuration.ConfigurationSection;

/**
 *
 * @author Brian
 */
public class EffectFactoryManager extends ContinousFactoryManager {

	protected int areaEffectUpdatePeriod;
	protected ChatArea chatArea;
	protected ReinforcementArea reinforcementArea;

	public EffectFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		chatArea = new ChatArea();
		reinforcementArea = new ReinforcementArea();
	}
	
	/*
	 * Gets the chat area effect
	 */
	public ChatAreaEffect getChatAreaEffect(int radius) {
		return chatArea.new ChatAreaEffect(radius);
	}
	
	/*
	 * Gets a reinforcement area effect
	 */
	public ReinforcementAreaEffect getReinforcementAreaEffect(int radius) {
		return reinforcementArea.new ReinforcementAreaEffect(radius);
	}
	
	/*
	 * Gets an AreaEffect Factory
	 */
	public Factory getFactory(Anchor anchor, ContinousFactoryProperties properties){
		return new AreaFactory(anchor, properties.getName());
	}
	
}
