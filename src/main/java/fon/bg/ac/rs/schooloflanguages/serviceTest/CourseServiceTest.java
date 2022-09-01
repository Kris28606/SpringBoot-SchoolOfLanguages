package fon.bg.ac.rs.schooloflanguages.serviceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.CourseMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.repository.CourseRepository;

/**
 * <h1>Servis za entitet Kurs.</h1>
 * <p>Odgovoran za pozivanje Kurs repozitorijuma i upravljanje podacima iz baze.</p>
 * 
 * @author Kristina
 *
 */
@Service
public class CourseServiceTest {
	
	/**
	 * Repozitorijum za entitet Kurs
	 */
	@Autowired
	private CourseRepository courseRepository;

	/**
	 * <h1>Metoda koja vraca sve Kurseve iz baze i mapira ih u KursDto objekte.</h1>
	 * 
	 * @return Listu KursDto objekata koji predstavljaju sve Kurseve iz baze.
	 * @throws Exception ukoliko upit ne moze da se izvrsi.
	 */
	public List<Course> getAll() throws Exception {
		List<Course> courses=courseRepository.findAll();
		return courses;
	}

	/**
	 * <h1>Metoda koja cuva novi Kurs u bazi podataka.</h1>
	 * <p>Metoda prvo proverava da li u bazi vec 
	 * postoji Kurs sa tim nazivom, ukoliko postoji baca korisnicki definisan izuzetak klase ErrorException.
	 * Uspesno sacuvan Kurs mapira u KursDto i prosledjuje kao rezultat metode.</p>
	 * 
	 * @param course Novi Kurs koji treba da se sacuva.
	 * @return Sacuvani Kurs mapiran u KursDto.
	 * @throws ErrorException Ukoliko Kurs sa istim nazivom vec postoji u bazi.
	 */
	public Course createNew(Course course) throws ErrorException {
		Optional<Course> optional=courseRepository.findByName(course.getName());
		if(optional.isPresent()) {
			throw new ErrorException("Course alredy exist!");
		}
		course=courseRepository.save(course);
		return course;
	} 
	
	/**
	 * <h1>Metoda koja vrsi izmenu postojeceg Kursa.</h1>
	 * <p>Metoda prvo poziva metodu One iz Servisa da pronadje Kurs sa prosledjenim id-jem.
	 * Ukoliko takav Kurs ne postoji baza korisnicki definisan izuzetak klase ErrorException.</p>
	 * <p>Ukoliko Kurs postoji, vrsi izmenu svih podataka i ponovo cuva, tj. update-uje Kurs u bazi.
	 * Kao rezultat vraca izmenjen Kurs mapiran u KursDto.</p>
	 * 
	 * @param course Izmenjen kurs koji treba update-ovati u bazi.
	 * @param id Id Kursa kojeg treba izmeniti.
	 * @return Izmenjen Kurs mapiran u KursDto.
	 * @throws ErrorException Ukoliko Kurs sa tim id-jem ne postoji.
	 */
	public Course update(Course course, Long id) throws ErrorException {
		Course c=one(id);
		c.setName(course.getName());
		c.setStartDate(course.getStartDate());
		c.setEndDate(course.getEndDate());
		c.setPrice(course.getPrice());
		c=courseRepository.save(c);
		return c;
	}
	
	/**
	 * <h1>Metoda koja pronalazi Kurs po zadatom id-u.</h1>
	 * <p>Ukoliko pronadje Kurs u bazi, vraca taj Kurs mapiran u KursDto.</p>
	 * <p>Ukoliko ne postoji Kurs sa tim id-jem, baca korisnicki definisan izuzetak klase ErrorException.</p>
	 * 
	 * @param id Kursa koji se trazi u bazi.
	 * @return Pronadjen Kurs mapiran u KursDto.
	 * @throws ErrorException Ukoliko Kurs sa tim id-jem ne postoji u bazi.
	 */
	public Course one(Long id) throws ErrorException {
		Optional<Course> optional=courseRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Course doesn't exist!!");
		}
		Course c=optional.get();
		return c;
	}
	
	/**
	 * <h1>Pretrazuje Kurseve po zadatom Kriterijumu.</h1>
	 * <p>Ukoliko nijedan Kurs ne ispunjava zadati kriterijum vraca praznu listu.</p>
	 * <p>Ukoliko pronadje Kurseve koji zadovoljavaju kriterijum, mapira ih u KursDto i prosledjuje kao rezultat metode.</p>
	 * 
	 * @param kriterijum String vrednost po kojoj se pretrazuju Kursevi.
	 * @return Listu Kurseva koji zadovoljavaju prosledjeni kriterijum ili praznu listu.
	 */
	public List<Course> find(String kriterijum) {
		String patern="%"+kriterijum+"%";
		List<Course> courses=courseRepository.findByNameLike(patern);
		if(courses.isEmpty()) {
			List<Course> prazna=new ArrayList<>();
			return prazna;
		}
		return courses;
	}
	
}
