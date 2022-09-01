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

/**
 * <h1>Kontroler za entitet User</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("login")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	/**
	 * Servis za entitet User
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Maper za entiter user.
	 * Pretvara objekte iz UserDto u User i obrnuto.
	 */
	private UserMapper userMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se samo inicijalizuje vrednost za User mapper
	 */
	public UserController() {
		this.userMapper=new UserMapper();
	}
	
	/**
	 * <h1>End point za login usera </h1>
	 * <p>Post ruta "login"</p>
	 * @param u - User koji hoce da se uloguje kao UserDto objekat
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(UserDto)-ukoliko je User pronadjen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko User nije pronadjen</li>
	 *  <li>ResponseEntity.InternalServerError()-ukoliko upit ne moze da se izvrsi</li>
	 * </ul>
	 */
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
