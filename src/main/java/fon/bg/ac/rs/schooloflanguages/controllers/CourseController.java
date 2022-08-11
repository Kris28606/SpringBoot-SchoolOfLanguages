package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.service.CourseService;

@RestController
@RequestMapping("course")
@CrossOrigin
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("all")
	public List<CourseDto> VratiSve() throws Exception {
		return courseService.getAll();
	}
	
	@PostMapping("new")
	public CourseDto SacuvajNovi(@RequestBody Course course) {
		return courseService.createNew(course);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> ObrisiKurs(@PathVariable("id") Long id) throws Exception {
		return courseService.deleteCourse(id);
	}
}
