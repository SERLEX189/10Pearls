package com.tenPearls.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenPearls.apirest.Dto.CityDto;
import com.tenPearls.apirest.Dto.CountryDto;
import com.tenPearls.apirest.Dto.StateDto;
import com.tenPearls.apirest.model.Client;
import com.tenPearls.apirest.model.Visit;
import com.tenPearls.apirest.service.IRegionService;
import com.tenPearls.apirest.service.IClientService;
import com.tenPearls.apirest.service.IVisitService;


//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	IClientService clientService;
	@Autowired
	IVisitService visitService;
	
	@Autowired
	IRegionService regionService;	
	
	@GetMapping("/clients")
	public List<Client> index() {
		return clientService.findAll();
	}
	
	@GetMapping("/cities")
	public List<CityDto> getCities() {
		return regionService.findAllCities();
	}
	@GetMapping("/states")
	public List<StateDto> getStates() {
		return regionService.findAllStates();
	}
	@GetMapping("/countries")
	public List<CountryDto> getCountries() {
		return regionService.findAllCountries();
	}
	
	@GetMapping("/client/{nit}")
	public ResponseEntity<?> showClient(@PathVariable String nit){
		/*ResponseEntity es una clase que proporciona SpringFramework
		 * donde se puede pasar a la respuesta al front
		 * un Objeto y un codigo http  200 201 404 etc*/
		Client client=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			client =clientService.findByNit(nit);
		} catch (DataAccessException e) {
			response.put("mensaje", "Database error Searching the Client");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (client==null) {
			response.put("mensaje", "The Nit client: ".concat(nit.
					concat(" Does not exist in the Database")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	

	@PostMapping("/visit")
	public ResponseEntity<?> registerVisit (@Valid @RequestBody Visit visit, BindingResult result){
		
		Visit visitNew =null;
		Client client =null;
		Map<String, Object> response =new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		client =clientService.findByNit(visit.getClientNit());
		if (client == null) {
			response.put("mensaje", "The client of this visit does not exist in Data Base");	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			visitNew = visitService.save(visit);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Could not save the visit in the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "The visit has been registered successfully!");
		response.put("visitNew", visitNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/client")
	public ResponseEntity<?> create (@Valid @RequestBody Client client, BindingResult result){
		Client clientNew =null;
		
		Client clientActual = clientService.findByNit(client.getNit());
		
		Map<String, Object> response =new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (clientActual != null) {
				clientNew = clientService.update(client);
			}else {
				clientNew = clientService.save(client);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Could not save the Client in the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "The Client has been created successfully!");
		response.put("clientNew", clientNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/client/{nit}")
	public ResponseEntity<?> update(@Valid @RequestBody Client client, 
			BindingResult result, @PathVariable String nit) {

		Client clientActual = clientService.findByNit(nit);

		Client clientUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (clientActual == null) {
			response.put("mensaje", "Error: could not edit, the Client Nit: "
					.concat(nit.concat(" does not exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			clientActual.setFullName(client.getFullName());
			clientActual.setAddress(client.getAddress());
			clientActual.setPhone(client.getPhone());		
			clientActual.setCreditLimit(client.getCreditLimit());
			clientActual.setAvailableCredit(client.getCreditLimit());
			clientActual.setVisitsPercentage(client.getVisitsPercentage());
			clientActual.setCity_id(client.getCity_id());
			clientActual.setClientIdState(client.getClientIdState());
			clientActual.setClientIdCountry(client.getClientIdCountry());

			clientUpdated = clientService.update(clientActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Failed to update the Client in the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "The Client has been successfully updated!");
		response.put("Client", clientUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/client/{nit}")
	public ResponseEntity<?> delete(@PathVariable String nit) {
		
		Map<String, Object> response = new HashMap<>();
		Client clientActual = clientService.findByNit(nit);
		
		if (clientActual == null) {
			response.put("mensaje", "Error: could not delete, the Client Nit: "
					.concat(nit.concat(" does not exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		    clientService.delete(nit);
		} catch (DataAccessException e) {
			response.put("mensaje", "Failed to delete the Client from the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "The Client removed successfully!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
