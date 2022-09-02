package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CityTest {

	City c;
	static Teacher t;
	static List<Teacher> teachers;
	
	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		t=new Teacher();
		t.setId(1L);
		t.setFirstName("Pera");
		t.setLastName("Peric");
		t.setContact("0647890015");
		teachers=new ArrayList<>();
		teachers.add(t);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		c=new City();
	}

	@AfterEach
	void tearDown() throws Exception {
		c=null;
	}

	@DisplayName("Test za parametrizovani konstruktor")
	@Test
	void testCityLongStringIntListOfTeacher() {
		c=new City(1L,"Smederevska Palanka",11420,teachers);
		assertEquals(c.getId(), 1L);
		assertEquals(c.getName(), "Smederevska Palanka");
		assertEquals(c.getPTT(), 11420);
		assertEquals(c.getTeachers(), teachers);
	}
	@DisplayName("Test za setovanje atributa Teachers-OK")
	@Test
	void testSetTeachers() {
		c.setTeachers(teachers);
		assertEquals(c.getTeachers(), teachers);
	}
	
	@DisplayName("Test za setovanje atributa Teachers-Lista je null")
	@Test
	void testSetTeachersNull() {
		List<Teacher> teachers=null;
		assertThrows(java.lang.NullPointerException.class,
				() -> c.setTeachers(teachers) );
	}
	
	@DisplayName("Test za setovanje atributa Teachers-Lista je prazna")
	@Test
	void testSetTeachersEmpty() {
		List<Teacher> teachers=new ArrayList<>();
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> c.setTeachers(teachers) );
	}
	
	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		c.setId(28L);
		assertEquals(c.getId(), 28L);
	}
	
	@DisplayName("Test za setovanje atributa Id-Id je manji od 1")
	@Test
	void testSetIdSmallerThan0() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> c.setId(0L) );
	}

	@DisplayName("Test za setovanje atributa Name-OK")
	@Test
	void testSetName() {
		c.setName("Smederevska Palanka");
		assertEquals(c.getName(), "Smederevska Palanka");
	}
	
	@DisplayName("Test za setovanje atributa Name-Name je null")
	@Test
	void testSetNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> c.setName(null) );
	}
	
	@DisplayName("Test za setovanje atributa Name-Name nema bar tri slova")
	@Test
	void testSetNameNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> c.setName("Bo") );
	}

	@DisplayName("Test za setovanje atributa PTT")
	@Test
	void testSetPTT() {
		c.setPTT(11420);
		assertEquals(c.getPTT(), 11420);
	}

	@DisplayName("Test za setovanje atributa PTT-PTT manji od 11000")
	@Test
	void testSetSmallerThan11000() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> c.setPTT(8000) );
	}
	
	@DisplayName("Test za setovanje atributa PTT-PTT veci od 38999")
	@Test
	void testSetBiggerThan11000() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> c.setPTT(40000) );
	}
}
