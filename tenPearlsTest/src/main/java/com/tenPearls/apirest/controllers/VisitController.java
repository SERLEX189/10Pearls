package com.tenPearls.apirest.controllers;

import java.util.ArrayList;
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

import com.tenPearls.apirest.Dto.SaleRepresentDto;
import com.tenPearls.apirest.Dto.StateDto;
import com.tenPearls.apirest.model.Client;
import com.tenPearls.apirest.model.Visit;
import com.tenPearls.apirest.report.VisitReport;
import com.tenPearls.apirest.service.IClientService;
import com.tenPearls.apirest.service.IVisitService;


//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/visit")
@CrossOrigin("*")
public class VisitController {
	

	@Autowired
	IVisitService visitService;
	
	
	@GetMapping("/SalesRepresent")
	public List<SaleRepresentDto> getSalesRepresent() {
		return visitService.findAllSaleRepresets();
	}

	@GetMapping("/report")
	public ResponseEntity<?> visitByCity(){


		List<VisitReport> visitReportList=new ArrayList<VisitReport>(); 
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			visitReportList =visitService.visitByCity();
		} catch (Exception e) {
			response.put("mensaje", "The visit report could not be generated.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<List<VisitReport>>(visitReportList, HttpStatus.OK);
	}
	

}
