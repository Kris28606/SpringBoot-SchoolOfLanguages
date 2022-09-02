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
	 * Mesto iz kog je Predavac.
	 * Jedan predavac je iz jednog i samo jednog Grada.
	 */
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	/**
	 * Kursevi na kojima predaje Predavac.
	 * Jedan predavac moze da predaje na vise kurseva.
	 */
	@ManyToMany
	@JoinTable(name = "course_teacher", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	/**
	 * Bezparametarski konstruktor
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
	 * @throws NullPointerException ukoliko je bar jedan od prosledjenih atributa null
	 * @throws IllegalArgumentException ukoliko nisu unete odgovarajuce vrednosti za atribute
	 */
	public Teacher(Long id, String firstName, String lastName, String address, String contact, City city,
			List<Course> courses) {
		super();
		setId(id);
		setAddress(address);
		setFirstName(firstName);
		setLastName(lastName);
		setContact(contact);
		setCity(city);
		setCourses(courses);
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
		if(id<=0) {
			throw new IllegalArgumentException("Id mora biti veci od nule!");
		}
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
	 * @throws NullPointerException ukoliko je novo Ime predavaca null
	 * @throws IllegalArgumentException ukoliko novo Ime predavaca ima manje od tri slova
	 */
	public void setFirstName(String firstName) {
		if(firstName==null) {
			throw new NullPointerException("Ime ne sme biti null!");
		}
		if(firstName.length()<3) {
			throw new IllegalArgumentException("Ime mora da ima bar tri slova!");
		}
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
	 * @throws NullPointerException ukoliko je novo Prezime predavaca null
	 * @throws IllegalArgumentException ukoliko novo Prezime predavaca ima manje od cetiri slova
	 */
	public void setLastName(String lastName) {
		if(lastName==null) {
			throw new NullPointerException("Prezime ne sme biti null!");
		}
		if(lastName.length()<4) {
			throw new IllegalArgumentException("Prezime mora da ima bar cetiri slova!");
		}
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
	 * @throws NullPointerException ukoliko je nova Adresa predavaca null
	 * @throws IllegalArgumentException ukoliko nova Adresa predavaca ima manje od sest slova
	 */
	public void setAddress(String address) {
		if(address==null) {
			throw new NullPointerException("Adresa ne sme biti null!");
		}
		if(address.length()<6) {
			throw new IllegalArgumentException("Adresa mora imati bar sest slova!");
		}
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
	 * @throws NullPointerException ukoliko je novi Kontakt predavaca null
	 * @throws IllegalArgumentException ukoliko novi Kontakt predavaca ima manje od deset cigara
	 * @throws IllegalArgumentException ukoliko novi Kontakt predavaca ima vise od deset slova
	 * @throws IllegalArgumentException ukoliko novi Kontakt predavaca ne pocinje sa '06...'
	 */
	public void setContact(String contact) {
		if(contact==null) {
			throw new NullPointerException("Kontakt ne sme biti null!");
		}
		if(contact.length()<10) {
			throw new IllegalArgumentException("Kontakt mora imati 10 cifara!");
		}
		if(contact.length()>10) {
			throw new IllegalArgumentException("Kontakt mora imati 10 cifara!");
		}
		if(!contact.startsWith("06")) {
			throw new IllegalArgumentException("Kontakt mora da pocinje sa '06...'");
		}
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
	 * @throws NullPointerException ukoliko je novi Grad null
	 */
	public void setCity(City city) {
		if(city==null) {
			throw new NullPointerException("Grad ne sme da bude null!");
		}
		this.city = city;
	}
	
	/**
	 * Vraca kurseve na kojima predavac predaje
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
	 * @throws NullPointerException ukoliko je Lista kurseva null
	 * @throws IllegalArgumentException ukoliko je Lista kurseva prazna
	 */
	public void setCourses(List<Course> courses) {
		if(courses==null) {
			throw new NullPointerException("Lista kurseva ne sme biti null!");
		}
		if(courses.size()<1) {
			throw new IllegalArgumentException("Lista kurseva ne sme biti prazna!");
		}
		this.courses = courses;
	}
	
	
	
}
