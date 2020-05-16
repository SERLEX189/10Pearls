package com.tenPearls.apirest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenPearls.apirest.Dto.CityDto;
import com.tenPearls.apirest.Dto.CountryDto;
import com.tenPearls.apirest.Dto.StateDto;
import com.tenPearls.apirest.dao.ICityDao;
import com.tenPearls.apirest.dao.ICountryDao;
import com.tenPearls.apirest.dao.IStateDao;
import com.tenPearls.apirest.model.City;
import com.tenPearls.apirest.model.Country;
import com.tenPearls.apirest.model.State;


@Service
public class RegionServiceImpl implements IRegionService	 {

	@Autowired
	private ICityDao cityDao;
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private IStateDao stateDao;


	@Override
	@Transactional(readOnly = true)
	public List<CityDto> findAllCities() {
		
		List<CityDto> cities =new ArrayList<CityDto>();
		 
		List<City> Modelcities =new ArrayList<City>();
		Modelcities= (List<City>) cityDao.findAll();
		
		for (City city : Modelcities) {
			CityDto cityDto=new CityDto();
			cityDto.setLabel(city.getCityName());
			cityDto.setValue(city.getIdCity());
			cities.add(cityDto);
		}
		return cities;
	}


	@Override
	public List<StateDto> findAllStates() {
		List<StateDto> states =new ArrayList<StateDto>();
		 
		List<State> modelStates =new ArrayList<State>();
		modelStates= (List<State>) stateDao.findAll();
		
		for (State state : modelStates) {
			StateDto stateDto =new StateDto();
			stateDto.setLabel(state.getStateName());
			stateDto.setValue(state.getIdState());
			states.add(stateDto);
		}
		return states;
	}


	@Override
	public List<CountryDto> findAllCountries() {
		List<CountryDto> countries =new ArrayList<CountryDto>();
		 
		List<Country> modelCopuntries =new ArrayList<Country>();
		modelCopuntries= (List<Country>) countryDao.findAll();
		
		for (Country country : modelCopuntries) {
			CountryDto countryDto =new CountryDto();
			countryDto.setLabel(country.getCountryName());
			countryDto.setValue(country.getIdCountry());
			countries.add(countryDto);
		}
		return countries;
	}
		

}
