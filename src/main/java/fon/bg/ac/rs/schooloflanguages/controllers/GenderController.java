package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.Gender;
import fon.bg.ac.rs.schooloflanguages.service.GenderService;

@RestController
@RequestMapping("gender")
@CrossOrigin("http://localhost:4200")
public class GenderController {
	
	@Autowired
	private GenderService genderService;

	@GetMapping("all")
	public Gender[] getAll() {
		return genderService.getAll();
	}
}
