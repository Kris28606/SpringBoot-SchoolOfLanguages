package fon.bg.ac.rs.schooloflanguages.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Course;

/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Kurs.</h1>
 * <p>Nasledjuje i prosiruje JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	/**
	 * <h1>Metoda koja pronalazi Kurs po imenu, ukoliko on postoji.</h1>
	 * 
	 * @param name String vrednost koja predstvlja kriterijum za pretragu.
	 * @return Optional koji je prazan (ukoliko ne postoji Kurs sa tim imenom) ili sadrzi Kurs.
	 */
	Optional<Course> findByName(String name);
	
	/**
	 * <h1>Metoda koja pronalazi Kurs po Id-u, ukoliko on postoji.</h1>
	 * 
	 * @param id int vrednost za Id koja predstavlja kriterijum za pretragu.
	 * @return Optional koji je prazan (ukoliko ne postoji Kurs sa tim Id-jem) ili sadrzi Kurs.
	 */
	Optional<Course> findById(int id);
	
	/**
	 * <h1>Metoda koja pronalazi Kurseve cije ime zadovoljava uneti kriterijum.</h1>
	 * 
	 * @param patern String vrednost koja predstvlja kriterijum za pretragu.
	 * @return Optional koji je prazan (ukoliko ne postoji Kurs sa tim imenom) ili sadrzi Kurs.
	 */
	List<Course> findByNameLike(String patern);

}
