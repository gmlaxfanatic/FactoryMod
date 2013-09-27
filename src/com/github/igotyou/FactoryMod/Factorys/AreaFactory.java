package com.github.igotyou.FactoryMod.Factorys;

import com.github.igotyou.FactoryMod.Factorys.BaseFactory.FactoryCategory;
import com.github.igotyou.FactoryMod.interfaces.AreaEffect;
import com.github.igotyou.FactoryMod.utility.Anchor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class AreaFactory extends ContinousFactory {

	public AreaFactory(Anchor anchor, String factoryType) {
		super(anchor, factoryType, FactoryCategory.AREA);
	}

	public AreaFactory(Anchor anchor, String factoryType, int currentEnergyTime) {
		super(anchor, factoryType, FactoryCategory.AREA, currentEnergyTime);

	}

	/*
	 * Updates the effects and players effected
	 */
	@Override
	public void updateSpecifics() {
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
								if (group.contains(player) || group.size() == 0) {
									players.add(player);
								}
							}
						}
					}
				}
			}
			for (AreaEffect areaEffect : areaEffects.get(radius)) {
				areaEffect.apply(this, players);
			}
		}
	}
	
	/*
	 * Get data for the chat effect
	 */
	public List<String> getChatEffectData() {
		for (ItemStack itemStack : getInventory().getContents()) {
			if (itemStack.hasItemMeta() && itemStack.getItemMeta() instanceof BookMeta) {
				return ((BookMeta) itemStack.getItemMeta()).getPages();
			}
		}
		return Arrays.asList("No book to broadcast!");
	}
}
