package com.github.igotyou.FactoryMod.utility;

import java.util.ArrayList;
import java.util.List;

public class SoundCollection {
	/**
	 * Power On Sound
	 */
	public static Sound getPowerOnSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("random.bowhit");
		soundNames.add("random.breath");
		soundNames.add("damage.fallbig");

		return new Sound(soundNames);
	}

	/**
	 * Power Off Sound
	 */
	public static Sound getPowerOffSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("mob.silverfish.kill");
		soundNames.add("note.bass");
		soundNames.add("random.click");

		return new Sound(soundNames);
	}

	/**
	 * Creation Sound
	 */
	public static Sound getCreationSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("liquid.swim");
		soundNames.add("mob.irongolem.hit");
		soundNames.add("mob.zombie.metal");
		soundNames.add("mob.blaze.hit");

		return new Sound(soundNames);
	}

	/**
	 * Destruction Sound
	 */
	public static Sound getDestructionSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("fireworks.largeBlast_far");
		soundNames.add("mob.endermen.portal");
		soundNames.add("mob.enderdragon.hit");

		return new Sound(soundNames);
	}

	/**
	 * Upgrade Sound
	 */
	public static Sound getUpgradeSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("note.pling");
		soundNames.add("fireworks.launch1");
		soundNames.add("random.levelup");
		soundNames.add("random.glass1");

		return new Sound(soundNames);
	}

	/**
	 * Cloaking Sound
	 */
	public static Sound getCloakingSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("minecart.base");
		soundNames.add("mob.wither.idle3");
		soundNames.add("fire.fire");
		soundNames.add("random.breath");
		soundNames.add("mob.zombie.unfect");
		soundNames.add("mob.silverfish.step1");

		return new Sound(soundNames, 0.2f, 1.0f);
	}

	/**
	 * Placement Sound
	 */
	public static Sound getPlacementSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("random.click");
		soundNames.add("mob.magmacube.big");
		soundNames.add("mob.irongolem.walk1");

		return new Sound(soundNames);
	}

	/**
	 * Error Sound
	 */
	public static Sound getErrorSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("note.bassattack");
		soundNames.add("fire.ignite");
		soundNames.add("dig.grass2");

		return new Sound(soundNames);
	}

	/**
	 * Refuel Sound
	 */
	public static Sound getRefuelSound()
	{
		List<String> soundNames = new ArrayList<String>();
		soundNames.add("note.pling");

		return new Sound(soundNames);
	}
}
