package com.tenPearls.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.City;

public interface ICityDao extends CrudRepository<City, Long>{
	
	@Query("SELECT c.cityName  FROM City c  where c.idCity=?1")
	public String nameCityById(Long idCity);
}
