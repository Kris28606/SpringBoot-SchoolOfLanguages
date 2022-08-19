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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp datumRodjenja;
	
	private int years;
	private Gender gender;
	
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@NotEmpty
	private List<CourseDto> courses;
	
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public List<CourseDto> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseDto> courses) {
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
