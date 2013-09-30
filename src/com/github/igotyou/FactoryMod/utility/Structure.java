package com.github.igotyou.FactoryMod.utility;

import com.github.igotyou.FactoryMod.FactoryModPlugin;
import com.github.igotyou.FactoryMod.utility.Anchor.Orientation;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

/**
 * Holds a defined three-dimensional rectangle of specified blocks
 * 
 */
public class Structure {
	
	private byte[][][] blocks;
	//Whether air blocks are a required part of the structure
	private boolean ignoreAir;
	
	public Structure(byte[][][] blocks)
	{
		this.blocks=blocks;
		this.ignoreAir=false;
	}
	/*
	 * Checks if structure exists at a given point 
	 */
	public boolean exists(Location location) {
		for(Orientation orientation:Orientation.values()) {
			if(exists(new Anchor(orientation, location))) { 
				return true;
			}
		}
		return false;
	}
	/*
	 * Checks if structure exists at a given anchor point and given
	 * orientation
	 */
	public boolean exists(Anchor anchor)
	{
		FactoryModPlugin.debugMessage("Blocks size. x:"+blocks.length+", y:"+blocks[0].length+", z:"+blocks[0][0].length);
		FactoryModPlugin.debugMessage("Testing for exisistance at anchor: "+anchor.getLocation().getBlockX()+", "+anchor.getLocation().getY()+", "+ anchor.getLocation().getZ());
		for(int x = 0; x < blocks.length; x++) {
			FactoryModPlugin.debugMessage("Testing X for the "+x+" time.");
			for(int y = 0; y<blocks[x].length; y++) {
				for(int z = 0; z < blocks[x][y].length; z++)
				{
					FactoryModPlugin.debugMessage("Testing Block: "+blocks[x][y][z]);
					//Check if this is not a index contianing air which should be ignored
					if(!(blocks[x][y][z]==0 && ignoreAir)) {
						FactoryModPlugin.debugMessage("Predicted Block: "+Material.getMaterial(blocks[x][y][z]).toString()+". Actual Block: "+anchor.getLocation().clone().add(x*anchor.getXModifier(),y,z*anchor.getZModifier()).getBlock().getType().toString()+". Location: "+anchor.getLocation().clone().add(x*anchor.getXModifier(),y,z*anchor.getZModifier()).toString());
						if(!similiarBlocks(blocks[x][y][z], (byte) anchor.getLocation().clone().add(new Offset(x,y,z).orient(anchor.orientation).toVector()).getBlock().getTypeId())) {
							return false;
						}
					}
				}
			}
		}
		return true;
			
	}
	
	/*
	 * Compares two blocks and checks if they are the same
	 * Works with special cases such as burning furnaces
	 */
	
	private boolean similiarBlocks(byte block, byte otherBlock)
	{
		return block == 61 && otherBlock == 62 || block == 62 && otherBlock == 61 ||block==otherBlock;
	}
	/*
	 * TODO: Checks if a given location is contained within structure
	 */
	public boolean locationContainedInStructure(Anchor anchor, Location location) {
		return true;
	}
	
	/*
	 * Returns a set containing blocks that occur at the following offsets
	 * Should add materials of comparable blocks ie lit furnance
	 */
	public Set<Material> materialsOfOffsets(Collection<Offset> offsets) {
		Set<Material> materials = new HashSet();
		FactoryModPlugin.debugMessage("Offset Size:"+offsets.size());
		for(Offset offset:offsets) {
			FactoryModPlugin.debugMessage(offset.x+","+offset.y+","+offset.z);
			if(validOffset(offset)) {
				FactoryModPlugin.debugMessage("Valid Offset");
				if(!(blocks[offset.x][offset.y][offset.z]==0 && ignoreAir)) {
					materials.add(Material.getMaterial(blocks[offset.x][offset.y][offset.z]));
					
				}
			}
		}
		return materials;
	}
	/*
	 * Checks if the given offset is within the bounds of the structure
	 */
	private boolean validOffset(Offset offset) {
		return offset.x<blocks.length && offset.y<blocks[0].length && offset.z<blocks[0][0].length
			&& offset.x>=0 && offset.y>=0 && offset.z>=0;
	}
	/*
	 * Gets all material use in this schematic
	 */
	public Set<Material> getMaterials(){
		Set<Material> materials=new HashSet<Material>();
		for(short x = 0; x < blocks.length; x++) {
			for(short z = 0; z < blocks[x].length; z++) {
				for(short y = 0; y<blocks[x][z].length; y++) {
					if(!(blocks[x][y][z]==0 && ignoreAir)) {
						materials.add(Material.getMaterial((int) blocks[x][z][y]));
					}
				}
			}		
		}
		return materials;
	}

	/*
	 * Parses a Minecraft schematic file to a structure object
	 */
	
	public static Structure parseSchematic(File file)
	{
		try
		{
			NBTInputStream stream = new NBTInputStream(new FileInputStream(file));
			CompoundTag schematicTag = (CompoundTag) stream.readTag();
			Map<String, Tag> tags = schematicTag.getValue();
			short w = ((ShortTag)tags.get("Width")).getValue();
			short l = ((ShortTag)tags.get("Length")).getValue();
			short h = ((ShortTag)tags.get("Height")).getValue();
			byte[] importedBlocks = ((ByteArrayTag)tags.get("Blocks")).getValue();
			byte[][][] blocks = new byte[w][l][h];
			for(short x = 0; x < w; x++){
				for(short z = 0; z < l; z++){
					for(short y = 0; y < l; y++){
						blocks[x][y][z]=importedBlocks[y * w * l + z * w + x];
						FactoryModPlugin.debugMessage(String.valueOf(blocks[x][y][z]));
					}
				}
			}
			stream.close();
			return new Structure(blocks);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	public int[] getDimensions() {
		return new int[]{blocks.length,blocks[0].length,blocks[0][0].length};
	}
}
