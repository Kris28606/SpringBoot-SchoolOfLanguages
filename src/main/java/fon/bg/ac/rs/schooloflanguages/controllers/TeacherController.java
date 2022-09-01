package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.RequestDto;
import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.TeacherMapper;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.service.TeacherService;

/**
 * <h1>Kontroler za entitet Teacher</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("teacher")
@CrossOrigin("http://localhost:4200")
public class TeacherController {
	/**
	 * Servis za entitet Teacher
	 */
	@Autowired
	private TeacherService teacherService;
	/**
	 * Maper za entiter Teacher.
	 * Pretvara objekte iz TeacherDto u Teacher i obrnuto.
	 */
	private TeacherMapper teacherMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se samo inicijalizuje vrednost za Teacher mapper
	 */
	public TeacherController() {
		teacherMapper=new TeacherMapper();
	}
	
	/**
	 * <h1>End point za vracanje svih predavaca koji postoje u bazi </h1>
	 * <p>Get ruta "teacher/all"</p>
	 * @return Listu svih predavaca koji postoje u bazi kao Dto objekte
	 */
	@GetMapping("all")
	public List<TeacherDto> VratiSve() {
		return teacherService.getAll();
	}
	
	/**
	 * <h1>End point za cuvanje novog predavaca u bazi </h1>
	 * <p>Post ruta - "teacher/new"</p>
	 * @param t Novi Predavac koji treba da se sacuva-Dto objekat
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(TeacherDto)-ukoliko je Predavac uspesno sacuvan</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Predavac nije sacuvan</li>
	 * </ul>
	 */
	@PostMapping("new")
	public ResponseEntity<Object> Sacuvaj(@RequestBody TeacherDto t) {
		try {
			Teacher te=teacherMapper.toEntity(t);
			return ResponseEntity.ok(teacherService.saveNew(te));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za vracanje jednog predavaca na osnovu id-ja </h1>
	 * <p>Get ruta - "teacher/one/id"</p>
	 * @param id - Id predavaca kojeg treba pronaci u bazi
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(TeacherDto)-ukoliko je Predavac pronadjen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Predavac nije pronadjen</li>
	 * </ul>
	 */
	@GetMapping("one/{id}")
	public ResponseEntity<Object> UcitajJedan(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(teacherService.one(id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za izmenu postojeceg predavaca u bazi </h1>
	 * <p>Put ruta - "teacher/update/id"</p>
	 * @param id - Id predavaca za izmenu
	 * @param t - Izmenjen predavac
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(TeacherDto)-ukoliko je Predavac uspesno izmenjen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Predavac nije izmenjen</li>
	 * </ul>
	 */
	@PutMapping("update/{id}")
	public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody TeacherDto t) {
		try {
			Teacher te=teacherMapper.toEntity(t);
			return ResponseEntity.ok(teacherService.update(id, te));
		}catch (ErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	/**
	 * <h1>End point za vracanje svih predavaca koji zadovoljavaju zadati kriterijum </h1>
	 * <p>Post ruta - "teacher/find"</p>
	 * @param zahtev - Objekat koji sadrzi String vrednost koja predstavlja kriterijum
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(List TeacherDto)-ukoliko su Predavaci pronadjeni</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko upit nije izvrsen</li>
	 * </ul>
	 */
	@PostMapping("find")
	public ResponseEntity<Object> FindTeahers(@RequestBody RequestDto zahtev) {
		try {
			return ResponseEntity.ok(teacherService.find(zahtev.getKriterijum()));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
