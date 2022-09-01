package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.service.StudentService;

/**
 * <h1>Kontroler za entitet Student</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("student")
@CrossOrigin("http://localhost:4200")
public class StudentController {
	/**
	 * Servis za entitet Student
	 */
	@Autowired
	private StudentService studentService;
	/**
	 * Mapper za entiter Student koji pretvara objekte iz StudentDto u Student i obrnuto
	 */
	private StudentMapper studentMapper;
	
	/**
	 * Bezparametraski konstruktor u okviru kojeg se samo inicijalizuju vrednosti za student mapera
	 */
	public StudentController() {
		studentMapper=new StudentMapper();
	}
	
	/**
	 * <h1>End point za vracanje svih studenata koji postoje u bazi </h1>
	 * <p>Get ruta "student/all"</p>
	 * @return Listu svih studenata koji postoje u bazi kao StudentDto objekte
	 */
	@GetMapping("all")
	public List<StudentDto> vratiSve() {
		return studentService.getAll();
	}
	
	/**
	 * <h1>End point za brisanje zadatog studenta </h1>
	 * <p>Delete ruta "student/id"</p>
	 * @param id - Id studenta kojeg treba obrisati
	 * @return ResponseEntity - sa informacijom o uspesnosti izvrsenja upita
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> obrisiStudenta(@PathVariable("id") Long id) {
		try {
			return studentService.delete(id);
		} catch (Exception e) {
			Map<String, Boolean> response=new HashMap<>();
			response.put("not deleted", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	/**
	 * <h1>End point za cuvanje novog studenta </h1>
	 * <p>Delete ruta "student/new"</p>
	 * @param student - Novi Student kojeg treba sacuvati
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(StudentDto)-ukoliko je Student uspesno sacuvan</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Student nije sacuvan</li>
	 * </ul>
	 */
	@PostMapping("new")
	public ResponseEntity<Object> sacuvajNovog(@RequestBody StudentDto student){
		try {
			System.out.print(student);
			Student s=studentMapper.toEntity(student);
			return ResponseEntity.ok(studentService.save(s));
		} catch(ErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
