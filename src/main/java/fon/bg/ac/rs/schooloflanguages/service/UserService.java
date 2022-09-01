package fon.bg.ac.rs.schooloflanguages.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.UserMapper;
import fon.bg.ac.rs.schooloflanguages.model.User;
import fon.bg.ac.rs.schooloflanguages.repository.UserRepository;

/**
 * <h1>Servis za entitet User.</h1>
 * <p>Odgovoran za pozivanje User repozitorijuma i upravljanje podacima iz baze.</p>
 * @author Kristina
 *
 */
@Service
public class UserService {
	
	/**
	 * Repozitorijum za entitet User
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Maper za entitet User
	 */
	private UserMapper userMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se inicijalizuje vrednost za User maper.
	 */
	public UserService() {
		this.userMapper=new UserMapper();
	}
	
	/**
	 * <h1>Metoda koja sluzi za log in User-a</h1>
	 * <p>Metoda proverava da li User sa tim username-om i password-om postoji u bazi.
	 * Ukoliko ne postoji baca korisnicki definisan izuzetak klase ErrorException. 
	 * Ukoliko postoji vraca pronadjenog Usera kao UserDto objekata. </p>
	 * 
	 * @param u - User koji pokusava da se uloguje.
	 * @return Pronadjenog Usera
	 * @throws ErrorException ukoliko User ne postoji
	 */
	public UserDto logIn(User u) throws ErrorException {
		Optional<User> optional=userRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if(optional.isEmpty()) {
			throw new ErrorException("User doesn't exist!");
		}
		User user=optional.get();
		return userMapper.toDto(user);
	}
}
