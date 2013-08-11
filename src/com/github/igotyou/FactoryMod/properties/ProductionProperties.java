package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import java.util.List;

import com.github.igotyou.FactoryMod.interfaces.Properties;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;


public class ProductionProperties implements Properties
{
	private ItemList<NamedItemStack> inputs;
	private List<ProductionRecipe> recipes;
	private ItemList<NamedItemStack> fuel;
	private int energyTime;
	private String name;
	private int repair;
	
	public ProductionProperties(ItemList<NamedItemStack> inputs, List<ProductionRecipe> recipes,
			ItemList<NamedItemStack> fuel, int energyTime, String name,int repair)
	{
		this.inputs = inputs;
		this.recipes = recipes;
		this.fuel = fuel;
		this.energyTime = energyTime;
		this.name = name;
		this.repair=repair;
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
	
	public int getEnergyTime()
	{
		return energyTime;
	}
	
	public String getName()
	{
		return name;
	}
	/*
	 * Parse a ProductionProperties from a ConfigurationSection
	 */
	public static ProductionProperties fromConfig(String title, ConfigurationSection factoriesConfiguration)
	{
		ConfigurationSection factoryConfiguration=factoriesConfiguration.getConfigurationSection(title);
		title=title.replaceAll(" ","_");
		String factoryName=factoryConfiguration.getString("name","Default Name");
		//Uses overpowered getItems method for consistency, should always return a list of size=1
		//If no fuel is found, default to charcoal
		ItemList<NamedItemStack> fuel=FactoryModPlugin.getItems(factoryConfiguration.getConfigurationSection("fuel"));
		if(fuel.isEmpty())
		{
			fuel=new ItemList<NamedItemStack>();
			fuel.add(new NamedItemStack(Material.getMaterial("COAL"),1,(short)1,"Charcoal"));
		}
		int fuelTime=factoryConfiguration.getInt("fuel_time",2);
		ItemList<NamedItemStack> inputs=FactoryModPlugin.getItems(factoryConfiguration.getConfigurationSection("inputs"));
		ItemList<NamedItemStack> repairs=FactoryModPlugin.getItems(factoryConfiguration.getConfigurationSection("repair_inputs"));
		List<ProductionRecipe> factoryRecipes=new ArrayList<ProductionRecipe>();
		Iterator<String> ouputRecipeIterator=factoryConfiguration.getStringList("recipes").iterator();
		while (ouputRecipeIterator.hasNext())
		{
			factoryRecipes.add(FactoryModPlugin.getManager().getProductionManager().productionRecipes.get(ouputRecipeIterator.next()));
		}
		int repair=factoryConfiguration.getInt("repair_multiple",0);
		//Create repair recipe
		FactoryModPlugin.getManager().getProductionManager().productionRecipes.put(title+"REPAIR",new ProductionRecipe(title+"REPAIR","Repair Factory",1,repairs));
		factoryRecipes.add(FactoryModPlugin.getManager().getProductionManager().productionRecipes.get(title+"REPAIR"));
		return new ProductionProperties(inputs, factoryRecipes, fuel, fuelTime, factoryName, repair);
	}
}
