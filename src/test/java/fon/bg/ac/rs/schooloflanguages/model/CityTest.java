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
		t.setContact("064789001");
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
	@DisplayName("Test za setovanje atributa Teachers")
	@Test
	void testSetTeachers() {
		c.setTeachers(teachers);
		assertEquals(c.getTeachers(), teachers);
	}
	
	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		c.setId(28L);
		assertEquals(c.getId(), 28L);
	}

	@DisplayName("Test za setovanje atributa Name")
	@Test
	void testSetName() {
		c.setName("Smederevska Palanka");
		assertEquals(c.getName(), "Smederevska Palanka");
	}

	@DisplayName("Test za setovanje atributa PTT")
	@Test
	void testSetPTT() {
		c.setPTT(11420);
		assertEquals(c.getPTT(), 11420);
	}

}
