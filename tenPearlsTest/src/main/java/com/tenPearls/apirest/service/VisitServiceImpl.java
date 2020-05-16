package com.tenPearls.apirest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenPearls.apirest.Dto.CityDto;
import com.tenPearls.apirest.Dto.SaleRepresentDto;
import com.tenPearls.apirest.dao.ICityDao;
import com.tenPearls.apirest.dao.IClientDao;
import com.tenPearls.apirest.dao.ISaleRepresentDao;
import com.tenPearls.apirest.dao.IVisitDao;
import com.tenPearls.apirest.model.City;
import com.tenPearls.apirest.model.Client;
import com.tenPearls.apirest.model.SaleRepresentative;
import com.tenPearls.apirest.model.Visit;
import com.tenPearls.apirest.report.VisitReport;


@Service
public class VisitServiceImpl implements IVisitService {

	@Autowired
	private IVisitDao visitDao;
	@Autowired
	private IClientDao clientDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private ISaleRepresentDao saleRepresentDao;


	@Override
	@Transactional(readOnly = true)
	public List<Visit> findAll() {
		return (List<Visit>) visitDao.findAll();
	}
		

	@Override
	@Transactional
	public Visit save(Visit visit) {


		Client client=clientDao.findById(visit.getClientNit()).orElse(null);
		Visit visitNew= new Visit();
		
		City city= new City();
		city.setIdCity(client.getCity_id()); 
		
		Long totalVisit=0L;
		Long availableCredit=client.getAvailableCredit();
		
		totalVisit = visit.getNet() * client.getVisitsPercentage();		
		availableCredit=availableCredit- totalVisit;
		
		client.setAvailableCredit(availableCredit);
		
		visitNew.setIdSale(visit.getIdSale());
		visitNew.setNet(visit.getNet());
		visitNew.setCityId(client.getCity_id());
		visitNew.setVisitTotal(totalVisit);
		visitNew.setDescription(visit.getDescription());
		visitNew.setClientNit(visit.getClientNit());
		
		clientDao.save(client);	
		return visitDao.save(visitNew);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Visit findById(Long id) {
		return visitDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		visitDao.deleteById(id);
		
	}


	@Override
	public List<VisitReport> visitByCity() {

		List<Object[]> visitReportList=new ArrayList<Object[]>();
		List<VisitReport> ResultList=new ArrayList<VisitReport>();
		visitReportList = visitDao.visitsbyCity();


		for(Object[] obj : visitReportList){

			VisitReport visitReport=new VisitReport();
			visitReport.setCityId( Long.parseLong(obj[0].toString()));		
			visitReport.setCityName(cityDao.nameCityById(visitReport.getCityId()));
			visitReport.setVisitNumber(Long.parseLong(obj[1].toString()));
			
			ResultList.add(visitReport);

		}
		return ResultList;
	}


	@Override
	@Transactional(readOnly = true)
	public List<SaleRepresentDto> findAllSaleRepresets(){
		
		List<SaleRepresentDto> saleRepresentDtoList  =new ArrayList<SaleRepresentDto>();
		 
		List<SaleRepresentative> saleRepresentModel =new ArrayList<SaleRepresentative>();
		saleRepresentModel= (List<SaleRepresentative>) saleRepresentDao.findAll();
		
		for (SaleRepresentative saleRepresentative : saleRepresentModel) {
			SaleRepresentDto saleRepresentDto=new SaleRepresentDto();
			saleRepresentDto.setLabel(saleRepresentative.getNameSsale());
			saleRepresentDto.setValue(saleRepresentative.getIdSaleRepresentative());
			saleRepresentDtoList.add(saleRepresentDto);
		}
		return saleRepresentDtoList;
	}


}
