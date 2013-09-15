package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.utility.Offset;
import com.github.igotyou.FactoryMod.utility.Structure;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;

/**
 * Properties.java
 * Purpose: Interface for Properties objects for basic properties functionality
 *
 * @author MrTwiggy
 * @version 0.1 1/17/13
 */
//edited version:
/**
* Properties.java	 
 * Purpose: Interface for Properties objects for basic properties functionality
* @author igotyou
*
*/
public interface Properties 
{
	public String getName();

	public Structure getStructure(); 
	
	public List<Offset> getInteractionPoints();
	
	public Offset getCreationPoint();
	
	public Set<Material> getInteractionMaterials();
}