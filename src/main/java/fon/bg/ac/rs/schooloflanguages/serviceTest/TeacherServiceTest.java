package fon.bg.ac.rs.schooloflanguages.serviceTest;

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
public class TeacherServiceTest {
	@Autowired
	private TeacherRepository teacherRepository;

	public List<Teacher> getAll() {
		List<Teacher> teachers= teacherRepository.findAll();
		return teachers;
	}

	public Teacher saveNew(Teacher t) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findByFirstNameAndLastName(t.getFirstName(), t.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Teacher alredy exist!");
		}
		try {
			t=teacherRepository.save(t);
			return t;
		} catch (Exception e) {
			throw new ErrorException("Contact must be unique!");
		}
		
	}

	public Teacher one(Long id) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Teacher can't be found!");
		}
		return optional.get();
	}

	public Teacher update(Long id, Teacher te) throws ErrorException {
		Teacher t=one(id);
		t.setFirstName(te.getFirstName());
		t.setLastName(te.getLastName());
		t.setAddress(te.getAddress());
		t.setCity(te.getCity());
		t.setCourses(te.getCourses());
		return teacherRepository.save(t);
	}

	public List<Teacher> find(String likePattern) {
		List<Teacher> teachers=new ArrayList<>();
		String pattern="%"+likePattern+"%";
		teachers=teacherRepository.findByFirstNameLike(pattern);
		if(teachers.isEmpty()) {
			List<Teacher> prazna=new ArrayList<>();
			return prazna;
		}
		return teachers;
	}
}
