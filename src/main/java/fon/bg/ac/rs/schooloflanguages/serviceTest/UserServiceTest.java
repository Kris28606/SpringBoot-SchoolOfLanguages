package fon.bg.ac.rs.schooloflanguages.serviceTest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.UserMapper;
import fon.bg.ac.rs.schooloflanguages.model.User;
import fon.bg.ac.rs.schooloflanguages.repository.UserRepository;

@Service
public class UserServiceTest {
	@Autowired
	private UserRepository userRepository;
	
	public User logIn(User u) throws ErrorException {
		Optional<User> optional=userRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if(optional.isEmpty()) {
			throw new ErrorException("User doesn't exist!");
		}
		User user=optional.get();
		return user;
	}
}
