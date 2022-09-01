package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Student;


/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Student.</h1>
 * <p>Nasledjuje i prosiraju JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * <h1>Metoda koja pretrazuje Studenta po imenu i prezimenu.</h1>
	 * 
	 * @param firstName Ime studenta po kojem se pretrazuje
	 * @param lastName Prezime studenta po kojem se pretrazuje
	 * @return Optional koji je prazan (ukoliko ne postoji Student sa tim imenom i prezimenom) ili sadrzi Studenta.
	 */
	Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
	
}
