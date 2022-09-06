package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;

class CourseMapperTest {

	public static Course c;
	public static CourseDto dto;
	public static CourseMapper mapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mapper=new CourseMapper();
		c=new Course();
		c.setId(1L);
		c.setName("Engleskog jezika");
		c.setPrice(new BigDecimal(15000));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		
		c.setStartDate(datumStart);
		c.setEndDate(datumEnd);
		dto=new CourseDto();
		dto.setId(1L);
		dto.setName("Engleskog jezika");
		dto.setPrice(new BigDecimal(15000));
		dto.setStartDate(datumStart);
		dto.setEndDate(datumEnd);
	}

	@DisplayName("Test za pretvaranje iz CourseDto u Course objekat")
	@Test
	void testToEntity() {
		Course expected=mapper.toEntity(dto);
		
		assertEquals(expected.getId(), dto.getId());
		assertEquals(expected.getName(), dto.getName());
		assertEquals(expected.getPrice(), dto.getPrice());
		assertEquals(expected.getStartDate(), dto.getStartDate());
		assertEquals(expected.getEndDate(), dto.getEndDate());
	}

	@DisplayName("Test za pretvaranje iz Course objekta u CourseDto")
	@Test
	void testToDto() {
		CourseDto expected=mapper.toDto(c);
		
		assertEquals(expected.getId(), c.getId());
		assertEquals(expected.getName(), c.getName());
		assertEquals(expected.getPrice(), c.getPrice());
		assertEquals(expected.getStartDate(), dto.getStartDate());
		assertEquals(expected.getEndDate(), c.getEndDate());
	}

}
