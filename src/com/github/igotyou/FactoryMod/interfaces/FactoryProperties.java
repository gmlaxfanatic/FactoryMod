package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;

public interface FactoryProperties 
{
	public String getName();

	public Structure getStructure(); 
	
	public Map<String,Offset> getInteractionPoints();
	
	public Offset getCreationPoint();
	
	public Set<Material> getInteractionMaterials();
}