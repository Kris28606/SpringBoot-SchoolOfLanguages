package fon.bg.ac.rs.schooloflanguages.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import fon.bg.ac.rs.schooloflanguages.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet Predavac.
 * Sadrzi Id, Ime, Prezime, Adresu, Kontakt, Grad i listu kurseva na kojima predaje Predavac.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto implements Dto{
	
	/**
	 * Id predavac - jedinstveni identifikator Predavaca u bazi
	 */
	@NotNull
	private Long id;
	
	/**
	 * Ime predavaca
	 */
	@NotEmpty(message="First name is required field!")
	private String first_name;
	
	/**
	 * Prezime predavaca
	 */
	@NotEmpty(message="Last name is required field!")
	private String last_name;
	
	/**
	 * Adresa predavaca
	 */
	@NotEmpty(message="Address is required field!")
	private String address;
	
	/**
	 * Kontakt predavaca
	 */
	@NotEmpty(message="Contact is required field!")
	private String contact;
	
	/**
	 * Mesto iz kog je Predavac
	 */
	@NotNull(message="City is required field!")
	private CityDto city;
	
	/**
	 * Kursevi na kojima predaje Predavac
	 */
	@NotEmpty(message="Courses is required field!")
	private List<CourseDto> courses;
	
	/**
	 * Vraca Id predavaca
	 * 
	 * @return Id predavac kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja Id za predavaca
	 * 
	 * @param id novi Id predavaca kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Ime predavaca
	 * 
	 * @return Ime predavaca kao String
	 */
	public String getFirst_name() {
		return first_name;
	}
	
	/**
	 * Postavlja vrednost za Ime predavaca
	 * 
	 * @param first_name novo Ime predavaca kao String
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	/**
	 * Vraca Prezime predavaca
	 * 
	 * @return Prezime predavaca kao String
	 */
	public String getLast_name() {
		return last_name;
	}
	
	/**
	 * Postavlja vrednost za Prezime predavaca
	 * 
	 * @param last_name novo Prezime predavaca kao String
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	/**
	 * Vraca Adresu predavaca
	 * 
	 * @return Adresu predavaca kao String
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Postavlja vrednost za Adresu predavaca
	 * 
	 * @param address nova Adresa predavaca kao String
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Vraca Kontakt predavaca
	 * 
	 * @return Kontakt predavaca kao String
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Postavlja vrednost za Kontakt predavaca
	 * 
	 * @param contact novi Kontakt predavaca kao String
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Vraca Grad iz kojeg je predavaca
	 * 
	 * @return Grad predavaca kao tip Grad
	 */
	public CityDto getCity() {
		return city;
	}
	
	/**
	 * Postavlja vrednost za Grad predavaca
	 * 
	 * @param city novi Grad predavaca kao tip Grad
	 */
	public void setCity(CityDto city) {
		this.city = city;
	}
	
	/**
	 * Vraca kurseve na kojima predavac predaje
	 * 
	 * @return Listu kurseva na kojima predaje predavac
	 */
	public List<CourseDto> getCourses() {
		return courses;
	}
	
	/**
	 * Postavlja vrednost za listu kurseva na kojima predaje predavac
	 * 
	 * @param courses nova Lista kurseva na kojima predaje predavac
	 */
	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

}
