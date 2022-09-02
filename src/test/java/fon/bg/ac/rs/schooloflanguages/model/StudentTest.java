package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	void testStudentLongStringStringTimestampGender() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/06/1999");
		long time = date.getTime();
		Timestamp datum=new Timestamp(time);
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

	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		s.setId(12L);
		assertEquals(s.getId(), 12L);
	}
	
	@DisplayName("Test za setovanje atributa Id-Id je manji od 1")
	@Test
	void testSetIdSmallerThan1() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> s.setId(0L) );
	}

	@DisplayName("Test za setovanje atributa FirstName-OK")
	@Test
	void testSetFirstName() {
		s.setFirstName("Kristina");
		assertEquals(s.getFirstName(), "Kristina");
	}
	
	@DisplayName("Test za setovanje atributa FirstName-FirstName je null")
	@Test
	void testSetFirstNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> s.setFirstName(null) );
	}
	
	@DisplayName("Test za setovanje atributa FirstName-FirstName ima manje od tri slova")
	@Test
	void testSetFirstNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> s.setFirstName("Mi") );
	}

	@DisplayName("Test za setovanje atributa LastName")
	@Test
	void testSetLastName() {
		s.setLastName("Stanisavljevic");
		assertEquals(s.getLastName(), "Stanisavljevic");
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName je null")
	@Test
	void testSetLastNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> s.setLastName(null) );
	}
	
	@DisplayName("Test za setovanje atributa LastName-LastName ima manje od cetiri slova")
	@Test
	void testSetLastNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> s.setLastName("Ili") );
	}

	@DisplayName("Test za setovanje atributa DatumRodjenja-OK")
	@Test
	void testSetDatumRodjenja() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/06/1999");
		long time = date.getTime();
		Timestamp datum=new Timestamp(time);
		s.setDatumRodjenja(datum);
		assertEquals(s.getDatumRodjenja(), datum);
	}
	
	@DisplayName("Test za setovanje atributa DatumRodjenja-DatumRodjenja je null")
	@Test
	void testSetDatumRodjenjaNull() throws ParseException {
		assertThrows(java.lang.NullPointerException.class,
				() -> s.setDatumRodjenja(null) );
	}
	
	@DisplayName("Test za setovanje atributa DatumRodjenja-Student je maloletan")
	@Test
	void testSetDatumRodjenja18() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/06/2008");
		long time = date.getTime();
		Timestamp datum=new Timestamp(time);
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> s.setDatumRodjenja(datum) );
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
	
	@DisplayName("Test za setovanje atributa Courses-Courses su null")
	@Test
	void testSetCoursesNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> s.setCourses(null) );
	}

}
