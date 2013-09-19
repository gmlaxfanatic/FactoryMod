package com.github.igotyou.FactoryMod.recipes;


import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.Recipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;

public class ProductionRecipe implements Recipe
{
	private String title;
	private String recipeName;
	private int productionTime;
	private ItemList<NamedItemStack> inputs;
	private ItemList<NamedItemStack> upgrades;
	private ItemList<NamedItemStack> outputs;
	private ItemList<NamedItemStack> repairs;
	private List<ProbabilisticEnchantment> enchantments;
	private double distanceScaling;
	private double numberScaling;
	private List<String> scaledRecipeTitles;
	private List<ProductionRecipe> scaledRecipes;
	
	public ProductionRecipe(
		String title,
		String recipeName,
		int productionTime,
		ItemList<NamedItemStack> inputs,
		ItemList<NamedItemStack> upgrades,
		ItemList<NamedItemStack> outputs,
		List<ProbabilisticEnchantment> enchantments,
		ItemList<NamedItemStack> repairs,
		double distanceScaling,
		double numberScaling,
		List<String> scaledRecipeTitles)
	{
		this.title=title;
		this.recipeName = recipeName;
		this.productionTime = productionTime;
		this.inputs = inputs;
		this.upgrades=upgrades;
		this.outputs = outputs;
		this.enchantments=enchantments;
		this.repairs=repairs;
		this.distanceScaling=distanceScaling;
		this.numberScaling=numberScaling;
		this.scaledRecipeTitles=scaledRecipeTitles;
	}
	
	public ProductionRecipe(
		String title,
		String recipeName,
		int productionTime,
		ItemList<NamedItemStack> repairs)
	{
		this(title,
			recipeName,
			productionTime,
			new ItemList<NamedItemStack>(),
			new ItemList<NamedItemStack>(),
			new ItemList<NamedItemStack>(),
			new ArrayList<ProbabilisticEnchantment>(),
			repairs,
			0,0, new LinkedList<String>());
	}
	
	public boolean hasMaterials(Inventory inventory)
	{
		return inputs.allIn(inventory)&&upgrades.oneIn(inventory)&&repairs.allIn(inventory);
	}


	public ItemList<NamedItemStack> getInputs()
	{
		return inputs;
	}
	
	public ItemList<NamedItemStack> getUpgrades()
	{
		return upgrades;
	}
	
	public ItemList<NamedItemStack> getOutputs(ProductionFactory productionFactory) 
	{
		return outputs;
	}
	
	public boolean hasRecipeScaling()
	{
		return !scaledRecipes.isEmpty()||distanceScaling==0;
	}
		
	public ItemList<NamedItemStack> getRepairs()
	{
		return repairs;
	}

	public List<ProbabilisticEnchantment> getEnchantments()
	{
		return enchantments;
	}
	
	public boolean hasEnchantments()
	{
		return enchantments.size()>0;
	}
	
	public String getTitle()
	{
		return title;
	}
	public String getRecipeName() 
	{
		return recipeName;
	}

	public int getProductionTime() 
	{
		return productionTime;
	}
	
	private List<String> getScaledRecipeTitles()
	{
		return scaledRecipeTitles;
	}
	
	public List<ProductionRecipe> getscaledRecipes()
	{
		return scaledRecipes;
	}
	
	public static Map<String,ProductionRecipe> recipesFromConfig(ConfigurationSection recipeConfig)
	{
		Map<String,ProductionRecipe> productionRecipes=new HashMap<String,ProductionRecipe>();
		for(String title:recipeConfig.getKeys(false))
		{
			//All spaces are replaced with udnerscores so they don't disrupt saving format
			productionRecipes.put(title.replaceAll(" ","_"),ProductionRecipe.recipeFromConfig(title.replaceAll(" ","_"), recipeConfig.getConfigurationSection(title)));
		}
		return productionRecipes;
	}
	
	private static ProductionRecipe recipeFromConfig(String title, ConfigurationSection recipeConfig)
	{
		//Display name of the recipe, Deafult of "Default Name"
		String recipeName = recipeConfig.getString("name","Default Name");
		//Production time of the recipe, default of 1
		int productionTime=recipeConfig.getInt("production_time",2);
		//Inputs of the recipe, empty of there are no inputs
		ItemList<NamedItemStack> inputs = ItemList.fromConfig(recipeConfig.getConfigurationSection("inputs"));
		//Inputs of the recipe, empty of there are no inputs
		ItemList<NamedItemStack> upgrades = ItemList.fromConfig(recipeConfig.getConfigurationSection("upgrades"));
		//Outputs of the recipe, empty of there are no inputs
		ItemList<NamedItemStack> outputs = ItemList.fromConfig(recipeConfig.getConfigurationSection("outputs"));
		//Enchantments of the recipe, empty of there are no inputs
		List<ProbabilisticEnchantment> enchantments=ProbabilisticEnchantment.listFromConfig(recipeConfig.getConfigurationSection("enchantments"));
		//Get location/#based Scaling
		double distanceScaling = recipeConfig.getDouble("distance_scaling",0.0);
		double numberScaling = recipeConfig.getDouble("number_scaling",0.0);
		List<String> scaledRecipes = recipeConfig.getStringList("scaled_recipes");
		return new ProductionRecipe(title,
			recipeName,productionTime,inputs,upgrades,outputs,
			enchantments,new ItemList<NamedItemStack>(),
			distanceScaling, numberScaling, scaledRecipes);
	}
	
	public static void loadAllScaledRecipes(Map<String,ProductionRecipe> productionRecipes)
	{
		for(Entry<String,ProductionRecipe> entry:productionRecipes.entrySet())
		{
			entry.getValue().loadScaledRecipes(productionRecipes);
		}
	}
	
	private void loadScaledRecipes(Map<String,ProductionRecipe> productionRecipes)
	{
		scaledRecipes=new LinkedList<ProductionRecipe>();
		for(String title:scaledRecipeTitles)
		{
			scaledRecipes.add(productionRecipes.get(title));
		}
	}
	
	
	
}
