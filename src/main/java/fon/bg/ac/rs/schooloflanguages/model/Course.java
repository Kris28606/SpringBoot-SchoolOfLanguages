package fon.bg.ac.rs.schooloflanguages.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course implements MyEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	@NotNull(message="Name is required field!")
	@NotEmpty(message="Name is required field!")
	private String name;
	
	@NotNull(message="Price is required field!")
	@NotEmpty(message="Price is required field!")
	private BigDecimal price;
	
	@NotNull(message="Start date is required field!")
	@NotEmpty(message="Start date is required field!")
	private Timestamp startDate;
	
	@NotNull(message="End date is required field!")
	@NotEmpty(message="End date is required field!")
	private Timestamp endDate;
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	public List<Teacher> teachers;
	
	public Course() {
		
	}
	
	public Course(Long id, String name, BigDecimal price, Timestamp startDate, Timestamp endDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
}
