package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.service.TeacherService;

@RestController
@RequestMapping("teacher")
@CrossOrigin
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("all")
	public List<TeacherDto> VratiSve() {
		return teacherService.getAll();
	}
	
	@PostMapping("new")
	public ResponseEntity<Object> Sacuvaj(@RequestBody Teacher t) {
		try {
			return ResponseEntity.ok(teacherService.saveNew(t));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
}
