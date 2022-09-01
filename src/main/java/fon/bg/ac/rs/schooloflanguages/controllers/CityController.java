package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.service.CityService;

/**
 * <h1>Kontroler za entitet City</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("city")
@CrossOrigin("http://localhost:4200")
public class CityController {
	
	
	/**
	 * Servis za entitet City
	 */
	@Autowired
	private CityService cityService;
	
	/**
	 * <h1>End point za vracanje svih gradova</h1>
	 * Get ruta "city/all"
	 * @return Listu svih gradova koji postoje u bazi
	 */
	@GetMapping("all")
	public List<City> vratiSve() {
		return cityService.getAll();
	}
}
