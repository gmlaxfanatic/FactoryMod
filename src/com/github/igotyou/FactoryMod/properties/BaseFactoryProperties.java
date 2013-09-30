package com.github.igotyou.FactoryMod.properties;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.interfaces.FactoryProperties;
import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class BaseFactoryProperties implements FactoryProperties {

	protected String factoryID; //Unique name of the factory
	protected Map<String, Offset> interactionPoints;
	protected String name;
	protected Structure structure;

	public BaseFactoryProperties(String factoryID, Structure structure, Map<String, Offset> interactionPoints, String name) {
		this.factoryID = factoryID;
		this.structure = structure;
		this.interactionPoints = interactionPoints;

		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Structure getStructure() {
		return structure;
	}

	@Override
	public Map<String, Offset> getInteractionPoints() {
		return interactionPoints;
	}

	@Override
	public Set<Material> getInteractionMaterials() {
		return structure.materialsOfOffsets(interactionPoints.values());
	}

	@Override
	public Offset getCreationPoint() {
		return interactionPoints.get("creation");
	}

	protected static BaseFactoryProperties fromConfig(String factoryID, ConfigurationSection configurationSection) {
		factoryID = factoryID.replaceAll(" ", "_");
		String factoryName = configurationSection.getString("name", "Default Name");
		Structure structure = FactoryModPlugin.getManager().getStructureManager().getStructure(configurationSection.getString("structure", "ItemFactory"));
		ConfigurationSection interactionPointsConfiguration = configurationSection.getConfigurationSection("interaction_points");
		Map<String, Offset> interactionPoints = new HashMap<String, Offset>();
		if (interactionPointsConfiguration != null) {
			for (String interactionPoint : interactionPointsConfiguration.getKeys(false)) {
				interactionPoints.put(interactionPoint, Offset.fromConfig(interactionPointsConfiguration.getConfigurationSection(interactionPoint)));
			}
		}
		return new BaseFactoryProperties(factoryID, structure, interactionPoints, factoryName);
	}
}
