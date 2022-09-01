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

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.RequestDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.CourseMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.service.CourseService;


/**
 * <h1>Kontroler za entitet Course</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("course")
@CrossOrigin("http://localhost:4200")
public class CourseController {
	
	/**
	 * Servis za entitet Course
	 */
	@Autowired
	private CourseService courseService;
	
	/**
	 * Maper za entiter Course.
	 * Pretvara objekte iz CourseDto u Course i obrnuto.
	 */
	private CourseMapper courseMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se samo inicijalizuje vrednost za Course mapper
	 */
	public CourseController(){
		courseMapper=new CourseMapper();
	}
	
	
	/**
	 * <h1>End point za vracanje svih kurseva koji postoje u bazi </h1>
	 * <p>Get ruta "course/all"</p>
	 * @return Listu svih kurseva koji postoje u bazi kao Dto objekte
	 * @throws Exception ukoliko upit ne moze da se izvrsi
	 */
	@GetMapping("all")
	public List<CourseDto> VratiSve() throws Exception {
		return courseService.getAll();
	}
	
	/**
	 * <h1>End point za cuvanje novog kursa u bazi </h1>
	 * <p>Post ruta - "course/new"</p>
	 * @param course Novi kurs koji treba da se sacuva-Dto objekat
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(CourseDto)-ukoliko je Kurs uspesno sacuvan</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Kurs nije sacuvan</li>
	 * </ul>
	 */
	@PostMapping("new")
	public ResponseEntity<Object> SacuvajNovi(@RequestBody CourseDto course){
		try {
			Course c=courseMapper.toEntity(course);
			return ResponseEntity.ok(courseService.createNew(c));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/*@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> ObrisiKurs(@PathVariable("id") Long id){
		try {
			return courseService.deleteCourse(id);
		}catch(ErrorException ex) {
			Map<String, Boolean> response=new HashMap<>();
			response.put("not deleted", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}*/
	
	/**
	 * <h1>End point za izmenu postojeceg kursa u bazi </h1>
	 * <p>Put ruta - "course/id"</p>
	 * @param id - Id kursa za izmenu
	 * @param course - Izmenjen kursa
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(CourseDto)-ukoliko je Kurs uspesno izmenjen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Kurs nije izmenjen</li>
	 * </ul>
	 */
	@PutMapping("{id}")
	public ResponseEntity<Object> Izmeni(@PathVariable("id") Long id, @RequestBody CourseDto course) {
		try {
			Course c=courseMapper.toEntity(course);
			return ResponseEntity.ok(courseService.update(c, id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za vracanje jednog kursa na osnovu id-ja </h1>
	 * <p>Get ruta - "course/one/id"</p>
	 * @param id - Id kursa kojeg treba pronaci u bazi
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(CourseDto)-ukoliko je Kurs pronadjen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Kurs nije pronadjen</li>
	 * </ul>
	 */
	@GetMapping("one/{id}")
	public ResponseEntity<Object> UcitajJedan(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(courseService.one(id));
		}catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za vracanje svih kurseva koji zadovoljavaju zadati kriterijum </h1>
	 * <p>Post ruta - "course/find"</p>
	 * @param request - Objekat koji sadrzi String vrednost koja predstavlja kriterijum
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(List CourseDto )-ukoliko su Kursevi pronadjeni</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko upit nije izvrsen</li>
	 * </ul>
	 */
	@PostMapping("find")
	public ResponseEntity<Object> Nadji(@RequestBody RequestDto request) {
		try {
			return ResponseEntity.ok(courseService.find(request.getKriterijum()));
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
