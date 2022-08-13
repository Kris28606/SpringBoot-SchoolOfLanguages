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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
import fon.bg.ac.rs.schooloflanguages.service.StudentService;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {
	@Autowired
	private StudentService studentService;
	private StudentMapper studentMapper;
	
	public StudentController() {
		studentMapper=new StudentMapper();
	}
	
	@GetMapping("all")
	public List<StudentDto> vratiSve() {
		return studentService.getAll();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> obrisiStudenta(@PathVariable("id") Long id) {
		try {
			return studentService.delete(id);
		} catch (Exception e) {
			Map<String, Boolean> response=new HashMap<>();
			response.put("not deleted", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
