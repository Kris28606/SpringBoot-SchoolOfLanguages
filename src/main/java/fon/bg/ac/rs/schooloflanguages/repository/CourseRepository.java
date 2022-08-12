package fon.bg.ac.rs.schooloflanguages.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Optional<Course> findByName(String name);

}
