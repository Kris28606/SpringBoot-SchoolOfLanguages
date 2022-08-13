package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;
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

	public TeacherDto one(Long id) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Teacher can't be found!");
		}
		return teacherMapper.toDto(optional.get());
	}

	public Object update(Long id, Teacher te) throws ErrorException {
		Teacher t=teacherMapper.toEntity(one(id));
		t.setFirstName(te.getFirstName());
		t.setLastName(te.getLastName());
		t.setAddress(te.getAddress());
		t.setCity(te.getCity());
		t.setCourses(te.getCourses());
		return teacherMapper.toDto(teacherRepository.save(t));
	}

	public List<TeacherDto> find(String likePattern) {
		List<Teacher> teachers=new ArrayList<>();
		String pattern="%"+likePattern+"%";
		teachers=teacherRepository.findByFirstNameLike(pattern);
		if(teachers.isEmpty()) {
			List<TeacherDto> prazna=new ArrayList<>();
			return prazna;
		}
		return teachers.stream().map( (teacher)-> {
			return teacherMapper.toDto(teacher);
		}).collect(Collectors.toList());
	}
}
