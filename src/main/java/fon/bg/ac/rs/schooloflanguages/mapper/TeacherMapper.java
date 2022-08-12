package fon.bg.ac.rs.schooloflanguages.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;

public class TeacherMapper implements GenericMapper<TeacherDto, Teacher>{
	
	private CourseMapper courseMapper;
	
	public TeacherMapper() {
		courseMapper=new CourseMapper();
	}
	
	@Override
	public Teacher toEntity(TeacherDto dto) {
		Teacher t=new Teacher();
		t.setFirst_name(dto.getFirst_name());
		t.setLast_name(dto.getLast_name());
		t.setAddress(dto.getAddress());
		t.setContact(dto.getContact());
		t.setCity(dto.getCity());
		t.setId(dto.getId());
		t.setCourses(dto.getCourses().stream().map((course)->{
			return courseMapper.toEntity(course);
		}).collect(Collectors.toList()));
		return t;
	}

	@Override
	public TeacherDto toDto(Teacher e) {
		TeacherDto t=new TeacherDto();
		t.setFirst_name(e.getFirst_name());
		t.setLast_name(e.getLast_name());
		t.setAddress(e.getAddress());
		t.setContact(e.getContact());
		t.setCity(e.getCity());
		t.setId(e.getId());
		t.setCourses(e.getCourses().stream().map((course)->{
			return courseMapper.toDto(course);
		}).collect(Collectors.toList()));
		return t;
	}

}
