package com.tenPearls.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.Client;

public interface IClientDao extends CrudRepository<Client, String>{

}
