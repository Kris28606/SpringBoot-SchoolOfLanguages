package fon.bg.ac.rs.schooloflanguages.service;

import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.Gender;

/**
 * <h1>Servis za enum Pol.</h1>
 * 
 * @author Kristina
 *
 */
@Service
public class GenderService {
	
	/**
	 * <h1>Vraca niz svih vrednosti za enum Pol.</h1>
	 * 
	 * @return Niz vrednosti tipa enum Pol.
	 */
	public Gender[] getAll() {
		return Gender.values();
	}
}
