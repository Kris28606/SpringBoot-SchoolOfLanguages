package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;

public class CourseMapper implements GenericMapper<CourseDto, Course>{

	@Override
	public Course toEntity(CourseDto dto) {
		Course c=new Course();
		c.setId(dto.getId());
		c.setName(dto.getName());
		c.setPrice(dto.getPrice());
		c.setStartDate(dto.getStartDate());
		c.setEndDate(dto.getEndDate());
		return c;
	}

	@Override
	public CourseDto toDto(Course e) {
		CourseDto c=new CourseDto();
		c.setId(e.getId());
		c.setName(e.getName());
		c.setPrice(e.getPrice());
		c.setStartDate(e.getStartDate());
		c.setEndDate(e.getEndDate());
		return c;
	}

}
