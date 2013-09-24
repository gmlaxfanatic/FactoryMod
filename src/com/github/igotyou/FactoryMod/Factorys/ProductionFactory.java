package com.github.igotyou.FactoryMod.Factorys;

import org.bukkit.Location;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.Recipe;
import com.github.igotyou.FactoryMod.managers.ProductionFactoryManager;
import com.github.igotyou.FactoryMod.properties.ProductionFactoryProperties;
import com.github.igotyou.FactoryMod.recipes.ProbabilisticEnchantment;
import com.github.igotyou.FactoryMod.recipes.ProductionRecipe;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.InteractionResponse;
import com.github.igotyou.FactoryMod.utility.InteractionResponse.InteractionResult;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.bukkit.inventory.Inventory;

public class ProductionFactory extends ItemFactory {

	private int currentRecipeNumber = 0;//the array index of the current recipe

	/**
	 * Constructor
	 */
	public ProductionFactory(Anchor anchor, String factoryType) {
		super(anchor, false, FactoryCategory.PRODUCTION, factoryType);
		this.setRecipeToNumber(0);
	}

	/**
	 * Constructor
	 */
	public ProductionFactory(Anchor anchor,
		boolean active, String factoryType, int currentProductionTimer, int currentEnergyTimer,
		int currentRecipeNumber, double currentMaintenance, long timeDisrepair) {
		super(anchor, active, FactoryCategory.PRODUCTION, factoryType, currentProductionTimer, currentEnergyTimer, currentMaintenance, timeDisrepair);
		this.setRecipeToNumber(currentRecipeNumber);
	}

	protected ProductionFactoryProperties getFactoryProperties() {
		return (ProductionFactoryProperties) super.getFactoryProperties();
	}
	
	protected ProductionRecipe getCurrentRecipe() {
		return getRecipes().get(currentRecipeNumber);
	}
	
	@Override
	public boolean checkHasMaterials() {
		return getCurrentRecipe().hasMaterials(getInventory());
	}

	@Override
	public boolean isRepairing() {
		return getCurrentRecipe().getRepairs().size() != 0;
	}

