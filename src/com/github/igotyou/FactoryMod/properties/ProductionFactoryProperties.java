package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import java.util.List;

import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.managers.ProductionFactoryManager;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;


public class ProductionFactoryProperties extends ItemFactoryProperties implements FactoryProperties
{
	private ItemList<NamedItemStack> inputs;
	private List<ProductionRecipe> recipes;
	
	public ProductionFactoryProperties(Structure structure, ItemList<NamedItemStack> inputs, List<ProductionRecipe> recipes,
			ItemList<NamedItemStack> fuel, int energyTime, String name,int repair)
	{
		super(structure,fuel, repair, energyTime, name);
		this.inputs = inputs;
		this.recipes = recipes;
		
	}

	public int getRepair()
	{
		return repair;
	}

	public ItemList<NamedItemStack> getInputs() 
	{
		return inputs;
	}
	
	public List<ProductionRecipe> getRecipes()
	{
		return recipes;
	}
	
	public ItemList<NamedItemStack> getFuel()
	{
		return fuel;
	}

	
	/*
	 * Parse a ProductionFactoryProperties from a ConfigurationSection
	 */
	public static Map<String, FactoryProperties> productionPropertiesFromConfig(ConfigurationSection factoriesConfiguration, ProductionFactoryManager productionManager)
	{
		Map<String, FactoryProperties> productionProperties=new HashMap<String, FactoryProperties>();
		for(String title:factoriesConfiguration.getKeys(false))
		{
			productionProperties.put(title, ProductionFactoryProperties.productionPropertyFromConfig(title, factoriesConfiguration.getConfigurationSection(title),productionManager));
		}
		return productionProperties;
	}
	
	protected static ProductionFactoryProperties productionPropertyFromConfig(String title, ConfigurationSection factoryConfiguration, ProductionFactoryManager productionManager)
	{
		title=title.replaceAll(" ","_");
		String factoryName=factoryConfiguration.getString("name","Default Name");
		//Uses overpowered getItems method for consistency, should always return a list of size=1
		//If no fuel is found, default to charcoal
		ItemList<NamedItemStack> fuel=ItemList.fromConfig(factoryConfiguration.getConfigurationSection("fuel"));
		if(fuel.isEmpty())
		{
			fuel=new ItemList<NamedItemStack>();
			fuel.add(new NamedItemStack(Material.getMaterial("COAL"),1,(short)1,"Charcoal"));
		}
		int fuelTime=factoryConfiguration.getInt("fuel_time",2);
		ItemList<NamedItemStack> inputs=ItemList.fromConfig(factoryConfiguration.getConfigurationSection("inputs"));
		ItemList<NamedItemStack> repairs=ItemList.fromConfig(factoryConfiguration.getConfigurationSection("repair_inputs"));
		List<ProductionRecipe> factoryRecipes=new ArrayList<ProductionRecipe>();
		for(String outputRecipe:factoryConfiguration.getStringList("recipes"))
		{
			factoryRecipes.add(productionManager.getProductionRecipe(outputRecipe));
		}
		int repair=factoryConfiguration.getInt("repair_multiple",0);
		//Create repair recipe
		ProductionRecipe repairRecipe=new ProductionRecipe(title+"REPAIR","Repair Factory",1,repairs);
		productionManager.addProductionRecipe(title+"REPAIR",repairRecipe);
		factoryRecipes.add(repairRecipe);
		return new ProductionFactoryProperties(FactoryModPlugin.getManager().getStructureManager().getStructure("ItemFactory"),inputs, factoryRecipes, fuel, fuelTime, factoryName, repair);
	}
}
