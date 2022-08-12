package fon.bg.ac.rs.schooloflanguages.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.CourseMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	private CourseMapper courseMapper;
	
	public CourseService(){
		courseMapper=new CourseMapper();
	}

	public List<CourseDto> getAll() throws Exception {
		List<Course> courses=courseRepository.findAll();
		return courses.stream().map((entity)->{
			return courseMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	public CourseDto createNew(Course course) throws ErrorException {
		Optional<Course> optional=courseRepository.findByName(course.getName());
		if(optional.isPresent()) {
			throw new ErrorException("Course alredy exist!");
		}
		course=courseRepository.save(course);
		return courseMapper.toDto(course);
	} 

	public ResponseEntity<Map<String, Boolean>> deleteCourse(Long id) throws ErrorException {
		Optional<Course> course=courseRepository.findById(id);
		if(!course.isPresent()) {
			throw new ErrorException("Course doesn't exist!");
		}
		courseRepository.delete(course.get());
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
