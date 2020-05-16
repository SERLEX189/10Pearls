package com.tenPearls.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenPearls.apirest.dao.IClientDao;
import com.tenPearls.apirest.dao.IVisitDao;
import com.tenPearls.apirest.model.Client;
import com.tenPearls.apirest.model.Visit;


@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;


	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}
		

	@Override
	@Transactional
	public Client save(Client client) {
		Long availableCredit = client.getCreditLimit();
		client.setAvailableCredit(availableCredit);
		return clientDao.save(client);	
	}
	
	@Override
	@Transactional
	public Client update(Client client) {
		Client clientOld =clientDao.findById(client.getNit()).orElse(null);

		if (clientOld.getCreditLimit()<(client.getCreditLimit())) {
			client.setAvailableCredit(
					clientOld.getAvailableCredit()+(
					 client.getCreditLimit() - clientOld.getCreditLimit() ));
		}else if (clientOld.getCreditLimit() > (client.getCreditLimit())) {
			client.setAvailableCredit(
					clientOld.getAvailableCredit()-(
					 clientOld.getCreditLimit() - client.getCreditLimit() ));
		}else {
			client.setAvailableCredit(clientOld.getAvailableCredit());
		}
		return clientDao.save(client);	
	}
	

	@Override
	@Transactional(readOnly = true)
	public Client findByNit(String nit) {
		return clientDao.findById(nit).orElse(null);
	}

	@Override
	@Transactional
	public void delete(String nit) {
		clientDao.deleteById(nit);
		
	}

}
