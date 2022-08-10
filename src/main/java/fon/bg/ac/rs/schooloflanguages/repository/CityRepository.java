package fon.bg.ac.rs.schooloflanguages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
