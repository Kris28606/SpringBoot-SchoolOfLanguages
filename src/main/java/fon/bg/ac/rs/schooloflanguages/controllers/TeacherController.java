package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.service.TeacherService;

@RestController
@RequestMapping("teacher")
@CrossOrigin
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("all")
	public List<Teacher> VratiSve() {
		return teacherService.getAll();
	}
}
