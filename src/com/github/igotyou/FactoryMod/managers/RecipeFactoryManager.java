package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.RecipeFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.Iterator;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Superclass for specific RecipeFactory managers to extend
 */
public abstract class RecipeFactoryManager extends BaseFactoryManager {

	protected long repairTime;
	protected final long disrepairPeriod;
	protected final long repairPeriod;

	public RecipeFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		//Period of days before a factory is removed after it falls into disrepair
		disrepairPeriod = configurationSection.getLong("disrepair_period", 14) * 24 * 60 * 60 * 1000;
		//The length of time it takes a factory to go to 0% health
		repairPeriod = configurationSection.getLong("repair_period", 28) * 24 * 60 * 60 * 1000;
	}

	/*
	 * Updates the current state of the repair of the factories given that
	 * the amount of time "time" has passed. This is currently called with
	 * each save cycle.
	 * If any factories have exceeded their total time in disrepair they 
	 * are permenantly removed.
	 * Note that repair degradation takes into account time the server is 
	 * operational, where as the time in disrepair to removal is solely based
	 * on real world clocks.
	 */
	public void updateRepair(long time) {
		for (Factory recipeFactory : factories) {
			((RecipeFactory) recipeFactory).updateRepair(time / ((double) repairPeriod));
		}
		long currentTime = System.currentTimeMillis();
		Iterator<Factory> itr = factories.iterator();
		while (itr.hasNext()) {
			RecipeFactory recipeFactory = (RecipeFactory) itr.next();
			if (currentTime > (recipeFactory.getTimeDisrepair() + disrepairPeriod)) {
				itr.remove();
			}
		}
	}

	public void update() {
		//Takes difference between last repair update and current one and scales repair accordingly
		updateRepair(System.currentTimeMillis() - repairTime);
		repairTime = System.currentTimeMillis();
		save();
	}

	/*
	 * Updates repair and saves the manager
	 */
	public void onDisable() {
		//Takes difference between last repair update and current one and scales repair accordingly
		updateRepair(System.currentTimeMillis() - repairTime);
		save();
	}
}
