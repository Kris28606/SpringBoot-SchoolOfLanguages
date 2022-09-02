package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.model.User;

class UserMapperTest {
	
	public static UserMapper mapper;
	public static UserDto dto;
	public static User user;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mapper=new UserMapper();
		user=new User();
		user.setFirstName("Kristina");
		user.setId(1L);
		user.setLastName("Stanisavljevic");
		user.setPassword("kiki2806");
		user.setUsername("kiki");
		
		dto=new UserDto();
		dto.setFirstName("Kristina");
		dto.setId(1L);
		dto.setLastName("Stanisavljevic");
		dto.setPassword("kiki2806");
		dto.setUsername("kiki");
	}

	@DisplayName("Test za pretvaranje iz UserDto objekta u User")
	@Test
	void testToEntity() {
		User u=mapper.toEntity(dto);
		
		assertEquals(u.getUsername(), dto.getUsername());
		assertEquals(u.getPassword(), dto.getPassword());
	}

	@DisplayName("Test za pretvaranje iz User objekta u UserDto")
	@Test
	void testToDto() {
		UserDto u=mapper.toDto(user);
		
		assertEquals(u.getFirstName(), user.getFirstName());
		assertEquals(u.getLastName(), user.getLastName());
	}

}
