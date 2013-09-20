/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ItemFactory;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.Iterator;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Superclass for specific ItemFactory managers to extend
 */

public abstract class ItemFactoryManager extends BaseFactoryManager{
	
	
	public ItemFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection)
	{
		super(plugin, configurationSection);
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
	public void updateRepair(long time)
	{
		for (Factory itemFactory: factories)
		{
			((ItemFactory) itemFactory).updateRepair(time/((double)FactoryModPlugin.REPAIR_PERIOD));
		}
		long currentTime=System.currentTimeMillis();
		Iterator<Factory> itr=factories.iterator();
		while(itr.hasNext())
		{
			ItemFactory itemFactory = (ItemFactory) itr.next();
			if(currentTime>(itemFactory.getTimeDisrepair()+FactoryModPlugin.DISREPAIR_PERIOD))
			{
				itr.remove();
			}
		}
	}
}
