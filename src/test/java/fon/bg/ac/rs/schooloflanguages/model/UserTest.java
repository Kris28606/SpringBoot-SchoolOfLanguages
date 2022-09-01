package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	User u;

	@BeforeEach
	void setUp() throws Exception {
		u=new User();
	}

	@AfterEach
	void tearDown() throws Exception {
		u=null;
	}

	@DisplayName("Test za parametrizovani konstruktor")
	@Test
	void testUserLongStringStringStringString() {
		u=new User(1L,"Zika","Zikic","zika","zika");
		
		assertEquals(u.getFirstName(), "Zika");
		assertEquals(u.getLastName(), "Zikic");
		assertEquals(u.getUsername(), "zika");
		assertEquals(u.getPassword(), "zika");
		assertEquals(u.getId(), 1L);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		u.setId(1L);
		assertEquals(u.getId(), 1L);
	}

	@DisplayName("Test za setovanje atributa FirstName")
	@Test
	void testSetFirstName() {
		u.setFirstName("Zika");
		assertEquals(u.getFirstName(), "Zika");
	}

	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		u.setLastName("Zikic");
		assertEquals(u.getLastName(), "Zikic");
	}

	@DisplayName("Test za setovanje atributa Username")
	@Test
	void testSetUsername() {
		u.setUsername("zika");
		assertEquals(u.getUsername(), "zika");
	}

	@DisplayName("Test za setovanje atributa Password")
	@Test
	void testSetPassword() {
		u.setPassword("zika");
		assertEquals(u.getPassword(), "zika");
	}

}
