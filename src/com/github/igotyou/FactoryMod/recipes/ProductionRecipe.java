package com.github.igotyou.FactoryMod.recipes;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import java.util.ArrayList;
import java.util.List;


import com.github.igotyou.FactoryMod.interfaces.Recipe;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import org.bukkit.inventory.Inventory;

public class ProductionRecipe implements Recipe
{
	private FactoryModPlugin plugin;
	private String title;
	private String recipeName;
	private int productionTime;
	private ItemList<NamedItemStack> inputs;
	private ItemList<NamedItemStack> upgrades;
	private ItemList<NamedItemStack> outputs;
	private ItemList<NamedItemStack> repairs;
	private List<ProductionRecipe> outputRecipes;
	private List<ProbabilisticEnchantment> enchantments;
	private boolean useOnce;
	private double recipeScaling;
	
	public ProductionRecipe(FactoryModPlugin plugin, String title,String recipeName,int productionTime,ItemList<NamedItemStack> inputs,ItemList<NamedItemStack> upgrades,
		ItemList<NamedItemStack> outputs,List<ProbabilisticEnchantment> enchantments,boolean useOnce, ItemList<NamedItemStack> repairs, double recipeScaling)
	{
		this.plugin=plugin;
		this.title=title;
		this.recipeName = recipeName;
		this.productionTime = productionTime;
		this.inputs = inputs;
		this.upgrades=upgrades;
		this.outputs = outputs;
		this.outputRecipes=new ArrayList<ProductionRecipe>();
		this.enchantments=enchantments;
		this.useOnce=useOnce;
		this.repairs=repairs;
		this.recipeScaling=recipeScaling;
	}
	
	public ProductionRecipe(FactoryModPlugin plugin,String title,String recipeName,int productionTime,ItemList<NamedItemStack> repairs)
	{
		this(plugin,title,recipeName,productionTime,new ItemList<NamedItemStack>(),new ItemList<NamedItemStack>(),new ItemList<NamedItemStack>(),new ArrayList<ProbabilisticEnchantment>(),false,repairs,0);
	}
	
	public boolean hasMaterials(Inventory inventory)
	{
		return inputs.allIn(inventory)&&upgrades.oneIn(inventory)&&repairs.allIn(inventory);
	}
	public void addOutputRecipe(ProductionRecipe outputRecipe)
	{
		this.outputRecipes.add(outputRecipe);
	}

	public ItemList<NamedItemStack> getInputs()
	{
		return inputs;
	}
	
	public ItemList<NamedItemStack> getUpgrades()
	{
		return upgrades;
	}
	
	public ItemList<NamedItemStack> getOutputs() 
	{
		if(recipeScaling==0)
		{
			return outputs;
		}
		else
		{
			return outputs.getMultiple(Math.pow(plugin.getManager().getProductionManager().getFactoriesByRecipe(this).size(),recipeScaling));
		}
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

	public List<ProductionRecipe> getOutputRecipes()
	{
		return outputRecipes;
	}

	public boolean getUseOnce()
	{
		return useOnce;
	}
}
