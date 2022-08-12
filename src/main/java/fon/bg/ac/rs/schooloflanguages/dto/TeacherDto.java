package fon.bg.ac.rs.schooloflanguages.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import fon.bg.ac.rs.schooloflanguages.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto implements Dto{
	@NotNull
	private Long id;
	@NotEmpty(message="First name is required field!")
	private String first_name;
	@NotEmpty(message="Last name is required field!")
	private String last_name;
	@NotEmpty(message="Address is required field!")
	private String address;
	@NotEmpty(message="Contact is required field!")
	private String contact;
	@NotNull(message="City is required field!")
	private City city;
	@NotEmpty(message="Courses is required field!")
	private List<CourseDto> courses;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<CourseDto> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

}
