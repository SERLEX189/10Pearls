package com.tenPearls.apirest.service;

import java.util.List;

import com.tenPearls.apirest.model.Client;
import com.tenPearls.apirest.model.Visit;


public interface IClientService {

	public List<Client> findAll();

	public Client save(Client Client);
	public Client update(Client client); 

	public Client findByNit(String nit);
	
	public void delete(String nit);

}
