package com.github.igotyou.FactoryMod.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.properties.ProductionFactoryProperties;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class ProductionFactoryManager extends RecipeFactoryManager {

	public Map<String, ProductionRecipe> productionRecipes;

	public ProductionFactoryManager(FactoryModPlugin plugin, ConfigurationSection configurationSection) {
		super(plugin, configurationSection);
		initConfig(configurationSection);
		updateMaterials();
		updateInteractionMaterials();
		updateStructures();
		updateFactorys();
	}

	/*
	 * Legacy load
	 */
	@Override
	public void load1(File file) {
		try {
			repairTime = System.currentTimeMillis();
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
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
				long timeDisrepair = Long.parseLong(parts[17]);
				if (allFactoryProperties.containsKey(subFactoryType)) {
					Set<ProductionRecipe> recipes = new HashSet<ProductionRecipe>();

					// TODO: Give default recipes for subfactory type
					ProductionFactoryProperties properties = (ProductionFactoryProperties) allFactoryProperties.get(subFactoryType);
					recipes.addAll(properties.getRecipes());

					for (String name : recipeNames) {
						if (productionRecipes.containsKey(name)) {
							recipes.add(productionRecipes.get(name));
						}
					}

					Location difference = inventoryLocation.subtract(powerLocation);
					Anchor.Orientation orientation;
					if (difference.getX() == 0) {
						if (difference.getZ() > 0) {
							orientation = Anchor.Orientation.NW;
						} else {
							orientation = Anchor.Orientation.SE;
						}
					} else {
						if (difference.getZ() > 0) {
							orientation = Anchor.Orientation.NE;
						} else {
							orientation = Anchor.Orientation.SW;
						}
					}

					ProductionFactory production = new ProductionFactory(new Anchor(orientation, inventoryLocation), active, subFactoryType, productionTimer, energyTimer, currentRecipeNumber, currentRepair, timeDisrepair);
					addFactory(production);
				}
			}
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Imports settings, recipes and factories from config
	 */
	private void initConfig(ConfigurationSection productionConfiguration) {
		//Import recipes from config.yml
		productionRecipes = ProductionRecipe.recipesFromConfig(productionConfiguration.getConfigurationSection("recipes"));
		//Import factory properties
		allFactoryProperties = ProductionFactoryProperties.productionPropertiesFromConfig(productionConfiguration.getConfigurationSection("factories"), this);
	}
	/*
	 * Creates a factory at the location if the creation conditions are met
	 *	Inputs are present in inventory block
	 */

	public InteractionResponse createFactory(FactoryProperties properties, Anchor anchor) {
		ProductionFactoryProperties productionProperties = (ProductionFactoryProperties)properties;
		ItemList<NamedItemStack> inputs =  productionProperties.getInputs();
		Inventory inventory = ((InventoryHolder)anchor.getLocationOfOffset(productionProperties.getInventoryOffset()).getBlock().getState()).getInventory();
		if(inputs.exactlyIn(inventory))
		{
			inputs.removeFrom(inventory);

			ProductionFactory productionFactory = new ProductionFactory(anchor, properties.getName());
			addFactory(productionFactory);
			return new InteractionResponse(InteractionResult.SUCCESS, "Successfully created " + properties.getName());
		}
		FactoryModPlugin.debugMessage("Creation materials not present for "+productionProperties.getName());
		FactoryModPlugin.debugMessage("Creation materials: "+productionProperties.getInputs().toString());
		for(ItemStack itemStack:inventory.getContents()){
			if(itemStack!=null) FactoryModPlugin.debugMessage("Material Present: "+ itemStack.toString());
		}
		return new InteractionResponse(InteractionResult.FAILURE, "Incorrect Materials! They must match exactly.");
	}

	/*
	 * Returns of the ProductionProperites for a particular factory
	 */
	@Override
	public ProductionFactoryProperties getProperties(String title) {
		return (ProductionFactoryProperties) super.getProperties(title);
	}

	public ProductionRecipe getProductionRecipe(String identifier) {
		return productionRecipes.get(identifier);
	}

	public void addProductionRecipe(String title, ProductionRecipe productionRecipe) {
		productionRecipes.put(title, productionRecipe);
	}
}
