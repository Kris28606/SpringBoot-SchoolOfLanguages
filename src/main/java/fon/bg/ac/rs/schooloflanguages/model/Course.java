package fon.bg.ac.rs.schooloflanguages.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Domenska klasa koja predstavlja Kurs.
 * Kurs ima svoj Id, Naziv, Cenu,Datum pocetka i Datum zavrsetka kursa.
 * 
 * @author Kristina
 */
@Entity
@Table(name="course")
public class Course implements MyEntity{
	
	/**
	 * Id kursa - jedinstveni identifikator Kursa u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	/**
	 * Naziv kursa
	 */
	@NotNull(message="Name is required field!")
	@NotEmpty(message="Name is required field!")
	@Column(length = 30)
	private String name;
	
	/**
	 * Cena kursa
	 */
	@NotNull(message="Price is required field!")
	@NotEmpty(message="Price is required field!")
	private BigDecimal price;
	
	/**
	 * Datum pocetka kursa
	 */
	@NotNull(message="Start date is required field!")
	@NotEmpty(message="Start date is required field!")
	private Timestamp startDate;
	
	/**
	 * Datum zavrsetka kursa
	 */
	@NotNull(message="End date is required field!")
	@NotEmpty(message="End date is required field!")
	private Timestamp endDate;
	
	/**
	 * Lista svih predavaca koji predaju na kursu.
	 * Na jednom kursu moze da predaje vise predavaca.
	 */
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	public List<Teacher> teachers;
	
	/**
	 * Lista svih studenata koji pohadjaju kurs.
	 * Jedan kurs moze da pohadja vise studenata.
	 */
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	public List<Student> students;
	
	/**
	 * Lista svih stavki fakture na kojima se nalazi kurs.
	 * Jedan kurs moze da se odnosi na vise stavki fakture.
	 */
	@OneToMany(mappedBy="course")
	@JsonIgnore
	public List<InvoiceItem> items;
	
	/**
	 * Generise hash kod za atribut Naziv kursa
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	/**
	 * Poredi dva kursa po nazivu kursa
	 * 
	 * @return
	 * <ul>
	 * 	<li>true - Ako je Naziv isti kod oba kursa</li>
	 *  <li>false - U svakom drugom slucaju </li>
	 * </ul>
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(name, other.name);
	}
	/**
	 * Bezparametarski konstruktor
	 */
	public Course() {
		
	}
	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param id Id kursa
	 * @param name Naziv kursa
	 * @param price Cena kursa
	 * @param startDate Datum pocetka
	 * @param endDate Datum zavrsetka
	 */
	public Course(Long id, String name, BigDecimal price, Timestamp startDate, Timestamp endDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	/**
	 * Vraca Id kursa
	 * 
	 * @return Id kursa kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja vrednost za Id kursa
	 * 
	 * @param id novi Id za kurs
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Naziv kursa
	 * 
	 * @return Naziv kursa kao String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Postavlja vrednost za Naziv kursa
	 * 
	 * @param name novi Naziv za kurs kao String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Vraca Cenu kursa
	 * 
	 * @return Cena kursa kao decimalni broj BigDecimal
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Postavlja vrednost za Cenu kursa
	 * 
	 * @param price nova Cena za kurs kao decimalni broj BigDecimal
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * Vraca Datum pocetka kursa
	 * 
	 * @return Datum pocetka kursa kao Timestamp
	 */
	public Timestamp getStartDate() {
		return startDate;
	}
	
	/**
	 * Postavlja vrednost za Datum pocetka kursa
	 * 
	 * @param startDate novi Datum pocetka kursa kao Timestamp
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Vraca Datum zavrsetka kursa
	 * 
	 * @return Datum zavrsetka kursa kao Timestamp
	 */
	public Timestamp getEndDate() {
		return endDate;
	}
	
	/**
	 * Postavlja vrednost za Datum zavrsetka kursa
	 * 
	 * @param endDate novi Datum zavrsetka kursa kao Timestamp
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
}
