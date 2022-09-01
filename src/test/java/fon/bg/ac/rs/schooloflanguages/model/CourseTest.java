package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CourseTest {

	Course k;

	@BeforeEach
	void setUp() throws Exception {
		k=new Course();
	}

	@AfterEach
	void tearDown() throws Exception {
		k=null;
	}

	@DisplayName("Testiranje equals metode")
	@ParameterizedTest
	@CsvSource({
			"Engleski jezik, Engleski jezik, true",
			"Englesk jezik, Nemacki jezik, false",
			"Nemacki jezik, Engleski jezik, false"
	})
	void testEqualsObject(String ime1, String ime2, boolean equals) {
		k.setName(ime1);
		
		Course k2=new Course();
		k2.setName(ime2);
		
		assertEquals(equals, k.equals(k2));
	}

	@DisplayName("Testiranje parametrizovanog konstruktora")
	@Test
	void testCourseLongStringBigDecimalTimestampTimestamp() {
		Timestamp datumStart=new Timestamp(1661558221667L);
		Timestamp datumEnd=new Timestamp(1672558221667L);
		k=new Course(14L,"Kurs spanskog jezika",new BigDecimal(18500.00),datumStart,datumEnd );
	
		assertEquals(k.getId(), 14L);
		assertEquals(k.getName(), "Kurs spanskog jezika");
		assertEquals(k.getPrice(), new BigDecimal(18500.00));
		assertEquals(k.getStartDate(), datumStart);
		assertEquals(k.getEndDate(), datumEnd);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		k.setId(26L);
		assertEquals(k.getId(), 26L);
	}

	@DisplayName("Test za setovanje atributa Name")
	@Test
	void testSetName() {
		k.setName("Kurs engleskog jezika");
		assertEquals(k.getName(), "Kurs engleskog jezika");
	}

	@DisplayName("Test za setovanje atributa Price")
	@Test
	void testSetPrice() {
		k.setPrice(new BigDecimal(15000.00));
		assertEquals(k.getPrice(), new BigDecimal(15000.00));
	}

	@DisplayName("Test za setovanje atributa StartDate")
	@Test
	void testSetStartDate() {
		Timestamp datumStart=new Timestamp(1661558221667L);
		k.setStartDate(datumStart);
		assertEquals(k.getStartDate(), datumStart);
	}

	@DisplayName("Test za setovanje atributa EndDate")
	@Test
	void testSetEndDate() {
		Timestamp datumEnd=new Timestamp(1672558221667L);
		k.setEndDate(datumEnd);
		assertEquals(k.getEndDate(), datumEnd);
	}

}
