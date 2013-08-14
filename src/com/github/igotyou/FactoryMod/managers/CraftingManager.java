/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.igotyou.FactoryMod.managers;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

/**
 *
 * @author Brian Landry
 */
public class CraftingManager {
	private FactoryModPlugin plugin;
	
	public CraftingManager(FactoryModPlugin plugin)
	{
		this.plugin = plugin;
		initConfig(plugin.getConfig().getConfigurationSection("crafting"));
	}
	
	private void initConfig(ConfigurationSection craftingConfiguration)
	{
		for(String disabledRecipe:craftingConfiguration.getStringList("disable"))
		{
			ItemStack recipeItemStack = new ItemStack(Material.getMaterial(disabledRecipe));
			List<Recipe> tempList = plugin.getServer().getRecipesFor(recipeItemStack);
			for (int itterator = 0; itterator < tempList.size(); itterator ++)
			{
				removeRecipe(tempList.get(itterator));
			}

		}
		//Enable the following recipes
		ConfigurationSection configCraftingEnable=craftingConfiguration.getConfigurationSection("enable");
		for (String recipeName:configCraftingEnable.getKeys(false))
		{
			ConfigurationSection configSection=configCraftingEnable.getConfigurationSection(recipeName);
			Recipe recipe;
			List<String> shape=configSection.getStringList("shape");
			NamedItemStack output=ItemList.fromConfig(configSection.getConfigurationSection("output")).get(0);
			if(shape.isEmpty())
			{
				ShapelessRecipe shapelessRecipe=new ShapelessRecipe(output);
				for (ItemStack input:ItemList.fromConfig(configSection.getConfigurationSection("inputs")))
				{
					shapelessRecipe.addIngredient(input.getAmount(),input.getType(),input.getDurability());
				}
				recipe=shapelessRecipe;
			}
			else
			{
				ShapedRecipe shapedRecipe=new ShapedRecipe(output);
				shapedRecipe.shape(shape.toArray(new String[shape.size()]));
				for(String inputKey:configSection.getConfigurationSection("inputs").getKeys(false))
				{
					ItemStack input=ItemList.fromConfig(configSection.getConfigurationSection("inputs."+inputKey)).get(0);
					shapedRecipe.setIngredient(inputKey.charAt(0),input.getType(),input.getDurability());
				}
				recipe=shapedRecipe;
			}
			Bukkit.addRecipe(recipe);
		}
	}
	
	private void removeRecipe(Recipe removalRecipe)
	{
		Iterator<Recipe> itterator = plugin.getServer().recipeIterator();
		while (itterator.hasNext())
		{
			Recipe recipe = itterator.next();
			if (recipe.getResult().getType() == removalRecipe.getResult().getType())
			{
				itterator.remove();
			}
		}
	}
}
