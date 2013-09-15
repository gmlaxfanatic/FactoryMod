package com.github.igotyou.FactoryMod.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import com.github.igotyou.FactoryMod.Factorys.PrintingPress;
import com.github.igotyou.FactoryMod.Factorys.PrintingPress.OperationMode;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import com.github.igotyou.FactoryMod.interfaces.Properties;
import com.github.igotyou.FactoryMod.properties.PrintingPressProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;


public class PrintingPressManager  extends BaseFactoryManager implements FactoryManager
{

	public PrintingPressProperties printingPressProperties;
	
	public PrintingPressManager (FactoryModPlugin plugin)
	{
		super(plugin);
		printingPressProperties = PrintingPressProperties.fromConfig(plugin, plugin.getConfig().getConfigurationSection("printing_presses"));
		interactionMaterials.addAll(printingPressProperties.getInteractionMaterials());
		//Set maintenance clock to 0
		updateFactorys();
	}
	
	public void save(File file) throws IOException 
	{
		//Takes difference between last repair update and current one and scales repair accordingly
		updateRepair(System.currentTimeMillis()-repairTime);
		repairTime=System.currentTimeMillis();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		int version = 2;
		oos.writeInt(2);
		oos.writeInt(factories.size());
		for (BaseFactory baseFactory : factories)
		{
			PrintingPress printingPress=(PrintingPress) baseFactory;
		
			oos.writeUTF(printingPress.getAnchor().location.getWorld().getName());
						
			oos.writeInt(printingPress.getAnchor().location.getBlockX());
			oos.writeInt(printingPress.getAnchor().location.getBlockY());
			oos.writeInt(printingPress.getAnchor().location.getBlockZ());
			
			oos.writeInt(printingPress.getAnchor().orientation.id);
			
			oos.writeBoolean(printingPress.getActive());
			oos.writeInt(printingPress.getMode().getId());
			oos.writeInt(printingPress.getProductionTimer());
			oos.writeInt(printingPress.getEnergyTimer());
			oos.writeDouble(printingPress.getCurrentRepair());
			oos.writeLong(printingPress.getTimeDisrepair());

			oos.writeInt(printingPress.getContainedPaper());
			oos.writeInt(printingPress.getContainedBindings());
			oos.writeInt(printingPress.getContainedSecurityMaterials());
			oos.writeInt(printingPress.getLockedResultCode());
			
			int[] processQueue = printingPress.getProcessQueue();
			oos.writeInt(processQueue.length);
			for (int entry : processQueue) {
				oos.writeInt(entry);
			}
		}
		oos.flush();
		fileOutputStream.close();
	}

	public void load(File file) throws IOException 
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			int version = ois.readInt();
			if(version==1) {
				load1(file);
			}
			else {
				assert(version == 2);
				repairTime=System.currentTimeMillis();
				int count = ois.readInt();
				int i = 0;
				for (i = 0; i < count; i++)
				{
					String worldName = ois.readUTF();
					World world = plugin.getServer().getWorld(worldName);
					Location location = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
					Orientation orientation = Orientation.getOrientation(ois.readInt());
					boolean active = ois.readBoolean();
					OperationMode mode = PrintingPress.OperationMode.byId(ois.readInt());
					int productionTimer = ois.readInt();
					int energyTimer = ois.readInt();
					double currentRepair = ois.readDouble();
					long timeDisrepair  = ois.readLong();
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

					PrintingPress printingPress = new PrintingPress(new Anchor(orientation,location),
							active, productionTimer,
							energyTimer, currentRepair, timeDisrepair,
							mode,
							printingPressProperties,
							containedPaper, containedBindings, containedSecurityMaterials,
							processQueue, lockedResultCode);
					addFactory(printingPress);
				}

			}
			fileInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
	public void load1(File file) throws IOException 
	{
		try {
			repairTime=System.currentTimeMillis();
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			int version = ois.readInt();
			assert(version == 1);
			int count = ois.readInt();
			int i = 0;
			for (i = 0; i < count; i++)
			{
				String worldName = ois.readUTF();
				World world = plugin.getServer().getWorld(worldName);

				Location centerLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Location inventoryLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Location powerLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				boolean active = ois.readBoolean();
				OperationMode mode = PrintingPress.OperationMode.byId(ois.readInt());
				int productionTimer = ois.readInt();
				int energyTimer = ois.readInt();
				double currentRepair = ois.readDouble();
				long timeDisrepair  = ois.readLong();
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
				if(difference.getX()==0) {
					if(difference.getZ()>0) {
						orientation = Orientation.NW;
					}
					else {
						orientation = Orientation.SE;
					}
				}
				else {
					if(difference.getZ()>0) {
						orientation = Orientation.NE;
					}
					else {
						orientation = Orientation.SW;
					}
				}
				
				
				PrintingPress production = new PrintingPress(new Anchor(orientation,inventoryLocation),
						active, productionTimer,
						energyTimer, currentRepair, timeDisrepair,
						mode,
						printingPressProperties,
						containedPaper, containedBindings, containedSecurityMaterials,
						processQueue, lockedResultCode);
				addFactory(production);
			}
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InteractionResponse createFactory(Properties properties, Anchor anchor) 
	{
		Block inventoryBlock = anchor.getBlock(properties.getCreationPoint());
		Chest chest = (Chest) inventoryBlock.getState();
		Inventory chestInventory = chest.getInventory();
		ItemList<NamedItemStack> inputs = printingPressProperties.getConstructionMaterials();
		boolean hasMaterials = inputs.allIn(chestInventory);
		if (hasMaterials)
		{
			PrintingPress production = new PrintingPress(anchor, false, printingPressProperties);
			if (printingPressProperties.getConstructionMaterials().removeFrom(production.getInventory()))
			{
				addFactory(production);
				return new InteractionResponse(InteractionResult.SUCCESS, "Successfully created " + printingPressProperties.getName());
			}
		}
		return new InteractionResponse(InteractionResult.FAILURE, "Not enough materials in chest!");
	}
	
	public PrintingPress factoryAtLocation(Location factoryLocation) 
	{
		return (PrintingPress) super.factoryAtLocation(factoryLocation);
	}

	public String getSavesFileName() 
	{
		return FactoryModPlugin.PRINTING_PRESSES_SAVE_FILE;
	}
	/*
	 * Returns of PrintingPressProperites
	 */
	public PrintingPressProperties getProperties(String title)
	{
		return printingPressProperties;
	}
}
