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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.RequestDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.CourseMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.service.CourseService;

@RestController
@RequestMapping("course")
@CrossOrigin("http://localhost:4200")
public class CourseController {
	@Autowired
	private CourseService courseService;
	private CourseMapper courseMapper;
	
	public CourseController(){
		courseMapper=new CourseMapper();
	}
	
	@GetMapping("all")
	public List<CourseDto> VratiSve() throws Exception {
		return courseService.getAll();
	}
	
	@PostMapping("new")
	public ResponseEntity<Object> SacuvajNovi(@RequestBody CourseDto course){
		try {
			Course c=courseMapper.toEntity(course);
			return ResponseEntity.ok(courseService.createNew(c));
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
	
	@PutMapping("{id}")
	public ResponseEntity<Object> Izmeni(@PathVariable("id") Long id, @RequestBody CourseDto course) {
		try {
			Course c=courseMapper.toEntity(course);
			return ResponseEntity.ok(courseService.update(c, id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@GetMapping("one/{id}")
	public ResponseEntity<Object> UcitajJedan(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(courseService.one(id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PostMapping("find")
	public ResponseEntity<Object> Nadji(@RequestBody RequestDto request) {
		try {
			return ResponseEntity.ok(courseService.find(request.getKriterijum()));
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
