package fon.bg.ac.rs.schooloflanguages.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet Student.
 * Sadrzi Id, Ime, Prezime, Datum rodjenja, Broj godina i Pol.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto implements Dto{
	
	/**
	 * Id studenta - jedinstveni identifikator Studenta u bazi
	 */
	@NonNull
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
	 * Datum rodjenja studenta.
	 * U formatu yyyy-MM-dd
	 */
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp datumRodjenja;
	
	/**
	 * Broj godina studenta
	 */
	private int years;
	
	/**
	 * Pol studenta
	 */
	private Gender gender;
	
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
	 * Lista kurseva koje pohadja student
	 */
	@NotEmpty
	private List<CourseDto> courses;
	
	/**
	 * Vraca broj godina studenta
	 * 
	 * @return Broj godina studenta kao ceo broj tipa Int
	 */
	public int getYears() {
		return years;
	}
	
	/**
	 * Postavlja vrednost za broj godina studenta
	 * 
	 * @param years novi Broj godina studenta kao ceo broj tipa Int
	 */
	public void setYears(int years) {
		this.years = years;
	}
	
	/**
	 * Vraca kurseve koje pohadja student
	 * 
	 * @return Lista Kurseva koje pohadja student
	 */
	public List<CourseDto> getCourses() {
		return courses;
	}
	
	/**
	 * Postavlja kurseve koje pohadja student
	 * 
	 * @param courses nova lista Kurseva koje pohadjaj student
	 */
	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
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
	
}
