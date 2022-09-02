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
		u=new User(1L,"Zika","Zikic","zika","zika1304");
		
		assertEquals(u.getFirstName(), "Zika");
		assertEquals(u.getLastName(), "Zikic");
		assertEquals(u.getUsername(), "zika");
		assertEquals(u.getPassword(), "zika1304");
		assertEquals(u.getId(), 1L);
	}

	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		u.setId(1L);
		assertEquals(u.getId(), 1L);
	}
	
	@DisplayName("Test za setovanje atributa Id-Id je manji od 1")
	@Test
	void testSetIdSmallerThan1() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setId(0L) );
	}

	@DisplayName("Test za setovanje atributa FirstName-OK")
	@Test
	void testSetFirstName() {
		u.setFirstName("Zika");
		assertEquals(u.getFirstName(), "Zika");
	}
	
	@DisplayName("Test za setovanje atributa FirstName-FirstName je null")
	@Test
	void testSetFirstNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setFirstName(null) );
	}
	
	@DisplayName("Test za setovanje atributa FirstName-FirstName nema bar tri slova")
	@Test
	void testSetFirstNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setFirstName("Ki") );
	}

	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		u.setLastName("Zikic");
		assertEquals(u.getLastName(), "Zikic");
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName je null")
	@Test
	void testSetLastNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setLastName(null) );
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName nema bar cetiri slova")
	@Test
	void testSetLastNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setLastName("Ili") );
	}

	@DisplayName("Test za setovanje atributa Username-OK")
	@Test
	void testSetUsername() {
		u.setUsername("zika");
		assertEquals(u.getUsername(), "zika");
	}
	
	@DisplayName("Test za setovanje atributa Username-Username je null")
	@Test
	void testSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setUsername(null) );
	}

	@DisplayName("Test za setovanje atributa Username-Username nema bar dva slova")
	@Test
	void testSetUsernameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setUsername("k") );
	}
	
	@DisplayName("Test za setovanje atributa Password")
	@Test
	void testSetPassword() {
		u.setPassword("zika1304");
		assertEquals(u.getPassword(), "zika1304");
	}
	
	@DisplayName("Test za setovanje atributa Password-Password je null")
	@Test
	void testSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setPassword(null) );
	}

	@DisplayName("Test za setovanje atributa Password-Password nema bar sest slova")
	@Test
	void testSetPasswordShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setPassword("kiki") );
	}
}
