package fon.bg.ac.rs.schooloflanguages.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAll() {
		return courseRepository.findAll();
	}

	public Course createNew(Course course) {
		return courseRepository.save(course);
	}

	public ResponseEntity<Map<String, Boolean>> deleteCourse(Long id) throws Exception {
		Optional<Course> course=courseRepository.findById(id);
		if(!course.isPresent()) {
			throw new Exception("Course doesn't exist!");
		}
		courseRepository.delete(course.get());
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
