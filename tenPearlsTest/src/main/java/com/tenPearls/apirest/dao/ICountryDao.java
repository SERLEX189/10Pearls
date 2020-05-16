package com.tenPearls.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.Country;

public interface ICountryDao extends CrudRepository<Country, Long>{
	

}
