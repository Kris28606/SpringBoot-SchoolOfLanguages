package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {

	Student s;

	@BeforeEach
	void setUp() throws Exception {
		s=new Student();
	}

	@AfterEach
	void tearDown() throws Exception {
		s=null;
	}

	@DisplayName("Test za parametrizovani konstruktor")
	@Test
	void testStudentLongStringStringTimestampGender() {
		Timestamp datum=new Timestamp(1661560744293L);
		s=new Student(2L,"Kristina","Stanisavljevic",datum, Gender.Female);
		
		assertEquals(s.getId(), 2L);
		assertEquals(s.getFirstName(), "Kristina");
		assertEquals(s.getLastName(), "Stanisavljevic");
		assertEquals(s.getDatumRodjenja(), datum);
		assertEquals(s.getGender(), Gender.Female);
	}

	@DisplayName("Test za setovanje atributa Gender")
	@Test
	void testSetGender() {
		s.setGender(Gender.Female);
		assertEquals(s.getGender(), Gender.Female);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		s.setId(12L);
		assertEquals(s.getId(), 12L);
	}

	@DisplayName("Test za setovanje atributa FirstName")
	@Test
	void testSetFirstName() {
		s.setFirstName("Kristina");
		assertEquals(s.getFirstName(), "Kristina");
	}

	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		s.setLastName("Stanisavljevic");
		assertEquals(s.getLastName(), "Stanisavljevic");
	}

	@DisplayName("Test za setovanje atributa DatumRodjenja")
	@Test
	void testSetDatumRodjenja() {
		Timestamp datum=new Timestamp(1661560744293L);
		s.setDatumRodjenja(datum);
		assertEquals(s.getDatumRodjenja(), datum);
	}

	@DisplayName("Test za setovanje atributa Courses")
	@Test
	void testSetCourses() {
		Course k=new Course();
		k.setId(1L);
		k.setName("Engleski jezik");
		List<Course> kursevi=new ArrayList<>();
		kursevi.add(k);
		s.setCourses(kursevi);
		assertEquals(s.getCourses(), kursevi);
	}

}
