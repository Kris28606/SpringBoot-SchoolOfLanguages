package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;

/**
 * Maper koji predstavlja implementaciju Generickog mapera za entitet Kurs.
 * 
 * @author Kristina
 *
 */
public class CourseMapper implements GenericMapper<CourseDto, Course>{
	
	/**
	 * Transformise Kurs dto u Kurs entitet.
	 * 
	 * @param dto - Kurs dto
	 * @return Kurs entitet
	 */
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
	
	/**
	 * Transformise Kurs entitet u Kurs dto.
	 * 
	 * @param e - Kurs entitet
	 * @return Kurs dto
	 */
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
