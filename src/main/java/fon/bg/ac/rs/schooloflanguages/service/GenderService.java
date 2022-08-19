package fon.bg.ac.rs.schooloflanguages.service;

import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.Gender;

@Service
public class GenderService {
	
	public Gender[] getAll() {
		return Gender.values();
	}
}
