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
/**
 * Domenska klasa koja predstavlja Grad.
 * Grad ima svoj naziv i PTT broj
 * 
 * @author Kristina
 *
 */
@Entity
@Table(name="city")
public class City implements MyEntity{
	/**
	 * Id grada - jedinstveni identifikator Grada u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Naziv grada
	 * Vrednost mora biti jedinstvena u bazi
	 */
	@Column(unique=true)
	private String name;
	
	/**
	 * PTT broj grada
	 * Duzina broja mora biti tacno 5 broja
	 */
	@Column(length = 5)
	private int PTT;
	
	/**
	 * Lista predavaca koji zive u gradu
	 */
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Teacher> teachers;
	
	/**
	 * Neparametrizovani konstruktor
	 */
	public City() {
	}
	
	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param id Id grada
	 * @param name Naziv grada
	 * @param pTT PTT broj grada
	 * @param teachers Lista predavaca koji zive u gradu
	 */
	public City(Long id, String name, int pTT, List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		PTT = pTT;
		this.teachers = teachers;
	}
	
	/**
	 * Vraca sve predavace koji su iz grada
	 * 
	 * @return Lista svih predavaca koji su iz grada
	 */
	public List<Teacher> getTeachers() {
		return teachers;
	}
	
	/**
	 * Postavlja predavace koji su iz grada
	 * 
	 * @param teachers nova Lista svih predavaca koji su iz grada
	 */
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	/**
	 * Vraca Id grada
	 * 
	 * @return Id grada kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja vrednost za Id grada
	 * 
	 * @param id novi Id grada kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Naziv grada
	 * 
	 * @return Naziv kursa kao String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Postavlja vrednost za Naziv grada
	 * 
	 * @param name novi Naziv za grada kao String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Vraca PTT grada
	 * 
	 * @return PTT broj grada kao ceo broj tipa int
	 */
	public int getPTT() {
		return PTT;
	}
	/**
	 * Postavlja vrednost za PTT broj grada
	 * 
	 * @param pTT novi PTT broj grada kao ceo broj tipa int
	 */
	public void setPTT(int pTT) {
		PTT = pTT;
	}
}
