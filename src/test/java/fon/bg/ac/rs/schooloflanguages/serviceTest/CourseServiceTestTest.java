package fon.bg.ac.rs.schooloflanguages.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.CourseMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.repository.CourseRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CourseServiceTestTest {

	@MockBean
	public CourseRepository courseRepository;
	
	@Autowired
	@InjectMocks
	public CourseServiceTest courseService;
	
	@InjectMocks
	public CourseMapper mapper;
	
	public static List<Course> kursevi;
	public static Course k;
	public static Course k2;
	
	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		k=new Course();
		k.setId(1L);
		k.setName("Spanski jezik");
		k.setPrice(new BigDecimal(17000));
		k2=new Course();
		k2.setId(2L);
		k2.setName("Engleski jezik");
		k2.setPrice(new BigDecimal(17000));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/12/2022");
		long time = date.getTime();
		Timestamp datum=new Timestamp(time);
		k2.setStartDate(datum);
		k.setStartDate(datum);
		
		Date dateEnd = dateFormat.parse("28/12/2022");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		k2.setEndDate(datumEnd);
		k.setEndDate(datumEnd);
		
		kursevi=new ArrayList<>();
		kursevi.add(k);
		kursevi.add(k2);
	}
	
	@DisplayName("Test za vracanje svih kurseva")
	@Test
	void testGetAll() throws Exception {
		when(courseService.getAll()).thenReturn(kursevi);
			
		assertNotNull(courseService.getAll());
		assertEquals(2,courseService.getAll().size());
		
	}

	@DisplayName("Test za kreiranje novog kursa-OK")
	@Test
	void testCreateNew() throws ErrorException {
		
		given(courseRepository.findByName(k.getName())).willReturn(Optional.empty());
		given(courseRepository.save(k)).willAnswer(invocation -> invocation.getArgument(0));
		
		Course saved=courseService.createNew(k);
		
		assertThat(saved).isNotNull();
		
		verify(courseRepository).save(any(Course.class));
		
		assertEquals(saved, k);
	}
	
	@DisplayName("Test za kreiranje novog kursa-Kurs vec postoji")
	@Test
	void testCreateAlredyExist() {
		given(courseRepository.findByName(k.getName())).willReturn(Optional.of(k));
		
		assertThrows(ErrorException.class,() -> {
            courseService.createNew(k);
        });

        verify(courseRepository, never()).save(any(Course.class));
	
	}

	@DisplayName("Test za azuriranje postojeceg kursa-OK")
	@Test
	void testUpdate() throws ErrorException {
		given(courseRepository.findById(k2.getId())).willReturn(Optional.of(k2));
		given(courseRepository.save(k2)).willAnswer(invocation -> invocation.getArgument(0));
		
		final Course expected = courseService.update(k2, k2.getId());

	    assertThat(expected).isNotNull();
	     
	    assertEquals(expected, k2);

	    verify(courseRepository).save(any(Course.class));

	}
	
	@DisplayName("Test za azuriranje postojeceg kursa-Kurs nije pronadjen")
	@Test
	void testUpdateNotFound() {
		given(courseRepository.findById(k2.getId())).willReturn(Optional.empty());
		
		assertThrows(ErrorException.class,() -> {
            courseService.update(k2, k2.getId());
        });

        verify(courseRepository, never()).save(any(Course.class));
	
	}

	@DisplayName("Test za pronalazenje kursa po id-ju-OK")
	@Test
	void testOne() throws ErrorException {
		given(courseRepository.findById(k.getId())).willReturn(Optional.of(k));
	
		final Course expected = courseService.one(k.getId());

	    assertThat(expected).isNotNull();

	    assertEquals(expected, k);
	    
	    verify(courseRepository).findById(any(Long.class));
	}
	
	@DisplayName("Test za pronalazenje kursa po id-ju-Kurs nije pronadjen")
	@Test
	void testOneNotFound() {
		given(courseRepository.findById(k.getId())).willReturn(Optional.empty());
	
		assertThrows(ErrorException.class,() -> {
            courseService.one(k.getId());
        });
	}

	@DisplayName("Test za pronalazenje kurseva po kriterijumu")
	@Test
	void testFind() {
		String kriterijum="Spa";
		List<Course> courses=new ArrayList<>();
		courses.add(k);
		when(courseService.find(kriterijum)).thenReturn(courses);
		assertEquals(courseService.find(kriterijum).size(), 1);
		assertEquals(courseService.find(kriterijum).get(0), k);
	}

}
