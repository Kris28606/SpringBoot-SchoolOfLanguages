package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
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
	void testCourseLongStringBigDecimalTimestampTimestamp() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		
		k=new Course(14L,"Kurs spanskog jezika",new BigDecimal(18500.00),datumStart,datumEnd );
	
		assertEquals(k.getId(), 14L);
		assertEquals(k.getName(), "Kurs spanskog jezika");
		assertEquals(k.getPrice(), new BigDecimal(18500.00));
		assertEquals(k.getStartDate(), datumStart);
		assertEquals(k.getEndDate(), datumEnd);
	}

	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		k.setId(26L);
		assertEquals(k.getId(), 26L);
	}
	
	@DisplayName("Test za setovanje atributa Id-Id manji od 0")
	@Test
	void testSetIdSmallerThan0() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> k.setId(0L) );
	}

	@DisplayName("Test za setovanje atributa Name-OK")
	@Test
	void testSetName() {
		k.setName("Kurs engleskog jezika");
		assertEquals(k.getName(), "Kurs engleskog jezika");
	}
	
	@DisplayName("Test za setovanje atributa Name-Name je null")
	@Test
	void testSetNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> k.setName(null) );
	}
	
	@DisplayName("Test za setovanje atributa Name-Name nema bar pet slova")
	@Test
	void testSetNameShort() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> k.setName("Kurs") );
	}

	@DisplayName("Test za setovanje atributa Price-OK")
	@Test
	void testSetPrice() {
		k.setPrice(new BigDecimal(15000.00));
		assertEquals(k.getPrice(), new BigDecimal(15000.00));
	}
	
	@DisplayName("Test za setovanje atributa Price-Price je null")
	@Test
	void testSetPriceNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> k.setPrice(null) );
	}
	
	@DisplayName("Test za setovanje atributa Price-Price je manja od 5000din")
	@Test
	void testSetPriceSmallerThan5000() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> k.setPrice(new BigDecimal(4500)) );
	}

	@DisplayName("Test za setovanje atributa StartDate-OK")
	@Test
	void testSetStartDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		k.setStartDate(datumStart);
		assertEquals(k.getStartDate(), datumStart);
	}
	
	@DisplayName("Test za setovanje atributa StartDate-StartDate je null")
	@Test
	void testSetStartDateNull() throws ParseException {
		assertThrows(java.lang.NullPointerException.class,
				() -> k.setStartDate(null) );
	}
	
	@DisplayName("Test za setovanje atributa StartDate-StartDate je proslost")
	@Test
	void testSetStartDateBefore() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/04/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> k.setStartDate(datumStart) );
	}

	@DisplayName("Test za setovanje atributa EndDate-OK")
	@Test
	void testSetEndDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		Date dateStart = dateFormat.parse("28/10/2022");
		long timeStrat = dateStart.getTime();
		Timestamp datumStart=new Timestamp(timeStrat);
		k.setStartDate(datumStart);
		k.setEndDate(datumEnd);
		assertEquals(k.getEndDate(), datumEnd);
	}
	
	@DisplayName("Test za setovanje atributa EndDate-EndDate je null")
	@Test
	void testSetEndDateNull() throws ParseException {
		assertThrows(java.lang.NullPointerException.class,
				() -> k.setEndDate(null) );
	}
	
	@DisplayName("Test za setovanje atributa EndDate-EndDate je pre StartDate")
	@Test
	void testSetEndDateBeforeStart() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		k.setStartDate(datumStart);
		Date dateEnd = dateFormat.parse("28/09/2022");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> k.setEndDate(datumEnd) );
	}

}
