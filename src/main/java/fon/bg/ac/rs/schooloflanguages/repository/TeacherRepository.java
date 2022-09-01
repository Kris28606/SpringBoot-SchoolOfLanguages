package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Teacher;

/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Predavac.</h1>
 * <p>Nasledjuje i prosiruje JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	/**
	 * <h1>Metoda koja pretrazuje Predavaca po imenu i prezimenu.</h1>
	 * 
	 * @param firstName Ime predavaca po kojem se pretrazuje
	 * @param lastName Prezime predavaca po kojem se pretrazuje
	 * @return Optional koji je prazan (ukoliko ne postoji Predavac sa tim imenom i prezimenom) ili sadrzi Predavaca.
	 */
	Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * <h1>Metoda koja pronalazi Predavace cije ime zadovoljava uneti kriterijum.</h1>
	 * 
	 * @param kriterijumIme String vrednost koja predstvlja kriterijum za pretragu.
	 * @return Optional koji je prazan (ukoliko ne postoji Predavac sa tim imenom) ili sadrzi Predavaca.
	 */
	List<Teacher> findByFirstNameLike(String kriterijumIme);

}
