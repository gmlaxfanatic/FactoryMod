package com.github.igotyou.FactoryMod.persistence;

import java.util.List;

import com.github.igotyou.FactoryMod.Factorys.IFactory;

public interface IFactoryReader<T extends IFactory> {
	
	public List<T> read();

}
