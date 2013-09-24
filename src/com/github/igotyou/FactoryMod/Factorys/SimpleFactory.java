package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.AreaEffect.AreaPotionEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ChatEffect;
import com.github.igotyou.FactoryMod.AreaEffect.ReinforcementEffect;
import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.SimpleFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.untamedears.citadel.Citadel;
import com.untamedears.citadel.access.AccessDelegate;
import com.untamedears.citadel.entity.Faction;
import com.untamedears.citadel.entity.FactionMember;
import com.untamedears.citadel.entity.IReinforcement;
import com.untamedears.citadel.entity.PlayerReinforcement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SimpleFactory extends BaseFactory {

	private int currentEnergyTime;
	private int currentProductionTime;

	public SimpleFactory(Anchor anchor, String factoryType) {
		this(anchor, factoryType, ((SimpleFactoryProperties) FactoryModPlugin.getManager().getManager(FactoryCategory.AREA).getProperties(factoryType)).getEnergyTime(), 0);
	}

	public SimpleFactory(Anchor anchor, String factoryType, int currentEnergyTime, int currentProductionTime) {
		super(anchor, FactoryCategory.AREA, factoryType);
		this.currentEnergyTime = currentEnergyTime;
		this.currentProductionTime = currentProductionTime;
		indicatePowerOn();
	}
	/*
	 * Performs an update of the factory
	 */
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
		Inventory powerSourceInventory = getInventory();
		ItemList<NamedItemStack> fuel = getFactoryProperties().getFuel();
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
		indicatePowerOff();
		for (AreaEffect areaEffect : getFactoryProperties().getAllAreaEffects()) {
			areaEffect.disable(this);
		}
		FactoryModPlugin.getManager().getManager(factoryCategory).removeFactory(this);
	}

	/*
	 * Returns the time one unit of fuel lasts in ticks
	 */
	public int getEnergyTime() {
		return getFactoryProperties().getEnergyTime();
	}

	/*
	 * Returns the time between proudction in ticks
	 */
	public int getProductionTime() {
		return getFactoryProperties().getProductionTime();
	}

	/*
	 * Gets the inventory of the powersource used for this factory
	 */
	private Inventory getInventory() {
		return ((InventoryHolder) getInventoryLocation().getBlock().getState()).getInventory();
	}
	
	/*
	 * gets the location of the power source of the factory
	 */
	private Location getInventoryLocation() {
		return anchor.getLocationOfOffset(getFactoryProperties().getInventory());
	}

	/*
	 * Gets a correctly caste properties file
	 */
	@Override
	protected SimpleFactoryProperties getFactoryProperties() {
		return (SimpleFactoryProperties) super.getFactoryProperties();
	}

	/*
	 * Updates the effects and players effected
	 */
	public void updateEffects() {
		Map<Integer, Set<AreaEffect>> areaEffects = getFactoryProperties().getAreaEffects();
		Set<Player> group = getGroup();
		for (Integer radius : areaEffects.keySet()) {
			Set<Player> players = new HashSet<Player>();
			//Replicates Mojang implementation of Beacons, unsure of the requirement of -2 and +2
			int xMin = anchor.getLocation().getBlockX() - 2 - radius;
			int xMax = anchor.getLocation().getBlockX() + 2 + radius;
			int zMin = anchor.getLocation().getBlockZ() - 2 - radius;
			int zMax = anchor.getLocation().getBlockZ() + 2 + radius;
			for (int x = xMin; x <= xMax; x += 16) {
				for (int z = zMin; z <= zMax; z += 16) {
					for (Entity entity : anchor.getLocation().getWorld().getChunkAt(x, z).getEntities()) {
						if (entity instanceof Player) {
							Player player = (Player) entity;
							if (xMin < player.getLocation().getBlockX()
								&& xMax > player.getLocation().getBlockX()
								&& zMin < player.getLocation().getBlockZ()
								&& zMax > player.getLocation().getBlockZ()) {
								if (group.contains(player)||group.size()==0) {
									players.add(player);
								}
							}
						}
					}
				}
			}
			for (AreaEffect areaEffect : areaEffects.get(radius)) {
				updateAreaEffect(areaEffect, players);
			}
		}
	}
	/*
	 * Gets the set of allowed playersgroup which owns the factory
	 */
	public Set<Player> getGroup() {
		Set<Player> players = new HashSet<Player>();
		if(FactoryModPlugin.CITADEL_ENABLED) {
			IReinforcement rein = AccessDelegate.getDelegate(getInventoryLocation().getBlock()).getReinforcement();
			if (!(rein instanceof PlayerReinforcement)){
				PlayerReinforcement prein = (PlayerReinforcement)rein;
				Faction group = prein.getOwner();
				Set<FactionMember> factionMembers = Citadel.getGroupManager().getMembersOfGroup(group.getName());
				for(FactionMember factionMemeber : factionMembers) {
					players.add(Bukkit.getServer().getPlayer(factionMemeber.getMemberName()));
				}
			}
		}
		return players;
		
	}
	
	/*
	 * Generates the outputs produced by this factory
	 */
	protected void generateProducts() {
		getFactoryProperties().getOutputs().putIn(getInventory());
	}
	
	/*
	 * Updates the Effect depending on what class it is
	 */

	private void updateAreaEffect(AreaEffect areaEffect, Set<Player> players) {
		if (areaEffect instanceof ReinforcementEffect) {
			ReinforcementEffect.apply(this, players);
		} else if (areaEffect instanceof AreaPotionEffect) {
			AreaPotionEffect.apply(this, players);
		} else if (areaEffect instanceof ChatEffect) {
			ChatEffect.apply(this, players);
		}

	}

	/*
	 * Get data for the chat effect
	 */
	public List<String> getChatEffectData() {
		for(ItemStack itemStack:getInventory().getContents()) {
			if(itemStack.hasItemMeta()&&itemStack.getItemMeta() instanceof BookMeta) {
				return ((BookMeta) itemStack.getItemMeta()).getPages();
			}
		}
		return Arrays.asList("No book to broadcast!");
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
