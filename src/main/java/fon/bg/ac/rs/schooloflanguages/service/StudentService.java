package fon.bg.ac.rs.schooloflanguages.service;

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
public class StudentService {
	
	/**
	 * Repozitorijum za entitet Student
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Maper za entitet Student
	 */
	private StudentMapper studentMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se samo inicijalizuje vrednost za Student maper.
	 */
	public StudentService() {
		studentMapper=new StudentMapper();
	}
	
	/**
	 * <h1>Metoda koja vraca sve Studente iz baze i mapira ih u StudentDto objekte.</h1>
	 * 
	 * @return Listu StudentDto objekata koji predstavljaju sve Studente iz baze.
	 */
	public List<StudentDto> getAll() {
		List<Student> studenti=studentRepository.findAll();
		if(studenti.isEmpty()) {
			List<StudentDto> prazna=new ArrayList<>();
			return prazna;
		}
		return studenti.stream().map((student)-> {
			return studentMapper.toDto(student);
		}).collect(Collectors.toList());
	}

	/**
	 * <h1>Metoda koja brise zadatog Studenta iz baze</h1>
	 * @param id - Id studenta kojeg treba obrisati
	 * @return - ResponseEntitt.Ok()
	 * @throws ErrorException - Ukoliko student sa tim id-jem ne postoji
	 */
	public ResponseEntity<Map<String, Boolean>> delete(Long id) throws ErrorException {
		Optional<Student> course=studentRepository.findById(id);
		if(!course.isPresent()) {
			throw new ErrorException("Student doesn't exist!");
		}
		studentRepository.delete(course.get());
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	/**
	 * <h1>Metoda koja cuva novog Studenta u bazu</h1>
	 * @param entity - Novi student kojeg treba sacuvati
	 * @return - Sacuvanog studenta kao StudentDto objekat
	 * @throws ErrorException - Ukoliko Studetn sa tim imenom i prezimenom vec postoji
	 */
	public StudentDto save(Student entity) throws ErrorException {
		Optional<Student> optional=studentRepository.findByFirstNameAndLastName(entity.getFirstName(), entity.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Student alredy exist!");
		}
		entity=studentRepository.save(entity);
		return studentMapper.toDto(entity);
	}
}
