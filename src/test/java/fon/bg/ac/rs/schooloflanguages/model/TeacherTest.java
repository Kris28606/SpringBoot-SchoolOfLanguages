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
		t=new Teacher(3L,"Pera","Peric","Palanacke cete 14", "069876543",c,kursevi);
		
		assertEquals(t.getId(), 3L);
		assertEquals(t.getFirstName(), "Pera");
		assertEquals(t.getLastName(), "Peric");
		assertEquals(t.getAddress(), "Palanacke cete 14");
		assertEquals(t.getContact(), "069876543");
		assertEquals(t.getCity(), c);
		assertEquals(t.getCourses(), kursevi);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		t.setId(3L);
		assertEquals(t.getId(), 3L);
	}

	@DisplayName("Test za setovanje atributa FirstName")
	@Test
	void testSetFirstName() {
		t.setFirstName("Pera");
		assertEquals(t.getFirstName(), "Pera");
	}

	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		t.setLastName("Peric");
		assertEquals(t.getLastName(), "Peric");
	}

	@DisplayName("Test za setovanje atributa Address")
	@Test
	void testSetAddress() {
		t.setAddress("Palanacke cete 14");
		assertEquals(t.getAddress(), "Palanacke cete 14");
	}

	@DisplayName("Test za setovanje atributa Contact")
	@Test
	void testSetContact() {
		t.setContact("069876543");
		assertEquals(t.getContact(), "069876543");
	}

	@DisplayName("Test za setovanje atributa City")
	@Test
	void testSetCity() {
		City c=new City();
		c.setName("Beograd");
		c.setPTT(11000);
		t.setCity(c);
		assertEquals(t.getCity(), c);
	}

	@DisplayName("Test za setovanje atributa Courses")
	@Test
	void testSetCourses() {
		t.setCourses(kursevi);
		assertEquals(t.getCourses(), kursevi);
	}

}
