package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.TeacherDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.TeacherMapper;
import fon.bg.ac.rs.schooloflanguages.model.Teacher;
import fon.bg.ac.rs.schooloflanguages.repository.TeacherRepository;

/**
 * <h1>Servis za entitet Predavac.</h1>
 * <p>Odgovoran za pozivanje Predavac repozitorijuma i upravljanje podacima iz baze.</p>
 * @author Kristina
 *
 */
@Service
public class TeacherService {
	/**
	 * Repozitorijum za entitet Predavac
	 */
	@Autowired
	private TeacherRepository teacherRepository;
	
	/**
	 * Maper za entitet Predavac
	 */
	private TeacherMapper teacherMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se inicijalizuje vrednost za Predavac maper.
	 */
	public TeacherService() {
		teacherMapper=new TeacherMapper();
	}

	/**
	 * <h1>Metoda koja vraca sve Predavace iz baze i mapira ih u PredavacDto objekte.</h1>
	 * 
	 * @return Listu PredavacDto objekata koji predstavljaju sve Predavace iz baze.
	 */
	public List<TeacherDto> getAll() {
		List<Teacher> teachers= teacherRepository.findAll();
		return teachers.stream().map((teacher)-> {
			return teacherMapper.toDto(teacher);
		}).collect(Collectors.toList());
	}

	/**
	 * <h1>Metoda koja cuva novog Predavaca u bazi podataka.</h1>
	 * <p>Metoda prvo proverava da li u bazi vec 
	 * postoji Predavac sa tim imenom i prezimenom, ukoliko postoji baca korisnicki definisan izuzetak klase ErrorException.
	 * Takodje ukoliko postoji vec Predavac sa istim kontaktom, takodje baca korisnicki definisan izuzetak klase ErrorException.
	 * Uspesno sacuvanog Predavaca mapira u PredavacDto i prosledjuje kao rezultat metode.</p>
	 * 
	 * @param t Novi Predavac kojeg treba da se sacuva.
	 * @return Sacuvani Predavac mapiran u PredavacDto.
	 * @throws ErrorException Ukoliko Predavac sa istim imeom, prezimenom ili kontaktom vec postoji u bazi.
	 */
	public TeacherDto saveNew(Teacher t) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findByFirstNameAndLastName(t.getFirstName(), t.getLastName());
		if(optional.isPresent()) {
			throw new ErrorException("Teacher alredy exist!");
		}
		try {
			t=teacherRepository.save(t);
			return teacherMapper.toDto(t);
		} catch (Exception e) {
			throw new ErrorException("Contact must be unique!");
		}
		
	}

	/**
	 * <h1>Metoda koja pronalazi Predavaca po zadatom id-u.</h1>
	 * <p>Ukoliko pronadje Predavaca u bazi, vraca tog Predavaca mapiranog u PredavacDto.</p>
	 * <p>Ukoliko ne postoji Predavac sa tim id-jem, baca korisnicki definisan izuzetak klase ErrorException.</p>
	 * 
	 * @param id Predavaca koji se trazi u bazi.
	 * @return Pronadjenog Predavac mapiranpg u PredavacDto.
	 * @throws ErrorException Ukoliko Predavac sa tim id-jem ne postoji u bazi.
	 */
	public TeacherDto one(Long id) throws ErrorException {
		Optional<Teacher> optional=teacherRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Teacher can't be found!");
		}
		return teacherMapper.toDto(optional.get());
	}

	/**
	 * <h1>Metoda koja vrsi izmenu postojeceg Predavaca.</h1>
	 * <p>Vrsi izmenu svih podataka Predavaca i ponovo ga cuva, tj. update-uje Predavaca u bazi.
	 * Kao rezultat vraca izmenjenog Predavaca mapiranog u PredavacDto.</p>
	 * 
	 * @param te Izmenjen predavac kojeg treba update-ovati u bazi.
	 * @param id Id Predavaca kojeg treba izmeniti.
	 * @return Izmenjen Predavac mapiran u TeacherDto.
	 * @throws ErrorException ukoliko upit ne moze da se izvrsi
	 */
	public Object update(Long id, Teacher te) throws ErrorException {
		Teacher t=teacherMapper.toEntity(one(id));
		t.setFirstName(te.getFirstName());
		t.setLastName(te.getLastName());
		t.setAddress(te.getAddress());
		t.setCity(te.getCity());
		t.setCourses(te.getCourses());
		return teacherMapper.toDto(teacherRepository.save(t));
	}

	/**
	 * <h1>Pretrazuje Predavace po zadatom Kriterijumu.</h1>
	 * <p>Ukoliko nijedan Predavac ne ispunjava zadati kriterijum vraca praznu listu.</p>
	 * <p>Ukoliko pronadje Predavace koji zadovoljavaju kriterijum, mapira ih u TeacherDto i prosledjuje kao rezultat metode.</p>
	 * 
	 * @param likePattern String vrednost po kojoj se pretrazuju Predavaci.
	 * @return Listu Predavaca koji zadovoljavaju prosledjeni kriterijum ili praznu listu.
	 */
	public List<TeacherDto> find(String likePattern) {
		List<Teacher> teachers=new ArrayList<>();
		String pattern="%"+likePattern+"%";
		teachers=teacherRepository.findByFirstNameLike(pattern);
		if(teachers.isEmpty()) {
			List<TeacherDto> prazna=new ArrayList<>();
			return prazna;
		}
		return teachers.stream().map( (teacher)-> {
			return teacherMapper.toDto(teacher);
		}).collect(Collectors.toList());
	}
}
