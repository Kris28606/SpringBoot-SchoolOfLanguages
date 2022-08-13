package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

	List<Teacher> findByFirstNameLike(String kriterijumIme);

}
