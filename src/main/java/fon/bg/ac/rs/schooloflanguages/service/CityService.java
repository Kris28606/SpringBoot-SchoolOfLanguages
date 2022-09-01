package fon.bg.ac.rs.schooloflanguages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.repository.CityRepository;

/**
 * <h1>Servis za entitet Grad.</h1>
 * <p>Odgovoran za pozivanje Grad repozitorijuma i upravljanje podacima iz baze.</p>
 * 
 * @author Kristina
 *
 */
@Service
public class CityService {
	
	/**
	 * Repozitorijum za entitet Grad
	 */
	@Autowired
	private CityRepository cityRepository;
	
	
	/**
	 * <h1>Metoda koja vraca sve Gradove iz baze</h1>
	 * 
	 * @return Listu gradova koji postoje u bazi
	 */
	public List<City> getAll() {
		return cityRepository.findAll();
	}
}
