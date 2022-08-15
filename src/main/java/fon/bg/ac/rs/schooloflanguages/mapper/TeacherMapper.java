package fon.bg.ac.rs.schooloflanguages.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fon.bg.ac.rs.schooloflanguages.dto.CityDto;
import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;

public class TeacherMapper implements GenericMapper<TeacherDto, Teacher>{
	
	private CourseMapper courseMapper;
	private CityMapper cityMapper;
	
	public TeacherMapper() {
		courseMapper=new CourseMapper();
		cityMapper=new CityMapper();
	}
	
	@Override
	public Teacher toEntity(TeacherDto dto) {
		Teacher t=new Teacher();
		t.setFirstName(dto.getFirst_name());
		t.setLastName(dto.getLast_name());
		t.setAddress(dto.getAddress());
		t.setContact(dto.getContact());
		CityDto c=dto.getCity();
		City ci=cityMapper.toEntity(c);
		t.setCity(ci);
		t.setId(dto.getId());
		t.setCourses(dto.getCourses().stream().map((course)->{
			return courseMapper.toEntity(course);
		}).collect(Collectors.toList()));
		return t;
	}

	@Override
	public TeacherDto toDto(Teacher e) {
		TeacherDto t=new TeacherDto();
		t.setFirst_name(e.getFirstName());
		t.setLast_name(e.getLastName());
		t.setAddress(e.getAddress());
		t.setContact(e.getContact());
		t.setCity(cityMapper.toDto(e.getCity()));
		t.setId(e.getId());
		t.setCourses(e.getCourses().stream().map((course)->{
			return courseMapper.toDto(course);
		}).collect(Collectors.toList()));
		return t;
	}

}
