package fon.bg.ac.rs.schooloflanguages.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="student")
public class Student implements MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="First name is required field!")
	@NotEmpty(message="First name is required field!")
	private String firstName;
	@NotNull(message="Last name is required field!")
	@NotEmpty(message="Last name is required field!")
	private String lastName;
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy.")
	private Timestamp datumRodjenja;
	
	@ManyToMany
	@JoinTable(name = "course_student", 
			  joinColumns = @JoinColumn(name = "student_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

	
	public Student() {
	}
	
	public Student(Long id,String firstName,String lastName,Timestamp datumRodjenja,List<Course> courses) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.datumRodjenja = datumRodjenja;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Timestamp datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
