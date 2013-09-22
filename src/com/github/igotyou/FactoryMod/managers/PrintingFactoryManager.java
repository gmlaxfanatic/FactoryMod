package com.github.igotyou.FactoryMod.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.PrintingFactory;
import com.github.igotyou.FactoryMod.Factorys.PrintingFactory.OperationMode;
import com.github.igotyou.FactoryMod.interfaces.Factory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.PrintingFactoryProperties;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.InventoryHolder;


public class PrintingFactoryManager  extends ItemFactoryManager {

	public PrintingFactoryProperties printingFactoryProperties;
	
	public PrintingFactoryManager (FactoryModPlugin plugin, ConfigurationSection printingConfiguration)
	{
		super(plugin, printingConfiguration);
		printingFactoryProperties = PrintingFactoryProperties.fromConfig(plugin, printingConfiguration);
		allFactoryProperties.put("PrintingFactoryProperties",PrintingFactoryProperties.fromConfig(plugin, printingConfiguration));
		updateManager();
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
		for (Factory baseFactory : factories)
		{
			PrintingFactory printingFactory=(PrintingFactory) baseFactory;
		
			oos.writeUTF(printingFactory.getAnchor().location.getWorld().getName());
						
			oos.writeInt(printingFactory.getAnchor().location.getBlockX());
			oos.writeInt(printingFactory.getAnchor().location.getBlockY());
			oos.writeInt(printingFactory.getAnchor().location.getBlockZ());
			
			oos.writeInt(printingFactory.getAnchor().orientation.id);
			
			oos.writeBoolean(printingFactory.getActive());
			oos.writeInt(printingFactory.getMode().getId());
			oos.writeInt(printingFactory.getProductionTimer());
			oos.writeInt(printingFactory.getEnergyTimer());
			oos.writeDouble(printingFactory.getCurrentRepair());
			oos.writeLong(printingFactory.getTimeDisrepair());

			oos.writeInt(printingFactory.getContainedPaper());
			oos.writeInt(printingFactory.getContainedBindings());
			oos.writeInt(printingFactory.getContainedSecurityMaterials());
			oos.writeInt(printingFactory.getLockedResultCode());
			
			int[] processQueue = printingFactory.getProcessQueue();
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
					OperationMode mode = PrintingFactory.OperationMode.byId(ois.readInt());
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

					PrintingFactory printingFactory = new PrintingFactory(new Anchor(orientation,location),
							active, productionTimer,
							energyTimer, currentRepair, timeDisrepair,
							mode,
							printingFactoryProperties,
							containedPaper, containedBindings, containedSecurityMaterials,
							processQueue, lockedResultCode);
					addFactory(printingFactory);
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

				Location inventoryLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Location powerLocation = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				boolean active = ois.readBoolean();
				OperationMode mode = PrintingFactory.OperationMode.byId(ois.readInt());
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
				
				
				PrintingFactory production = new PrintingFactory(new Anchor(orientation,inventoryLocation),
						active, productionTimer,
						energyTimer, currentRepair, timeDisrepair,
						mode,
						printingFactoryProperties,
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
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) 
	{
		PrintingFactoryProperties printingProperties = (PrintingFactoryProperties) properties;
		Inventory inventory = ((InventoryHolder)anchor.getLocationOfOffset(printingProperties.getInventoryOffset()).getBlock().getState()).getInventory();
		if (printingProperties.getConstructionMaterials().allIn(inventory))
		{
			PrintingFactory production = new PrintingFactory(anchor, false, printingProperties);
			if (printingProperties.getConstructionMaterials().removeFrom(production.getInventory()))
			{
				addFactory(production);
				return new InteractionResponse(InteractionResult.SUCCESS, "Successfully created " + printingProperties.getName());
			}
		}
		return new InteractionResponse(InteractionResult.FAILURE, "Not enough materials in chest!");
	}
	
	public PrintingFactory factoryAtLocation(Location factoryLocation) 
	{
		return (PrintingFactory) super.factoryAtLocation(factoryLocation);
	}

	public String getSavesFileName() 
	{
		return FactoryModPlugin.PRINTING_FACTORY_SAVE_FILE;
	}
	/*
	 * Returns of PrintingFactoryProperites
	 */
	public PrintingFactoryProperties getProperties(String title)
	{
		return printingFactoryProperties;
	}
}
