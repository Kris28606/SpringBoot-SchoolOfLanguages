package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
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
	public ResponseEntity<Object> SacuvajNovi(@RequestBody Course course){
		try {
			return ResponseEntity.ok(courseService.createNew(course));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/*@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> ObrisiKurs(@PathVariable("id") Long id){
		try {
			return courseService.deleteCourse(id);
		}catch(ErrorException ex) {
			Map<String, Boolean> response=new HashMap<>();
			response.put("not deleted", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}*/
}
