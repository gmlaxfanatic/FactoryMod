package com.github.igotyou.FactoryMod.properties;


import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.ItemList;
import com.github.igotyou.FactoryMod.utility.NamedItemStack;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintingFactoryProperties extends RecipeFactoryProperties{

	private ItemList<NamedItemStack> constructionMaterials;
	private ItemList<NamedItemStack> plateMaterials;
	private ItemList<NamedItemStack> bindingMaterials;
	private ItemList<NamedItemStack> pageMaterials;
	private int pagesPerLot;
	private ItemList<NamedItemStack> pamphletMaterials;
	private int pamphletsPerLot;
	private ItemList<NamedItemStack> securityMaterials;
	private int securityNotesPerLot;
	private ItemList<NamedItemStack> repairMaterials;
	private int pageLead;
	private int setPlateTime;
	private int repairTime;


	public int getPageLead() {
		return pageLead;
	}


	public PrintingFactoryProperties(
			String factoryID,
			Structure structure,
			Map<String,Offset> interactionPoints,
			ItemList<NamedItemStack> fuel,
			ItemList<NamedItemStack> constructionMaterials,
			ItemList<NamedItemStack> repairMaterials,
			ItemList<NamedItemStack> plateMaterials,
			ItemList<NamedItemStack> bindingMaterials,
			ItemList<NamedItemStack> pageMaterials,
			int pagesPerLot,
			ItemList<NamedItemStack> pamphletMaterials,
			int pamphletsPerLot,
			ItemList<NamedItemStack> securityMaterials,
			int securityNotesPerLot,
			int energyTime, String name, int repair, int paperRate,
			int pageLead, int setPlateTime, int repairTime
			)
	{
		super(factoryID, name, structure, interactionPoints, fuel, repair, energyTime);
		this.constructionMaterials = constructionMaterials;
		this.repairMaterials = repairMaterials;
		this.plateMaterials = plateMaterials;
		this.bindingMaterials = bindingMaterials;
		this.pageMaterials = pageMaterials;
		this.pagesPerLot = pagesPerLot;
		this.pamphletMaterials = pamphletMaterials;
		this.pamphletsPerLot = pamphletsPerLot;
		this.securityMaterials = securityMaterials;
		this.securityNotesPerLot = securityNotesPerLot;
		this.pageLead = pageLead;
		this.setPlateTime = setPlateTime;
		this.repairTime = repairTime;
	}

	public int getSetPlateTime() {
		return setPlateTime;
	}


	public int getRepairTime() {
		return repairTime;
	}


	public ItemList<NamedItemStack> getBindingMaterials() {
		return bindingMaterials;
	}


	public ItemList<NamedItemStack> getPageMaterials() {
		return pageMaterials;
	}

	public ItemList<NamedItemStack> getSecurityMaterials() {
		return securityMaterials;
	}

	public ItemList<NamedItemStack> getRepairMaterials() {
		return repairMaterials;
	}
	
	public ItemList<NamedItemStack> getPlateMaterials() {
		return plateMaterials;
	}


	public static PrintingFactoryProperties fromConfig(FactoryModPlugin plugin, ConfigurationSection configPrintingFactoryes) {
		ItemList<NamedItemStack> ppFuel=ItemList.fromConfig(configPrintingFactoryes.getConfigurationSection("fuel"));
		if(ppFuel.isEmpty())
		{
			ppFuel=new ItemList<NamedItemStack>();
			ppFuel.add(new NamedItemStack(Material.getMaterial("COAL"),1,(short)1,"Charcoal"));
		}
		ConfigurationSection costs = configPrintingFactoryes.getConfigurationSection("costs");
		ItemList<NamedItemStack> ppConstructionCost=ItemList.fromConfig(costs.getConfigurationSection("construction"));
		ItemList<NamedItemStack> ppRepairCost=ItemList.fromConfig(costs.getConfigurationSection("repair"));
		ItemList<NamedItemStack> ppPlateCost=ItemList.fromConfig(costs.getConfigurationSection("plates"));
		ItemList<NamedItemStack> ppBindingCost=ItemList.fromConfig(costs.getConfigurationSection("binding"));
		ItemList<NamedItemStack> ppPageCost=ItemList.fromConfig(costs.getConfigurationSection("page_lot"));
		int pagesPerLot = costs.getInt("pages_per_lot",16); 
		ItemList<NamedItemStack> ppPamphletCost=ItemList.fromConfig(costs.getConfigurationSection("pamphlet_lot"));
		int pamphletsPerLot = costs.getInt("pamphlets_per_lot",24);
		ItemList<NamedItemStack> ppSecurityCost=ItemList.fromConfig(costs.getConfigurationSection("security_lot"));
		int securityNotesPerLot = costs.getInt("security_notes_per_lot",24);
		int ppEnergyTime = configPrintingFactoryes.getInt("fuel_time", 10);
		int ppRepair = costs.getInt("repair_multiple",1);
		String ppName = configPrintingFactoryes.getString("name", "Printing Factory");
		int paperRate = configPrintingFactoryes.getInt("paper_rate",3);
		int pageLead = configPrintingFactoryes.getInt("page_lead",12);
		int setPageTime = configPrintingFactoryes.getInt("set_page_time",20);
		int repairTime = configPrintingFactoryes.getInt("repair_time",12);
		Structure structure = FactoryModPlugin.getManager().getStructureManager().getStructure(configPrintingFactoryes.getString("structure","RecipeFactory"));
		ConfigurationSection interactionPointsConfiguration = configPrintingFactoryes.getConfigurationSection("interaction_points");
		Map<String, Offset> interactionPoints = new HashMap<String, Offset>();
		if (interactionPointsConfiguration != null) {
			for (String interactionPoint : interactionPointsConfiguration.getKeys(false)) {
				interactionPoints.put(interactionPoint, Offset.fromConfig(interactionPointsConfiguration.getConfigurationSection(interactionPoint)));
			}
		}
		return new PrintingFactoryProperties("printing_press", structure, interactionPoints,
			ppFuel, ppConstructionCost, ppRepairCost, ppPlateCost, ppBindingCost, ppPageCost, pagesPerLot, ppPamphletCost, pamphletsPerLot, ppSecurityCost,
			securityNotesPerLot, ppEnergyTime, ppName, ppRepair, paperRate, pageLead, setPageTime, repairTime);
	}


	public int getMaxRepair()
	{
		return repair;
	}
	
	public int getPagesPerLot() {
		return pagesPerLot;
	}


	public ItemList<NamedItemStack> getPamphletMaterials() {
		return pamphletMaterials;
	}


	public int getPamphletsPerLot() {
		return pamphletsPerLot;
	}


	public int getSecurityNotesPerLot() {
		return securityNotesPerLot;
	}


	public ItemList<NamedItemStack> getFuel()
	{
		return fuel;
	}
	

	public ItemList<NamedItemStack> getConstructionMaterials() {
		return constructionMaterials;
	}
}
