package com.github.igotyou.FactoryMod.interfaces;

import com.github.igotyou.FactoryMod.Factorys.BaseFactory.FactoryCategory;
import com.github.igotyou.FactoryMod.utility.Anchor;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import com.github.igotyou.FactoryMod.utility.Structure;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Factory {
		
	public Location getLocation();
	
	public Structure getStructure();
	
	public Orientation getOrientation();
	
	public Anchor getAnchor();

	public boolean exists();
	
	public boolean isWhole();
	
	public void interactionResponse(Player player, Location location);
	
	public FactoryCategory getFactoryCategory();
	
	public void blockBreakResponse();
	
	public abstract void update();
}
