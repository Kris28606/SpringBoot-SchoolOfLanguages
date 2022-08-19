package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.RequestDto;
import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.TeacherMapper;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.service.TeacherService;

@RestController
@RequestMapping("teacher")
@CrossOrigin("http://localhost:4200")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	private TeacherMapper teacherMapper;
	
	public TeacherController() {
		teacherMapper=new TeacherMapper();
	}
	
	@GetMapping("all")
	public List<TeacherDto> VratiSve() {
		return teacherService.getAll();
	}
	
	@PostMapping("new")
	public ResponseEntity<Object> Sacuvaj(@RequestBody TeacherDto t) {
		try {
			Teacher te=teacherMapper.toEntity(t);
			return ResponseEntity.ok(teacherService.saveNew(te));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@GetMapping("one/{id}")
	public ResponseEntity<Object> UcitajJedan(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(teacherService.one(id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody TeacherDto t) {
		try {
			Teacher te=teacherMapper.toEntity(t);
			return ResponseEntity.ok(teacherService.update(id, te));
		}catch (ErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("find")
	public ResponseEntity<Object> FindTeahers(@RequestBody RequestDto zahtev) {
		try {
			return ResponseEntity.ok(teacherService.find(zahtev.getKriterijum()));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
