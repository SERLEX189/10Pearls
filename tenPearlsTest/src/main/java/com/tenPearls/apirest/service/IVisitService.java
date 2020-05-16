package com.tenPearls.apirest.service;

import java.util.List;

import com.tenPearls.apirest.Dto.SaleRepresentDto;
import com.tenPearls.apirest.model.Visit;
import com.tenPearls.apirest.report.VisitReport;


public interface IVisitService {

	public List<Visit> findAll();
	
	public List<SaleRepresentDto> findAllSaleRepresets();

	public Visit save(Visit visit);

	public Visit findById(Long id);
	
	public void delete(Long id);
	
	public List<VisitReport> visitByCity();
	
	

}
