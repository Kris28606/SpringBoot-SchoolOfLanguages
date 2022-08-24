package fon.bg.ac.rs.schooloflanguages.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

/**
 * Domenska klasa koja predstavlja Predavaca na Kursu.
 * Predavac ima Id, Ime, Prezime, Adresu, Kontakt i Mesto.
 * 
 * @author Kristina
 *
 */
@Entity
@Table(name="teacher")
public class Teacher implements MyEntity{
	/**
	 * Id predavac - jedinstveni identifikator Predavaca u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Ime predavaca
	 */
	@NonNull
	private String firstName;
	
	/**
	 * Prezime predavaca
	 */
	@NonNull
	private String lastName;
	/**
	 * Adresa predavaca
	 */
	@NonNull
	private String address;
	
	/**
	 * Kontakt predavaca
	 */
	@Column(unique=true)
	@NonNull
	private String contact;
	/**
	 * Mesto iz kog je Predavac
	 */
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	/**
	 * Kursevi na kojima predaje Predavac
	 */
	@ManyToMany
	@JoinTable(name = "course_teacher", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	/**
	 * Neparametrizovani konstruktor
	 */
	public Teacher() {
		
	}
	
	
	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param id Id predavaca
	 * @param firstName Ime predavaca
	 * @param lastName Prezime predavaca
	 * @param address Adresa predavaca
	 * @param contact Kontakt predavaca
	 * @param city Grad predavaca
	 * @param courses Kursevi na kojima predavac predaje
	 */
	public Teacher(Long id, String firstName, String lastName, String address, String contact, City city,
			List<Course> courses) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contact = contact;
		this.city = city;
		this.courses = courses;
	}


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
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Postavlja vrednost za Ime predavaca
	 * 
	 * @param firstName novo Ime predavaca kao String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * Vraca Prezime predavaca
	 * 
	 * @return Prezime predavaca kao String
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Postavlja vrednost za Prezime predavaca
	 * 
	 * @param lastName novo Prezime predavaca kao String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public City getCity() {
		return city;
	}
	
	/**
	 * Postavlja vrednost za Grad predavaca
	 * 
	 * @param city novi Grad predavaca kao tip Grad
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * Vraca kurseve na koji predavac predaje
	 * 
	 * @return Listu kurseva na kojima predaje predavac
	 */
	public List<Course> getCourses() {
		return courses;
	}
	
	/**
	 * Postavlja vrednost za listu kurseva na kojima predaje predavac
	 * 
	 * @param courses nova Lista kurseva na kojima predaje predavac
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	
}