	/**
	 * Returns either a success or error message. Called by the
	 * blockListener when a player left clicks the center block, with the
	 * InteractionMaterial
	 */
	@Override
	public List<InteractionResponse> getCentralBlockResponse() {
		List<InteractionResponse> responses = new ArrayList<InteractionResponse>();
		//Is the factory off
		if (!active) {
			//is the recipe is initiaed
			if (getRecipes().size()<=currentRecipeNumber) {
				//if we are at the end of the recipe array loop around
				if (currentRecipeNumber == getRecipes().size() - 1) {
					setRecipeToNumber(0);
					currentProductionTimer = 0;
				} //if we can just increment the recipe
				else {
					setRecipeToNumber(currentRecipeNumber + 1);
					currentProductionTimer = 0;
				}
			} //if the recipe for some reason is not initialised, initialise it to recipe 0
			else {
				setRecipeToNumber(0);
				currentProductionTimer = 0;
			}
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "-----------------------------------------------------"));
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Switched recipe to: " + getCurrentRecipe().getRecipeName() + "."));
			if (currentRecipeNumber != getRecipes().size() - 1) {
				responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Next Recipe is: " + getRecipes().get(currentRecipeNumber + 1).getRecipeName() + "."));
			} else {
				responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Next Recipe is: " + getRecipes().get(0).getRecipeName() + "."));
			}
		} //if the factory is on, return error message
		else {
			responses.add(new InteractionResponse(InteractionResult.FAILURE, "You can't change recipes while the factory is on! Turn it off first."));
		}
		return responses;
	}

	@Override
	public ItemList<NamedItemStack> getFuel() {
		return getFactoryProperties().getFuel();
	}



	/**
	 * sets the recipe to the supplied index
	 *
	 * @param newRecipeNumber the desired recipeArray index
	 */
	public void setRecipeToNumber(int newRecipeNumber) {
		if (newRecipeNumber < getRecipes().size()) {
			currentRecipeNumber = newRecipeNumber;
		} else {
			currentRecipeNumber = 0;
		}
	}


	protected List<ProductionRecipe> getRecipes() {
		return getFactoryProperties().getRecipes();
	}

	@Override
	public List<InteractionResponse> getChestResponse() {
		List<InteractionResponse> responses = new ArrayList<InteractionResponse>();
		String status = active ? "On" : "Off";
		String percentDone = status.equals("On") ? " - " + Math.round(currentProductionTimer * 100 / getCurrentRecipe().getProductionTime()) + "% done." : "";
		//Name: Status with XX% health.
		int health = (getFactoryProperties().getRepair() == 0) ? 100 : (int) Math.round(100 * (1 - currentRepair / (getFactoryProperties().getRepair())));
		responses.add(new InteractionResponse(InteractionResult.SUCCESS, getFactoryProperties().getName() + ": " + status + " with " + String.valueOf(health) + "% health."));
		//RecipeName: X seconds(Y ticks)[ - XX% done.]
		responses.add(new InteractionResponse(InteractionResult.SUCCESS, getCurrentRecipe().getRecipeName() + ": " + getCurrentRecipe().getProductionTime() + " seconds(" + getCurrentRecipe().getProductionTime() * FactoryModPlugin.TICKS_PER_SECOND + " ticks)" + percentDone));
		//[Inputs: amount Name, amount Name.]
		if (!getCurrentRecipe().getInputs().isEmpty()) {
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Input: " + getCurrentRecipe().getInputs().toString() + "."));
		}
		//[Upgrades: amount Name, amount Name.]
		if (!getCurrentRecipe().getUpgrades().isEmpty()) {
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Upgrades: " + getCurrentRecipe().getUpgrades().toString() + "."));
		}
		//[Outputs: amount Name, amount Name.]
		if (!getOutputs().isEmpty()) {
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Output: " + getOutputs().toString() + "."));
		}
		//[Will repair XX% of the factory]
		if (!getCurrentRecipe().getRepairs().isEmpty() && getFactoryProperties().getRepair() != 0) {
			int amountAvailable = getCurrentRecipe().getRepairs().amountAvailable(getPowerSourceInventory());
			int amountRepaired = amountAvailable > currentRepair ? (int) Math.ceil(currentRepair) : amountAvailable;
			int percentRepaired = (int) (((double) amountRepaired) / getFactoryProperties().getRepair() * 100);
			responses.add(new InteractionResponse(InteractionResult.SUCCESS, "Will repair " + String.valueOf(percentRepaired) + "% of the factory with " + getCurrentRecipe().getRepairs().getMultiple(amountRepaired).toString() + "."));
		}
		return responses;
	}

	protected void recipeFinished() {
		//Remove upgrade and replace it with its upgraded form
		getCurrentRecipe().getUpgrades().removeOneFrom(getInventory()).putIn(getInventory(), getCurrentRecipe().getEnchantments());
	}

	@Override
	public ItemList<NamedItemStack> getInputs() {
		return getCurrentRecipe().getInputs();
	}

	@Override
	public ItemList<NamedItemStack> getOutputs() {
		return getCurrentRecipe().getOutputs(this);
	}

	@Override
	public ItemList<NamedItemStack> getRepairs() {
		return getCurrentRecipe().getRepairs();
	}

	@Override
	public List<ProbabilisticEnchantment> getEnchantments() {
		return getCurrentRecipe().getEnchantments();
	}

	@Override
	public double getEnergyTime() {
		return getFactoryProperties().getEnergyTime();
	}

	@Override
	public double getProductionTime() {
		return getCurrentRecipe().getProductionTime();
	}

	@Override
	public int getMaxRepair() {
		return getFactoryProperties().getRepair();
	}
}
