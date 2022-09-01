package fon.bg.ac.rs.schooloflanguages.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceTestTest {

	@MockBean
	public StudentRepository studentRepository;
	@Autowired
	@InjectMocks
	public StudentServiceTest studentService;
	
	public static Student s1;
	public static Student s2;
	public static List<Student> students;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s1=new Student();
		s1.setFirstName("Kristina");
		s1.setLastName("Stanisavljevic");
		s1.setId(1L);
		s2=new Student();
		s2.setFirstName("Pera");
		s2.setLastName("Peric");
		s2.setId(2L);
		students=new ArrayList<>();
		students.add(s1);
		students.add(s2);
	}

	@DisplayName("Test za vracanje svih studenata")
	@Test
	void testGetAll() {
		when(studentService.getAll()).thenReturn(students);
		assertEquals(studentService.getAll().size(), 2);
	}

	@DisplayName("Test za cuvanje novog studenta-OK")
	@Test
	void testSave() throws ErrorException {
		
		given(studentRepository.findById(s1.getId())).willReturn(Optional.empty());
		given(studentRepository.save(s1)).willAnswer(invocation -> invocation.getArgument(0));
		
		Student saved=studentService.save(s1);
		
		assertThat(saved).isNotNull();
		
		verify(studentRepository).save(any(Student.class));
		
		assertEquals(saved, s1);
	}
	
	@DisplayName("Test za cuvanje novog studenta-Student vec postoji")
	@Test
	void testSaveAlredyExist() {
		given(studentRepository.findByFirstNameAndLastName(s1.getFirstName(), s1.getLastName())).willReturn(Optional.of(s1));
		
		assertThrows(ErrorException.class,() -> {
            studentService.save(s1);
        });

        verify(studentRepository, never()).save(any(Student.class));
	}
	@Test
	void testDelete() throws ErrorException {
		given(studentRepository.findById(s1.getId())).willReturn(Optional.of(s1));
		
		studentService.delete(1L);
		
		verify(studentRepository, times(1)).delete(s1);
			
	}
	
	@Test
	void testDeleteNotFound(){
		given(studentRepository.findById(s1.getId())).willReturn(Optional.empty());
	
		assertThrows(ErrorException.class,() -> {
            studentService.delete(s1.getId());
        });
		
		verify(studentRepository, never()).delete(any(Student.class));
	}
	
	

}
