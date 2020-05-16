package com.tenPearls.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.State;

public interface IStateDao extends CrudRepository<State, Long>{
	

}
