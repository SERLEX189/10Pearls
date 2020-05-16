package com.tenPearls.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tenPearls.apirest.model.Visit;
import com.tenPearls.apirest.report.VisitReport;

public interface IVisitDao extends CrudRepository<Visit, Long>{
	
//	private Long cityId;
//	private String cityName;
//	private Long visitNumber;
	
	
	@Query("SELECT DISTINCT v.cityId , COUNT(v) FROM Visit v GROUP BY  v.cityId")
	public List<Object[]> visitsbyCity();
}
