package fon.bg.ac.rs.schooloflanguages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getAll() {
		return cityRepository.findAll();
	}
}
