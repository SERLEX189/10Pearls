package com.tenPearls.apirest.service;

import java.util.List;

import com.tenPearls.apirest.Dto.CityDto;
import com.tenPearls.apirest.Dto.CountryDto;
import com.tenPearls.apirest.Dto.StateDto;


public interface IRegionService {

	public List<CityDto> findAllCities();
	public List<StateDto> findAllStates();
	public List<CountryDto> findAllCountries();


}
