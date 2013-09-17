package com.github.igotyou.FactoryMod.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ItemFactory;
import com.github.igotyou.FactoryMod.Factorys.PrintingPress;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.FactoryManager;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.ProductionProperties;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.InventoryHolder;

public class ProductionManager  extends ItemFactoryManager implements FactoryManager
{
	public  Map<String, ProductionProperties> allProductionProperties;
	public  Map<String,ProductionRecipe> productionRecipes;
	private List<ProductionFactory> productionFactories=new ArrayList<ProductionFactory>();;
	
	public ProductionManager(FactoryModPlugin plugin)
	{
		super(plugin);
		initConfig(plugin.getConfig().getConfigurationSection("production"));
		for(ProductionProperties productionProperties:allProductionProperties.values()) {
			interactionMaterials.addAll(productionProperties.getInteractionMaterials());
		}
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
		oos.writeInt(version);
		oos.writeInt(itemFactories.size());
		for (ItemFactory itemFactory : itemFactories)
		{
			ProductionFactory productionFactory=(ProductionFactory) itemFactory;
		
			oos.writeUTF(productionFactory.getAnchor().location.getWorld().getName());
						
			oos.writeInt(productionFactory.getAnchor().location.getBlockX());
			oos.writeInt(productionFactory.getAnchor().location.getBlockY());
			oos.writeInt(productionFactory.getAnchor().location.getBlockZ());
			
			oos.writeInt(productionFactory.getAnchor().orientation.id);
			
			oos.writeUTF(productionFactory.getFactoryType());
			
			oos.writeBoolean(productionFactory.getActive());
		
			oos.writeInt(productionFactory.getProductionTimer());
			oos.writeInt(productionFactory.getEnergyTimer());
			oos.writeDouble(productionFactory.getCurrentRepair());
			oos.writeLong(productionFactory.getTimeDisrepair());
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
			assert(version == 2);
			repairTime=System.currentTimeMillis();
			int count = ois.readInt();
			int i = 0;
			for (i = 0; i < count; i++)
			{
				String worldName = ois.readUTF();
				World world = plugin.getServer().getWorld(worldName);
				Location location = new Location(world, ois.readInt(), ois.readInt(), ois.readInt());
				Anchor.Orientation orientation = Anchor.Orientation.getOrientation(ois.readInt());
				boolean active = ois.readBoolean();
				String factoryType = ois.readUTF();
				int productionTimer = ois.readInt();
				int energyTimer = ois.readInt();
				int currentRecipeNumber = ois.readInt();
				double currentRepair = ois.readDouble();
				long timeDisrepair  =  ois.readLong();
				
				if(allProductionProperties.containsKey(factoryType))
				{
					ProductionFactory productionFactory = new ProductionFactory(new Anchor(orientation,location),
						allProductionProperties.get(factoryType), active, productionTimer,
						energyTimer, currentRecipeNumber, currentRepair, timeDisrepair);
					addFactory(productionFactory);
				}
			}
			fileInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load1(File file) throws IOException 
	{
		repairTime=System.currentTimeMillis();
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			String parts[] = line.split(" ");

			//order: subFactoryType world recipe1,recipe2 central_x central_y central_z inventory_x inventory_y inventory_z power_x power_y power_z active productionTimer energyTimer current_Recipe_number 
			String subFactoryType = parts[0];
			String recipeNames[] = parts[1].split(",");

			Location centerLocation = new Location(plugin.getServer().getWorld(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
			Location inventoryLocation = new Location(plugin.getServer().getWorld(parts[2]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]), Integer.parseInt(parts[8]));
			Location powerLocation = new Location(plugin.getServer().getWorld(parts[2]), Integer.parseInt(parts[9]), Integer.parseInt(parts[10]), Integer.parseInt(parts[11]));
			boolean active = Boolean.parseBoolean(parts[12]);
			int productionTimer = Integer.parseInt(parts[13]);
			int energyTimer = Integer.parseInt(parts[14]);
			int currentRecipeNumber = Integer.parseInt(parts[15]);
			double currentRepair = Double.parseDouble(parts[16]);
			long timeDisrepair  =  Long.parseLong(parts[17]);
			if(allProductionProperties.containsKey(subFactoryType))
			{
				Set<ProductionRecipe> recipes=new HashSet<ProductionRecipe>();
				
				// TODO: Give default recipes for subfactory type
				ProductionProperties properties = allProductionProperties.get(subFactoryType);
				recipes.addAll(properties.getRecipes());
				
				for(String name:recipeNames)
				{
					if(productionRecipes.containsKey(name))
					{
						recipes.add(productionRecipes.get(name));
					}
				}
				
				Location difference = inventoryLocation.subtract(powerLocation);
				Anchor.Orientation orientation;
				if(difference.getX()==0) {
					if(difference.getZ()>0) {
						orientation = Anchor.Orientation.NW;
					}
					else {
						orientation = Anchor.Orientation.SE;
					}
				}
				else {
					if(difference.getZ()>0) {
						orientation = Anchor.Orientation.NE;
					}
					else {
						orientation = Anchor.Orientation.SW;
					}
				}
				
				ProductionFactory production = new ProductionFactory(new Anchor(orientation,inventoryLocation), allProductionProperties.get(subFactoryType), active, productionTimer, energyTimer, currentRecipeNumber, currentRepair,timeDisrepair);
				addFactory(production);
			}
		}
		fileInputStream.close();
	}
	
	/*
	 * Imports settings, recipes and factories from config
	 */
	private void initConfig(ConfigurationSection productionConfiguration)
	{
		//Import recipes from config.yml
		productionRecipes=ProductionRecipe.recipesFromConfig(productionConfiguration.getConfigurationSection("recipes"));
		ProductionRecipe.loadAllScaledRecipes(productionRecipes);
		//Import factory properties
		allProductionProperties=ProductionProperties.productionPropertiesFromConfig(productionConfiguration.getConfigurationSection("factories"), this);
	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */
	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		ProductionProperties productionProperties = (ProductionProperties)properties;
		ItemList<NamedItemStack> inputs =  productionProperties.getInputs();
		Offset creationPoint = productionProperties.getCreationPoint();
		if(inputs.exactlyIn(((InventoryHolder)anchor.getBlock(creationPoint)).getInventory()))
		{
			inputs.removeFrom(((InventoryHolder)anchor.getBlock(creationPoint)).getInventory());
			ProductionFactory productionFactory = new ProductionFactory(anchor, (ProductionProperties) properties);
			addFactory(productionFactory);
			return new InteractionResponse(InteractionResult.SUCCESS, "Successfully created " + productionFactory.getProductionFactoryProperties().getName());
		}
		return new InteractionResponse(InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}


	@Override
	public String getSavesFileName() 
	{
		return FactoryModPlugin.PRODUCTION_SAVES_FILE;
	}
	
	/*
	 * Returns of the ProductionProperites for a particular factory
	 */
	@Override
	public ProductionProperties getProperties(String title)
	{
		return allProductionProperties.get(title);
	}
	
	public ProductionRecipe getProductionRecipe(String identifier)
	{
		return productionRecipes.get(identifier);
	}
	
	public  void addProductionRecipe(String title, ProductionRecipe productionRecipe)
	{
		productionRecipes.put(title,productionRecipe);
	}
	public Set<ProductionFactory> getScaledFactories(List<ProductionRecipe> scaledRecipes)
	{
		Set<ProductionFactory> factoriesByRecipe=new HashSet<ProductionFactory>();
		for(ProductionFactory productionFactory:productionFactories)
		{
			for(ProductionRecipe scaledRecipe:scaledRecipes)
			{
				if(productionFactory.getRecipes().contains(scaledRecipe))
				{
					factoriesByRecipe.add(productionFactory);
				}
			}
		}
		return factoriesByRecipe;
	}
	
}
