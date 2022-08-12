package fon.bg.ac.rs.schooloflanguages.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.TeacherMapper;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	private TeacherMapper teacherMapper;
	
	public TeacherService() {
		teacherMapper=new TeacherMapper();
	}

	public List<TeacherDto> getAll() {
		List<Teacher> teachers= teacherRepository.findAll();
		return teachers.stream().map((teacher)-> {
			return teacherMapper.toDto(teacher);
		}).collect(Collectors.toList());
	}

	public TeacherDto saveNew(Teacher t) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findByFirstNameAndLastName(t.getFirstName(), t.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Teacher alredy exist!");
		}
		try {
			t=teacherRepository.save(t);
			return teacherMapper.toDto(t);
		} catch (Exception e) {
			throw new ErrorException("Contact must be unique!");
		}
		
	}
}
