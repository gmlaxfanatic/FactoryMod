package com.github.igotyou.FactoryMod.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.RepairFactory;

public class RepairCsvWriter implements IFactoryWriter<RepairFactory> {
	
	File mFile;
	
	public RepairCsvWriter(File file) {
		mFile = file;
	}

	@Override
	public synchronized void write(List<RepairFactory> factories) {
		FileBackup.backup(mFile);
		
		if(!mFile.exists()) {
			try {
				mFile.createNewFile();
			} catch (IOException e) {
				FactoryModPlugin.sendConsoleMessage(new StringBuilder("ERROR: Could not create file ")
					.append(mFile.getName()).append(": ").append(e.getMessage()).toString());
				return;				
			}
		}

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(mFile);
			ObjectOutputStream oos= new ObjectOutputStream(fileOutputStream);

			int version = 1;
			oos.writeInt(version);
			oos.writeInt(factories.size());
			for (RepairFactory factory: factories)
			{
				Location centerLocation = factory.getCenterLocation();
				Location inventoryLocation = factory.getInventoryLocation();
				Location powerLocation = factory.getPowerSourceLocation();
				
				oos.writeUTF(centerLocation.getWorld().getName());
				
				oos.writeInt(centerLocation.getBlockX());
				oos.writeInt(centerLocation.getBlockY());
				oos.writeInt(centerLocation.getBlockZ());
				
				oos.writeInt(inventoryLocation.getBlockX());
				oos.writeInt(inventoryLocation.getBlockY());
				oos.writeInt(inventoryLocation.getBlockZ());

				oos.writeInt(powerLocation.getBlockX());
				oos.writeInt(powerLocation.getBlockY());
				oos.writeInt(powerLocation.getBlockZ());
				
				oos.writeInt(factory.getMode().getId());
				oos.writeDouble(factory.getCurrentRepair());
				oos.writeLong(factory.getTimeDisrepair());
			}
		
			oos.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			FactoryModPlugin.sendConsoleMessage(new StringBuilder("ERROR: Could not open file ")
			.append(mFile.getName()).append("for writing: ").append(e.getMessage()).toString());
			return;
		} catch (IOException e) {
			FactoryModPlugin.sendConsoleMessage(new StringBuilder("ERROR: Could not complete write to ")
			.append(mFile.getName()).append(": ").append(e.getMessage()).toString());
		}
		
	}
}
