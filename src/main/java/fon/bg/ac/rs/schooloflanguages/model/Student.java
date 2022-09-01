package fon.bg.ac.rs.schooloflanguages.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Domenska klasa koja predstavlja Studenta koji pohadja Kurs.
 * Student ima Id, Ime, Prezime, Datum rodjenja i Pol.
 * 
 * @author Kristina
 *
 */
@Entity
@Table(name="student")
public class Student implements MyEntity{
	
	/**
	 * Id studenta - jedinstveni identifikator Studenta u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Ime studenta
	 */
	@NotNull(message="First name is required field!")
	@NotEmpty(message="First name is required field!")
	private String firstName;
	
	/**
	 * Prezime studenta
	 */
	@NotNull(message="Last name is required field!")
	@NotEmpty(message="Last name is required field!")
	private String lastName;
	
	/**
	 * Datum rodjenja studenta
	 */
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	private Timestamp datumRodjenja;
	
	/**
	 * Pol studenta
	 */
	private Gender gender;
	
	/**
	 * Lista kurseva koje pohadja Student.
	 * Jedan student moze da pohadja vise kurseva.
	 */
	@ManyToMany
	@JoinTable(name = "course_student", 
			  joinColumns = @JoinColumn(name = "student_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	/**
	 * Lista faktura za Studenta.
	 * Jedan student moze da ima vise faktura.
	 */
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Invoice> invoices;

	/**
	 * Bezparametarski konstruktor
	 */
	public Student() {
	}

	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param id Id studenta
	 * @param firstName Ime studenta
	 * @param lastName Prezime studenta
	 * @param datumRodjenja Datum rodjenja studenta
	 * @param gender Pol studenta
	 */
	public Student(Long id,String firstName,String lastName,Timestamp datumRodjenja,
			Gender gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.datumRodjenja = datumRodjenja;
		this.gender = gender;
	}


	/**
	 * Vraca pol studenta
	 * 
	 * @return Pol studenta kao enum Gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * Postavlja vrednost za Pol
	 * 
	 * @param gender novi Pol za korisnika kao enum Gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Vraca Id studenta
	 * 
	 * @return Id studenta kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja vrednost za Id 
	 * 
	 * @param id novi Id studenta kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Ime studenta
	 * 
	 * @return Ime studenta kao String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Postavlja vrednost za Ime studenta
	 * 
	 * @param firstName novo Ime studenta kao String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Vraca Prezime studenta
	 * 
	 * @return Prezime studenta kao String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Postavlja vrednost za Prezime studenta
	 * 
	 * @param lastName novo Prezime studenta kao String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Vraca Datum rodjenja studenta
	 * 
	 * @return Datum rodjenja studenta kao Timestamp
	 */
	public Timestamp getDatumRodjenja() {
		return datumRodjenja;
	}
	
	/**
	 * Postavlja vrednost za Datum rodjenja studenta
	 * 
	 * @param datumRodjenja novi Datum rodjenja studenta kao Timestamp
	 */
	public void setDatumRodjenja(Timestamp datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	/**
	 * Vraca kurseve koje pohadja student
	 * 
	 * @return Lista Kurseva koje pohadja student
	 */
	public List<Course> getCourses() {
		return courses;
	}
	
	/**
	 * Postavlja kurseve koje pohadja student
	 * 
	 * @param courses nova lista Kurseva koje pohadjaj student
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
