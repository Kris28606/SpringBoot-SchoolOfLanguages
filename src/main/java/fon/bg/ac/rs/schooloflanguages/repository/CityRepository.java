package fon.bg.ac.rs.schooloflanguages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.City;

/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Grad.</h1>
 * <p>Nasledjuje JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
