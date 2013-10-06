package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.Factorys.BaseFactory.FactoryCategory;
import com.github.igotyou.FactoryMod.properties.TerritoryFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;

public class TerritoryFactory extends ContinuousFactory {

	protected int currentProductionTime;

	public TerritoryFactory(Anchor anchor, String factoryType) {
		super(anchor, factoryType, FactoryCategory.TERRITORIAL);
		currentProductionTime = 0;
	}

	public TerritoryFactory(Anchor anchor, String factoryType, int currentEnergyTime) {
		super(anchor, factoryType, FactoryCategory.TERRITORIAL, currentEnergyTime);
		currentProductionTime = 0;
	}

	/*
	 * Updates the production of the factory
	 */
	
	@Override
	public void updateSpecifics() {
		if (currentProductionTime >= getProductionTime()) {
			currentProductionTime = 0;
			getFactoryProperties().getOutputs().putIn(getInventory());
		}
		currentProductionTime++;
	}

	/*
	 * Returns the time between proudction in ticks
	 */
	public int getProductionTime() {
		return getFactoryProperties().getProductionTime();
	}
	
	/*
	 * Gets a correctly caste properties file
	 */
	@Override
	protected TerritoryFactoryProperties getFactoryProperties() {
		return (TerritoryFactoryProperties) super.getFactoryProperties();
	}
}
