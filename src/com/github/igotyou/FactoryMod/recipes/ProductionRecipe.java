package com.github.igotyou.FactoryMod.recipes;


import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.Factorys.ProductionFactory;
import com.github.igotyou.FactoryMod.interfaces.Recipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import java.util.ArrayList;
import java.util.List;
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
	private double scalingBase;
	private double scalingExponent;
	
	public ProductionRecipe(
		String title,
		String recipeName,
		int productionTime,
		ItemList<NamedItemStack> inputs,
		ItemList<NamedItemStack> upgrades,
		ItemList<NamedItemStack> outputs,
		List<ProbabilisticEnchantment> enchantments,
		ItemList<NamedItemStack> repairs,
		double scalingBase,
		double scalingExponent)
	{
		this.title=title;
		this.recipeName = recipeName;
		this.productionTime = productionTime;
		this.inputs = inputs;
		this.upgrades=upgrades;
		this.outputs = outputs;
		this.enchantments=enchantments;
		this.repairs=repairs;
		this.scalingBase=scalingBase;
		this.scalingExponent=scalingExponent;
	}
	
	public ProductionRecipe(String title,String recipeName,int productionTime,ItemList<NamedItemStack> repairs)
	{
		this(title,recipeName,productionTime,new ItemList<NamedItemStack>(),new ItemList<NamedItemStack>(),new ItemList<NamedItemStack>(),new ArrayList<ProbabilisticEnchantment>(),repairs,0,0);
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
		if(hasRecipeScaling())
		{
			return outputs.getMultiple(getRecipeScaling(productionFactory));
		}
		else
		{
			return outputs;
		}
	}
	
	public boolean hasRecipeScaling()
	{
		return scalingBase==0;
	}
		
	/*
	 * Scaling takes into accoutn distance and number of factories by the
	 * formula: 1/Π(scalingBase^(scalingExponent*worldBorderCorrection/factoryDistance)
	 *	scalingBase>1
	 */
	public double getRecipeScaling(ProductionFactory producingFactory)
	{
		List<ProductionFactory> otherFactories=FactoryModPlugin.getManager().getProductionManager().getFactoriesByRecipe(this);
		if(scalingBase==0)
		{
			return 1;
		}
		
		double outputScaling=1.0;
		//Compensates for the world border restricting area factories can be by
		//Formula needs to be created
		double worldBorderCorrection=1;
		for(ProductionFactory factory:otherFactories)
		{
			if(factory!=producingFactory)
			{
				double distance=factory.getCenterLocation().distance(producingFactory.getCenterLocation());
				outputScaling=outputScaling*1/Math.pow(scalingBase,(scalingExponent*worldBorderCorrection/distance));
			}
		}
		return outputScaling;
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
	
	public static ProductionRecipe fromConfig(String title, ConfigurationSection recipeConfig)
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
		double scalingBase = recipeConfig.getDouble("scaling_base",0.0);
		double scalingExponent = recipeConfig.getDouble("scaling_exponent",0.0);
		ProductionRecipe productionRecipe = new ProductionRecipe(title,recipeName,productionTime,inputs,upgrades,outputs,enchantments,new ItemList<NamedItemStack>(),scalingBase,scalingExponent);
		return productionRecipe;
	}
}
