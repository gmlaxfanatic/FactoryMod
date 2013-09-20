package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.Arrays;
import java.util.List;


public class AreaFactory extends BaseFactory{
	private int currentFuelTime;
	
	public AreaFactory(Anchor anchor,
		Structure structure,
		boolean active,
		FactoryCategory factoryCategory,
		FactoryProperties factoryProperties) {
		super(anchor,
			factoryCategory,
			factoryProperties);
		this.currentFuelTime=0;
	}
	
	public AreaFactory(Anchor anchor,
		Structure structure,
		FactoryCategory factoryCategory,
		FactoryProperties factoryProperties,
		int currentFuelTime) {
		super(anchor,
			factoryCategory,
			factoryProperties);
		this.currentFuelTime=currentFuelTime;
	}
	
	public void update() {
		
	}
}
