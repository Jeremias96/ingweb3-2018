package edu.iua.calculator.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable>{

	public int save(T object);
	
	public T findById(ID id);
	
	public List<T> findAll();
	
	//public void delete (T object);
	
	//public void update (T object);
	
}
