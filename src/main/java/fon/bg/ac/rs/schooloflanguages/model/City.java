package fon.bg.ac.rs.schooloflanguages.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String name;
	@Column(length = 5)
	private int PTT;
	
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Teacher> teachers;
	  
	public City() {
	}
	
	public City(Long id, String name, int pTT, List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		PTT = pTT;
		this.teachers = teachers;
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPTT() {
		return PTT;
	}
	public void setPTT(int pTT) {
		PTT = pTT;
	}
}
