package fon.bg.ac.rs.schooloflanguages.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
import fon.bg.ac.rs.schooloflanguages.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	private StudentMapper studentMapper;
	
	public StudentService() {
		studentMapper=new StudentMapper();
	}
}
