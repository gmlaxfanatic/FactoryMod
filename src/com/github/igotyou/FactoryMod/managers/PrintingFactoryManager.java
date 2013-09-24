package com.github.igotyou.FactoryMod.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.PrintingFactory;
import com.github.igotyou.FactoryMod.Factorys.PrintingFactory.OperationMode;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.PrintingFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.InventoryHolder;

public class PrintingFactoryManager extends ItemFactoryManager {

	public PrintingFactoryProperties printingFactoryProperties;

	public PrintingFactoryManager(FactoryModPlugin plugin, ConfigurationSection printingConfiguration) {
		super(plugin, printingConfiguration);
		printingFactoryProperties = PrintingFactoryProperties.fromConfig(plugin, printingConfiguration);
		allFactoryProperties.put("PrintingFactoryProperties", PrintingFactoryProperties.fromConfig(plugin, printingConfiguration));
		updateManager();
	}

	/*
	 * Loads version 1 of the file system
	 */
	@Override
	public void load1(File file) {
		try {
			repairTime = System.currentTimeMillis();
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			int version = ois.readInt();
			assert (version == 1);
			int count = ois.readInt();
			int i = 0;
			for (i = 0; i < count; i++) {
				String worldName = ois.readUTF();
				World world = plugin.getServer().getWorld(worldName);

				Location inventoryLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Location powerLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				boolean active = ois.readBoolean();
				OperationMode mode = PrintingFactory.OperationMode.byId(ois.readInt());
				int productionTimer = ois.readInt();
				int energyTimer = ois.readInt();
				double currentRepair = ois.readDouble();
				long timeDisrepair = ois.readLong();
				int containedPaper = ois.readInt();
				int containedBindings = ois.readInt();
				int containedSecurityMaterials = ois.readInt();
				int lockedResultCode = ois.readInt();

				int queueLength = ois.readInt();
				int[] processQueue = new int[queueLength];
				int j;
				for (j = 0; j < queueLength; j++) {
					processQueue[j] = ois.readInt();
				}

				Location difference = inventoryLocation.subtract(powerLocation);
				Orientation orientation;
				if (difference.getX() == 0) {
					if (difference.getZ() > 0) {
						orientation = Orientation.NW;
					} else {
						orientation = Orientation.SE;
					}
				} else {
					if (difference.getZ() > 0) {
						orientation = Orientation.NE;
					} else {
						orientation = Orientation.SW;
					}
				}


				PrintingFactory production = new PrintingFactory(new Anchor(orientation, inventoryLocation),
					active, productionTimer,
					energyTimer, currentRepair, timeDisrepair,
					mode,
					containedPaper, containedBindings, containedSecurityMaterials,
					processQueue, lockedResultCode);
				addFactory(production);
			}
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		PrintingFactoryProperties printingProperties = (PrintingFactoryProperties) properties;
		Inventory inventory = ((InventoryHolder) anchor.getLocationOfOffset(printingProperties.getInventoryOffset()).getBlock().getState()).getInventory();
		if (printingProperties.getConstructionMaterials().allIn(inventory)) {
			PrintingFactory production = new PrintingFactory(anchor, false);
			if (printingProperties.getConstructionMaterials().removeFrom(production.getInventory())) {
				addFactory(production);
				return new InteractionResponse(InteractionResult.SUCCESS, "Successfully created " + printingProperties.getName());
			}
		}
		return new InteractionResponse(InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}

	@Override
	public PrintingFactory factoryAtLocation(Location factoryLocation) {
		return (PrintingFactory) super.factoryAtLocation(factoryLocation);
	}

	@Override
	public PrintingFactoryProperties getProperties(String title) {
		return printingFactoryProperties;
	}
}
