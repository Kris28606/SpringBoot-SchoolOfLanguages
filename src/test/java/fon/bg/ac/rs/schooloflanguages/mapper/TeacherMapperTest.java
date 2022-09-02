package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.CityDto;
import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;

class TeacherMapperTest {

	public static Teacher t;
	public static TeacherDto dto;
	public static TeacherMapper mapper;
	public static CourseMapper courseMapper;
	public static CityMapper cityMapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		courseMapper=new CourseMapper();
		cityMapper=new CityMapper();
		mapper=new TeacherMapper();
		Course k=new Course();
		k.setId(1L);
		k.setName("Spanski jezik");
		k.setPrice(new BigDecimal(17000));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		k.setStartDate(datumStart);
		k.setEndDate(datumEnd);
		
		CourseDto kDto=courseMapper.toDto(k);
		
		List<Course> kursevi=new ArrayList<>();
		List<CourseDto> kurseviDto=new ArrayList<>();
		kurseviDto.add(kDto);
		kursevi.add(k);
		City c=new City();
		c.setName("Beograd");
		c.setPTT(11000);
		c.setId(1L);
		
		CityDto cDto=cityMapper.toDto(c);
		t=new Teacher(3L,"Pera","Peric","Palanacke cete 14", "0698765436",c,kursevi);
		dto=new TeacherDto();
		dto.setAddress("Palanacke cete 14");
		dto.setCity(cDto);
		dto.setFirst_name("Pera");
		dto.setLast_name("Peric");
		dto.setId(3L);
		dto.setContact("0698765436");
		dto.setCourses(kurseviDto);
	}

	@DisplayName("Test za pretvaranje iz TeacherDto objekta u Teacher")
	@Test
	void testToEntity() {
		Teacher te=mapper.toEntity(dto);
		
		assertEquals(te.getAddress(), dto.getAddress());
		assertEquals(te.getFirstName(), dto.getFirst_name());
		assertEquals(te.getLastName(), dto.getLast_name());
		assertEquals(te.getId(), dto.getId());
		assertEquals(te.getCity().getName(), dto.getCity().getName());
		assertEquals(te.getContact(), dto.getContact());
		assertEquals(te.getCourses().get(0).getName(), dto.getCourses().get(0).getName());
		
	}

	@DisplayName("Test za pretvaranje iz Teacher objekta u TeacherDto")
	@Test
	void testToDto() {
		TeacherDto tDto=mapper.toDto(t);
		
		assertEquals(tDto.getAddress(), t.getAddress());
		assertEquals(tDto.getFirst_name(), t.getFirstName());
		assertEquals(tDto.getLast_name(), t.getLastName());
		assertEquals(tDto.getId(), t.getId());
		assertEquals(tDto.getCity().getName(), t.getCity().getName());
		assertEquals(tDto.getContact(), t.getContact());
		assertEquals(tDto.getCourses().get(0).getName(), dto.getCourses().get(0).getName());
	}

}
