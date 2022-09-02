package fon.bg.ac.rs.schooloflanguages.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.User;
import fon.bg.ac.rs.schooloflanguages.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTestTest {
	
	@MockBean
	public UserRepository userRepository;
	@Autowired
	@InjectMocks
	public UserServiceTest userService;

	@DisplayName("Test za login usera-OK")
	@Test
	void testLogIn() throws ErrorException {
		User u1=new User();
		u1.setUsername("kiki");
		u1.setPassword("kiki2806");
		
		given(userRepository.findByUsernameAndPassword(u1.getUsername(),u1.getPassword())).willReturn(Optional.of(u1));
		
		User expected=userService.logIn(u1);
		
	    assertThat(expected).isNotNull();
	     
	    assertEquals(expected, u1);
	}

	@DisplayName("Test za login usera-User ne postoji")
	@Test
	void testLogInUserDoesntExist() {
		User u2=new User();
		u2.setUsername("pera");
		u2.setPassword("pera1304");
	
		given(userRepository.findByUsernameAndPassword(u2.getUsername(),u2.getPassword())).willReturn(Optional.empty());
	
		assertThrows(ErrorException.class,() -> {
            userService.logIn(u2);
        });
	}
}
