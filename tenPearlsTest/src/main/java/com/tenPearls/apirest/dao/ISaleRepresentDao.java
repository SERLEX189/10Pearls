package com.tenPearls.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.SaleRepresentative;

public interface ISaleRepresentDao extends CrudRepository<SaleRepresentative, Long>{
	
}
