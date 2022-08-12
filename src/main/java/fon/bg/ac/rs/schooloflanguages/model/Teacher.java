package fon.bg.ac.rs.schooloflanguages.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;


@Entity
@Table(name="teacher")
public class Teacher implements MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String first_name;
	@NonNull
	private String last_name;
	@NonNull
	private String address;
	@Column(unique=true)
	@NonNull
	private String contact;
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToMany
	@JoinTable(name = "course_teacher", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

	public Teacher() {
		
	}
	
	public Teacher(Long id, String first_name, String last_name, String address, String contact, City city,
			List<Course> courses) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.contact = contact;
		this.city = city;
		this.courses = courses;
	}

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	
}
