package com.github.igotyou.FactoryMod.Factorys;

import static com.untamedears.citadel.Utility.isReinforced;
import static com.untamedears.citadel.Utility.getReinforcement;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.AreaFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Structure;
import com.untamedears.citadel.entity.PlayerReinforcement;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AreaFactory extends BaseFactory {

	private int currentEnergyTime;
	

	public AreaFactory(Anchor anchor,
		Structure structure,
		boolean active,
		FactoryProperties factoryProperties) {
		super(anchor,
			FactoryCategory.AREA,
			factoryProperties);
		this.currentEnergyTime = 0;
	}

	public AreaFactory(Anchor anchor,
		Structure structure,
		FactoryProperties factoryProperties,
		int currentEnergyTime) {
		super(anchor,
			FactoryCategory.AREA,
			factoryProperties);
		this.currentEnergyTime = currentEnergyTime;
	}
	
	@Override
	public void update() {
		if (currentEnergyTime >= getEnergyTime()) {
			if (consumeFuel()) {
				currentEnergyTime = 0;
			} else {
				powerOff();
				return;
			}
		}
		updateEffects();
		currentEnergyTime += FactoryModPlugin.getManager().getManager(factoryCategory).getUpdatePeriod();
	}
	
	/*
	 * Power off the AreaFactory, completely deleting it
	 */
	private void powerOff() {
		for(AreaEffect areaEffect:getProperties().getAllAreaEffects()) {
			areaEffect.disable(this);
		}
		FactoryModPlugin.getManager().getManager(factoryCategory).removeFactory(this);
	}

	/*
	 * Returns the time one unit of fuel lasts in ticks
	 */
	public int getEnergyTime() {
		return getProperties().getEnergyTime();
	}

	/*
	 * Attempts to consume fuel from the powerSource
	 */
	public boolean consumeFuel() {
		Inventory powerSourceInventory = getPowerSourceInventory();
		ItemList<NamedItemStack> fuel = getProperties().getFuel();
		if(fuel.allIn(powerSourceInventory)) {
			fuel.removeFrom(powerSourceInventory);
			return true;
		}
		else {
			return false;
		}

	}
	
	/*
	 * Gets the inventory of the powersource used for this factory
	 */
	private Inventory getPowerSourceInventory() {
		return ((InventoryHolder)anchor.getLocationOfOffset(getProperties().getPowerSourceOffset()).getBlock().getState()).getInventory();
	}
	
	/*
	 * Gets a correctly caste properties file
	 */
	private AreaFactoryProperties getProperties() {
		return ((AreaFactoryProperties)factoryProperties);
	}
	
	/*
	 * Updates the effects and palyers effected
	 */
	public void updateEffects() {
		Map<Integer,Set<AreaEffect>> areaEffects = getProperties().getAreaEffects();
		for(Integer radius:areaEffects.keySet()){
			//Replicates Mojang implementation of Beacons, unsure of the requirement of -2 and +2
			int xMin = anchor.location.getBlockX()-2-radius;
			int xMax = anchor.location.getBlockX()+2+radius;
			int zMin = anchor.location.getBlockZ()-2-radius;
			int zMax = anchor.location.getBlockZ()+2+radius;
			for(int x=xMin;x<=xMax;x+=16) {
				for(int z=zMin;z<=zMax;z+=16) {
					for(Entity entity:anchor.location.getWorld().getChunkAt(x, z).getEntities()) {
						if(entity instanceof Player) {
							if(xMin<entity.getLocation().getBlockX()&&xMax>entity.getLocation().getBlockX()&&zMin<entity.getLocation().getBlockZ()&&zMax>entity.getLocation().getBlockZ()){
								if((!FactoryModPlugin.CITADEL_ENABLED || FactoryModPlugin.CITADEL_ENABLED && !isReinforced(anchor.location)) || 
										(((PlayerReinforcement) getReinforcement(anchor.location)).isAccessible((Player)entity))){
									for(AreaEffect areaEffect:areaEffects.get(radius)) { 
										updateAreaEffect(areaEffect);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * Updates the Effect depending on what class it is
	 */
	private void updateAreaEffect(AreaEffect areaEffect) {
		
	}
	
	@Override
	public void blockBreakResponse() {
		powerOff();
	}
}
