package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.BaseFactory;
import java.util.List;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.managers.ProductionFactoryManager;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ProductionFactoryProperties extends RecipeFactoryProperties {

	private ItemList<NamedItemStack> inputs;
	private List<ProductionRecipe> recipes;

	public ProductionFactoryProperties(String factoryID, String name, Structure structure, Map<String, Offset> interactionPoints, ItemList<NamedItemStack> inputs, List<ProductionRecipe> recipes,
		ItemList<NamedItemStack> fuel, int energyTime, int repair) {
		super(factoryID, name, structure, interactionPoints, fuel, repair, energyTime);
		this.inputs = inputs;
		this.recipes = recipes;

	}

	public ItemList<NamedItemStack> getInputs() {
		return inputs;
	}

	public List<ProductionRecipe> getRecipes() {
		return recipes;
	}

	/*
	 * Parse a ProductionFactoryProperties from a ConfigurationSection
	 */
	public static Map<String, FactoryProperties> productionPropertiesFromConfig(ConfigurationSection factoriesConfiguration, ProductionFactoryManager productionManager) {
		Map<String, FactoryProperties> productionProperties = new HashMap<String, FactoryProperties>();
		for (String title : factoriesConfiguration.getKeys(false)) {
			productionProperties.put(title, ProductionFactoryProperties.fromConfig(title, factoriesConfiguration.getConfigurationSection(title), productionManager));
		}
		return productionProperties;
	}
	/*
	 * Imports a single Territorial factory properties from a configuration section
	 */

	protected static ProductionFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection, ProductionFactoryManager productionManager) {
		RecipeFactoryProperties recipeFactoryProperties = RecipeFactoryProperties.fromConfig(factoryID, configurationSection);
		ItemList<NamedItemStack> inputs = ItemList.fromConfig(configurationSection.getConfigurationSection("inputs"));
		List<ProductionRecipe> recipes = new ArrayList<ProductionRecipe>();
		for (String outputRecipe : configurationSection.getStringList("recipes")) {
			recipes.add(productionManager.getProductionRecipe(outputRecipe));
		}
		//Create repair recipe
		ItemList<NamedItemStack> repairs = ItemList.fromConfig(configurationSection.getConfigurationSection("repair_inputs"));
		ProductionRecipe repairRecipe = new ProductionRecipe(factoryID + "REPAIR", "Repair Factory", 1, repairs);
		productionManager.addProductionRecipe(factoryID + "REPAIR", repairRecipe);
		recipes.add(repairRecipe);
		return new ProductionFactoryProperties(recipeFactoryProperties.factoryID, recipeFactoryProperties.name, recipeFactoryProperties.structure, recipeFactoryProperties.interactionPoints, inputs, recipes, recipeFactoryProperties.fuel, recipeFactoryProperties.energyTime, recipeFactoryProperties.repair);
	}
}
