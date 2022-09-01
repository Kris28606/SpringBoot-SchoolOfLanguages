package fon.bg.ac.rs.schooloflanguages.serviceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.repository.StudentRepository;

/**
 * <h1>Servis za entitet Student.</h1>
 * <p>Odgovoran za pozivanje Student repozitorijuma i upravljanje podacima iz baze.</p>
 * 
 * @author Kristina
 *
 */
@Service
public class StudentServiceTest {
	
	/**
	 * Repozitorijum za entitet Student
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * <h1>Metoda koja vraca sve Studente iz baze i mapira ih u StudentDto objekte.</h1>
	 * 
	 * @return Listu StudentDto objekata koji predstavljaju sve Studente iz baze.
	 */
	public List<Student> getAll() {
		List<Student> studenti=studentRepository.findAll();
		if(studenti.isEmpty()) {
			List<Student> prazna=new ArrayList<>();
			return prazna;
		}
		return studenti;
	}

	public Boolean delete(Long id) throws ErrorException {
		Optional<Student> course=studentRepository.findById(id);
		if(!course.isPresent()) {
			throw new ErrorException("Student doesn't exist!");
		}
		studentRepository.delete(course.get());
		return true;
	}

	public Student save(Student entity) throws ErrorException {
		Optional<Student> optional=studentRepository.findByFirstNameAndLastName(entity.getFirstName(), entity.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Student alredy exist!");
		}
		entity=studentRepository.save(entity);
		return entity;
	}
}
