package com.github.igotyou.FactoryMod.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

/**
 *
 * @author Brian Landry
 */
public class ProbabilisticEnchantment {
	private String name;
	private Enchantment enchantment;
	private int level;
	private double probability;

	public ProbabilisticEnchantment(String name, String enchantment,Integer level,double probability){
		this.name=name;
		this.enchantment=Enchantment.getByName(enchantment);
		this.level=level.intValue();
		this.probability=probability;
	}
		
	public String getName()
	{
		return name;
	}
	
	public Enchantment getEnchantment()
	{
		return enchantment;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public double getProbability()
	{
		return probability;
	}
	
	public static List<ProbabilisticEnchantment> listFromConfig(ConfigurationSection enchantmentsConfig)
	{
		List<ProbabilisticEnchantment> enchantments=new ArrayList<ProbabilisticEnchantment>();
		if(enchantmentsConfig!=null)
		{
			for(String name:enchantmentsConfig.getKeys(false))
			{
				enchantments.add(ProbabilisticEnchantment.fromConfig(name,enchantmentsConfig.getConfigurationSection(name)));
			}
		}
		return enchantments;
	}
	
	public static ProbabilisticEnchantment fromConfig(String name, ConfigurationSection enchantmentConfig)
	{
		String type=enchantmentConfig.getString("type");
		if (type!=null)
		{
			int level=enchantmentConfig.getInt("level",1);
			double probability=enchantmentConfig.getDouble("probability",1.0);
			return new ProbabilisticEnchantment(name,type,level,probability);
		}
		else
		{
			return null;
		}
	}
}
