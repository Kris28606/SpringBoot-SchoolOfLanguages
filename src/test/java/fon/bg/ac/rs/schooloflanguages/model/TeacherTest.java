package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeacherTest {

	Teacher t;
	static List<Course> kursevi;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Course k=new Course();
		k.setId(1L);
		k.setName("Spanski jezik");
		kursevi=new ArrayList<>();
		kursevi.add(k);
	}

	@BeforeEach
	void setUp() throws Exception {
		t=new Teacher();
	}

	@AfterEach
	void tearDown() throws Exception {
		t=null;
	}

	@DisplayName("Test za parametrizovani konstruktor")
	@Test
	void testTeacherLongStringStringStringStringCityListOfCourse() {
		City c=new City();
		c.setName("Beograd");
		c.setPTT(11000);
		t=new Teacher(3L,"Pera","Peric","Palanacke cete 14", "0698765437",c,kursevi);
		
		assertEquals(t.getId(), 3L);
		assertEquals(t.getFirstName(), "Pera");
		assertEquals(t.getLastName(), "Peric");
		assertEquals(t.getAddress(), "Palanacke cete 14");
		assertEquals(t.getContact(), "0698765437");
		assertEquals(t.getCity(), c);
		assertEquals(t.getCourses(), kursevi);
	}

	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		t.setId(3L);
		assertEquals(t.getId(), 3L);
	}
	
	@DisplayName("Test za setovanje atributa Id-Id je manji od 1")
	@Test
	void testSetIdSmallerThan1() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setId(0L) );
	}

	@DisplayName("Test za setovanje atributa FirstName-OK")
	@Test
	void testSetFirstName() {
		t.setFirstName("Pera");
		assertEquals(t.getFirstName(), "Pera");
	}
	
	@DisplayName("Test za setovanje atributa FirstName-FirstName je null")
	@Test
	void testSetFirstNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setFirstName(null) );
	}

	@DisplayName("Test za setovanje atributa FirstName-FirstName nema bar tri slova")
	@Test
	void testSetFirstShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setFirstName("Mi") );
	}
	
	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		t.setLastName("Peric");
		assertEquals(t.getLastName(), "Peric");
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName je null")
	@Test
	void testSetLastNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setLastName(null) );
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName nema bar cetiri slova")
	@Test
	void testSetLastNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setLastName("Ili") );
	}

	@DisplayName("Test za setovanje atributa Address-OK")
	@Test
	void testSetAddress() {
		t.setAddress("Palanacke cete 14");
		assertEquals(t.getAddress(), "Palanacke cete 14");
	}
	
	@DisplayName("Test za setovanje atributa Address-Address je null")
	@Test
	void testSetAddressNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setAddress(null) );
	}
	
	@DisplayName("Test za setovanje atributa Address-Address nema bar sest slova")
	@Test
	void testSetAddressShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setAddress("Palan") );
	}

	@DisplayName("Test za setovanje atributa Contact-OK")
	@Test
	void testSetContact() {
		t.setContact("0698765434");
		assertEquals(t.getContact(), "0698765434");
	}
	
	@DisplayName("Test za setovanje atributa Contact-Contact je null")
	@Test
	void testSetContactNULL() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setContact(null) );
	}
	
	@DisplayName("Test za setovanje atributa Contact-Contact ima manje od 10 cifara")
	@Test
	void testSetContactShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setContact("0640482") );
	}
	
	@DisplayName("Test za setovanje atributa Contact-Contact ima vise od 10 cifara")
	@Test
	void testSetContactLong() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setContact("064048281567") );
	}
	
	@DisplayName("Test za setovanje atributa Contact-Contact ne pocinje sa '06..'")
	@Test
	void testSetContact06() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setContact("1234567892") );
	}

	@DisplayName("Test za setovanje atributa City-OK")
	@Test
	void testSetCity() {
		City c=new City();
		c.setName("Beograd");
		c.setPTT(11000);
		t.setCity(c);
		assertEquals(t.getCity(), c);
	}
	
	@DisplayName("Test za setovanje atributa City-City je null")
	@Test
	void testSetCityNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setCity(null) );
	}

	@DisplayName("Test za setovanje atributa Courses")
	@Test
	void testSetCourses() {
		t.setCourses(kursevi);
		assertEquals(t.getCourses(), kursevi);
	}
	
	@DisplayName("Test za setovanje atributa Courses-Courses su null")
	@Test
	void testSetCoursesNUll() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setCourses(null) );
	}
	
	@DisplayName("Test za setovanje atributa Courses-Courses su prazni")
	@Test
	void testSetCoursesEmpty() {
		List<Course> courses=new ArrayList<>();
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> t.setCourses(courses) );
	}

}
