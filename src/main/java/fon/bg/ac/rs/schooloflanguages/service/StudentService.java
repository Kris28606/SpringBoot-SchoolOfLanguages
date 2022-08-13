package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
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
}
