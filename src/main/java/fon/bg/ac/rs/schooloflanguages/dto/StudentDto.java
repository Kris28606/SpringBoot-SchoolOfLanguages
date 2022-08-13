package fon.bg.ac.rs.schooloflanguages.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import fon.bg.ac.rs.schooloflanguages.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto implements Dto{
	
	@NonNull
	private Long id;
	@NotNull(message="First name is required field!")
	@NotEmpty(message="First name is required field!")
	private String firstName;
	@NotNull(message="Last name is required field!")
	@NotEmpty(message="Last name is required field!")
	private String lastName;
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	private Timestamp datumRodjenja;
	@NotEmpty
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
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
	
}
