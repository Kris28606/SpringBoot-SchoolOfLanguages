package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.Gender;
import fon.bg.ac.rs.schooloflanguages.service.GenderService;

/**
 * <h3>Kontroler za enum Gender</h3>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("gender")
@CrossOrigin("http://localhost:4200")
public class GenderController {
	
	/**
	 * Servis za enum Gender
	 */
	@Autowired
	private GenderService genderService;

	/**
	 * <h1>End point za vracanje svih polova koji postoje</h1>
	 * <p>Get ruta "gender/all"</p>
	 * @return Niz svih polova koji postoje
	 */
	@GetMapping("all")
	public Gender[] getAll() {
		return genderService.getAll();
	}
}
