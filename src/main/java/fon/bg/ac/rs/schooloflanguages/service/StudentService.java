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

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	private StudentMapper studentMapper;
	
	public StudentService() {
		studentMapper=new StudentMapper();
	}

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

	public StudentDto save(Student entity) throws ErrorException {
		Optional<Student> optional=studentRepository.findByFirstNameAndLastName(entity.getFirstName(), entity.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Student alredy exist!");
		}
		entity=studentRepository.save(entity);
		return studentMapper.toDto(entity);
	}
}
