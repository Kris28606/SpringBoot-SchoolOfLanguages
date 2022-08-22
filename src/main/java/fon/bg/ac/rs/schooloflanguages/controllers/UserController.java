package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.UserMapper;
import fon.bg.ac.rs.schooloflanguages.model.User;
import fon.bg.ac.rs.schooloflanguages.service.UserService;

@RestController
@RequestMapping("login")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	private UserMapper userMapper;
	
	public UserController() {
		this.userMapper=new UserMapper();
	}
	
	@PostMapping
	public ResponseEntity<Object> logIn(@RequestBody UserDto u) {
		try {
			User user=userMapper.toEntity(u);
			return ResponseEntity.ok(userService.logIn(user));
		} catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch(Exception es) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(es.getMessage());
		}
	}
}
