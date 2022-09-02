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

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Gender;
import fon.bg.ac.rs.schooloflanguages.model.Student;

class StudentMapperTest {

	public static Student s;
	public static StudentDto dto;
	public static StudentMapper mapper;
	public static CourseMapper courseMapper;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s=new Student();
		courseMapper=new CourseMapper();
		mapper=new StudentMapper();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/06/1999");
		long time = date.getTime();
		Timestamp datum=new Timestamp(time);
		s.setDatumRodjenja(datum);
		s.setFirstName("Pera");
		s.setLastName("Peric");
		s.setId(1L);
		
		dto=new StudentDto();
		dto.setDatumRodjenja(datum);
		dto.setFirstName("Pera");
		dto.setLastName("Peric");
		dto.setId(1L);
		dto.setGender(Gender.Male);
		
		Date dateStartDate = dateFormat.parse("15/12/2022");
		long timeStart = dateStartDate.getTime();
		Timestamp datumStart=new Timestamp(timeStart);
		
		Date dateEndDate = dateFormat.parse("15/02/2023");
		long timeEnd = dateEndDate.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		
		Course k=new Course();
		k.setId(1L);
		k.setPrice(new BigDecimal(18000));
		k.setName("Engleski jezik");
		k.setStartDate(datumStart);
		k.setEndDate(datumEnd);
		CourseDto kDto=courseMapper.toDto(k);
		
		List<Course> kursevi=new ArrayList<>();
		kursevi.add(k);
		
		List<CourseDto> kurseviDto=new ArrayList<>();
		kurseviDto.add(kDto);
		
		s.setCourses(kursevi);
		dto.setCourses(kurseviDto);
		
	}

	@DisplayName("Test za pretvaranje iz StudentDto objekta u Student")
	@Test
	void testToEntity() {
		Student stu=mapper.toEntity(dto);
		
		assertEquals(stu.getFirstName(),dto.getFirstName());
		assertEquals(stu.getLastName(), dto.getLastName());
		assertEquals(stu.getId(), dto.getId());
		assertEquals(stu.getCourses().get(0).getName(), s.getCourses().get(0).getName());
		assertEquals(stu.getGender(), dto.getGender());
		assertEquals(stu.getDatumRodjenja(), dto.getDatumRodjenja());
	}

	@DisplayName("Test za pretvaranje iz Student objekta u StudentDto")
	@Test
	void testToDto() {
		StudentDto sDto=mapper.toDto(s);
		
		assertEquals(sDto.getFirstName(),s.getFirstName());
		assertEquals(sDto.getLastName(), s.getLastName());
		assertEquals(sDto.getId(), s.getId());
		assertEquals(sDto.getCourses().get(0).getName(), dto.getCourses().get(0).getName());
		assertEquals(sDto.getGender(), s.getGender());
		assertEquals(sDto.getDatumRodjenja(), s.getDatumRodjenja());
	}

}
