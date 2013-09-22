package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.AreaEffect.AreaPotionEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ChatEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementEffect;
import static com.untamedears.citadel.Utility.isReinforced;
import static com.untamedears.citadel.Utility.getReinforcement;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.SimpleFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.untamedears.citadel.entity.PlayerReinforcement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SimpleFactory extends BaseFactory {

	private int currentEnergyTime;
	private int currentProductionTime;

	public SimpleFactory(Anchor anchor, SimpleFactoryProperties simpleFactoryProperties) {
		this(anchor, simpleFactoryProperties, simpleFactoryProperties.getEnergyTime(), 0);
	}

	public SimpleFactory(Anchor anchor, FactoryProperties factoryProperties, int currentEnergyTime, int currenProductionTime) {
		super(anchor, FactoryCategory.AREA, factoryProperties);
		this.currentEnergyTime = currentEnergyTime;
		this.currentProductionTime = currentProductionTime;
	}

	@Override
	public void update() {
		if (!isWhole()) {
			powerOff();
			return;
		}
		if (!updateEnergy()) {
			powerOff();
			return;
		}
		updateProduction();
		updateEffects();
		currentEnergyTime += FactoryModPlugin.getManager().getManager(factoryCategory).getUpdatePeriod();
	}
	/*
	 * Updates the energy state of the factory, returns false if fuel is needed
	 * and is not present
	 */

	protected boolean updateEnergy() {
		if (currentEnergyTime >= getEnergyTime()) {
			if (consumeFuel()) {
				currentEnergyTime = 0;
			} else {
				return false;
			}
		}
		return true;
	}

	/*
	 * Attempts to consume fuel from the powerSource
	 */
	protected boolean consumeFuel() {
		Inventory powerSourceInventory = getPowerSourceInventory();
		ItemList<NamedItemStack> fuel = getProperties().getFuel();
		if (fuel.allIn(powerSourceInventory)) {
			fuel.removeFrom(powerSourceInventory);
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Updates the production state of the factory
	 */
	protected void updateProduction() {
		if (currentProductionTime >= getProductionTime()) {
			currentProductionTime = 0;
			generateProducts();
		}
	}

	/*
	 * Power off the SimpleFactory, completely deleting it
	 */
	protected void powerOff() {
		for (AreaEffect areaEffect : getProperties().getAllAreaEffects()) {
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
	 * Returns the time between proudction in ticks
	 */
	public int getProductionTime() {
		return getProperties().getProductionTime();
	}

	/*
	 * Gets the inventory of the powersource used for this factory
	 */
	private Inventory getPowerSourceInventory() {
		return ((InventoryHolder) anchor.getLocationOfOffset(getProperties().getInventory()).getBlock().getState()).getInventory();
	}

	/*
	 * Gets a correctly caste properties file
	 */
	protected SimpleFactoryProperties getProperties() {
		return ((SimpleFactoryProperties) factoryProperties);
	}

	/*
	 * Updates the effects and players effected
	 */
	public void updateEffects() {
		Map<Integer, Set<AreaEffect>> areaEffects = getProperties().getAreaEffects();
		for (Integer radius : areaEffects.keySet()) {
			Set<Player> players = new HashSet<Player>();
			//Replicates Mojang implementation of Beacons, unsure of the requirement of -2 and +2
			int xMin = anchor.location.getBlockX() - 2 - radius;
			int xMax = anchor.location.getBlockX() + 2 + radius;
			int zMin = anchor.location.getBlockZ() - 2 - radius;
			int zMax = anchor.location.getBlockZ() + 2 + radius;
			for (int x = xMin; x <= xMax; x += 16) {
				for (int z = zMin; z <= zMax; z += 16) {
					for (Entity entity : anchor.location.getWorld().getChunkAt(x, z).getEntities()) {
						if (entity instanceof Player) {
							Player player = (Player) entity;
							if (xMin < player.getLocation().getBlockX() && xMax > player.getLocation().getBlockX() && zMin < player.getLocation().getBlockZ() && zMax > player.getLocation().getBlockZ()) {
								if ((!FactoryModPlugin.CITADEL_ENABLED || FactoryModPlugin.CITADEL_ENABLED && !isReinforced(anchor.location))
									|| (((PlayerReinforcement) getReinforcement(anchor.location)).isAccessible(player))) {
									players.add(player);
								}
							}
						}
					}
				}
			}
			for (AreaEffect areaEffect : areaEffects.get(radius)) {
				updateAreaEffect(areaEffect,players);
			}
		}
	}
	/*
	 * Generates the outputs produced by this factory
	 */

	protected void generateProducts() {
	}
	/*
	 * Updates the Effect depending on what class it is
	 */

	private void updateAreaEffect(AreaEffect areaEffect, Set<Player> players) {
		if(areaEffect instanceof ReinforcementEffect) {
			ReinforcementEffect.apply(this, players);
		}
		else if(areaEffect instanceof AreaPotionEffect) {
			AreaPotionEffect.apply(this, players);
		}
		else if(areaEffect instanceof ChatEffect) {
			ChatEffect.apply(this, players);
		}
		
	}
	
	/*
	 * Get data for the chat effect
	 */
	public List<String> getChatEffectData() {
		return null;
	}
	
	@Override
	public void blockBreakResponse() {
		powerOff();
	}

	/*
	 * Changes the power indicator block to indicate the factory is on
	 */
	public void indicatePowerOn() {
	}

	/*
	 * Changes the power indicator block to indicate the factory is off
	 */
	public void indicatePowerOff() {
	}
}
