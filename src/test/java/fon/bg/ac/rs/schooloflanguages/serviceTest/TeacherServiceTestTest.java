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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TeacherServiceTestTest {

	@MockBean
	public TeacherRepository teacherRepository;
	
	@Autowired
	@InjectMocks
	public TeacherServiceTest teacherService;
	
	public static Teacher t;
	public static Teacher t2;
	public static List<Teacher> teachers;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		t=new Teacher();
		t.setFirstName("Pera");
		t.setLastName("Peric");
		t.setId(1L);
		t.setAddress("Palanacke cete 14");
		t.setContact("0673768902");
		
		City c=new City();
		c.setId(1L);
		c.setName("Smederevska Palanka");
		c.setPTT(11420);
		
		t.setCity(c);
		
		t2=new Teacher();
		t2.setFirstName("Zika");
		t2.setLastName("Zikic");
		t2.setId(2L);
		t2.setAddress("Palanacke cete 14");
		t2.setCity(c);
		t2.setContact("0673768902");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		
		Course k=new Course(14L,"Kurs spanskog jezika",new BigDecimal(18500.00),datumStart,datumEnd );
		List<Course> courses=new ArrayList<>();
		courses.add(k);
		t.setCourses(courses);
		t2.setCourses(courses);
		
		teachers=new ArrayList<>();
		teachers.add(t);
		teachers.add(t2);
	}

	@Test
	void testGetAll() {
		when(teacherService.getAll()).thenReturn(teachers);
		
		assertNotNull(teacherService.getAll());
		assertEquals(2,teacherService.getAll().size());
	}

	@Test
	void testSaveNew() throws ErrorException {
		given(teacherRepository.findByFirstNameAndLastName(t.getFirstName(), t.getLastName())).willReturn(Optional.empty());
		given(teacherRepository.save(t)).willAnswer(invocation -> invocation.getArgument(0));
	
		Teacher saved=teacherService.saveNew(t);
		
		assertThat(saved).isNotNull();
		
		verify(teacherRepository).save(any(Teacher.class));
		
		assertEquals(saved, t);
	}
	
	@Test
	void testSaveNewAlredyExist() throws ErrorException {
		given(teacherRepository.findByFirstNameAndLastName(t.getFirstName(), t.getLastName())).willReturn(Optional.of(t));
	
		assertThrows(ErrorException.class,() -> {
			teacherService.saveNew(t);
        });

        verify(teacherRepository, never()).save(any(Teacher.class));
	}

	@Test
	void testOne() throws ErrorException {
		given(teacherRepository.findById(t.getId())).willReturn(Optional.of(t));
		
		Teacher expected=teacherService.one(t.getId());
		
		assertThat(expected).isNotNull();
		
		verify(teacherRepository).findById(any(Long.class));
		
		assertEquals(expected, t);
	}
	
	@Test
	void testOneNotFound() throws ErrorException {
		given(teacherRepository.findById(t.getId())).willReturn(Optional.empty());
		
		assertThrows(ErrorException.class,() -> {
			teacherService.one(t.getId());
        });
		
		verify(teacherRepository).findById(any(Long.class));
	}
	
	
	@Test
	void testUpdate() throws ErrorException {
		given(teacherRepository.findById(t.getId())).willReturn(Optional.of(t));
		given(teacherRepository.save(t)).willAnswer(invocation -> invocation.getArgument(0));
		
		Teacher expected=teacherService.update(t.getId(), t);
		
		assertThat(expected).isNotNull();
		
		verify(teacherRepository).findById(any(Long.class));
		verify(teacherRepository).save(any(Teacher.class));
		
		assertEquals(expected, t);
	}

	@Test
	void testUpdateNotFound() throws ErrorException {
		given(teacherRepository.findById(t.getId())).willReturn(Optional.empty());
		
		assertThrows(ErrorException.class,() -> {
			teacherService.one(t.getId());
        });
		
		verify(teacherRepository).findById(any(Long.class));
	}
	
	@Test
	void testFind() {
		String kriterijum="Pe";
		List<Teacher> teachers=new ArrayList<>();
		teachers.add(t);
		when(teacherService.find(kriterijum)).thenReturn(teachers);
		assertEquals(teacherService.find(kriterijum).size(), 1);
		assertEquals(teacherService.find(kriterijum).get(0), t);
	}

}
