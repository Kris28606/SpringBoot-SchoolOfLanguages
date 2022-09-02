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
	 * Lista predavaca koji zive u gradu.
	 * Jedan grad moze da ima nula ili vise predavaca koji su iz tog grada.
	 */
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Teacher> teachers;
	
	/**
	 * Bezparametarski konstruktor
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
	 * @throws NullPointerException ukoliko je Lista predavaca null
	 * @throws IllegalArgumentException ukoliko su unete neodgovarajuce vrednosti za atribute
	 */
	public City(Long id, String name, int pTT, List<Teacher> teachers) {
		super();
		setId(id);
		setName(name);
		setPTT(pTT);
		setTeachers(teachers);
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
	 * @throws NullPointerException ukoliko je nova Lista predavaca null
	 * @throws IllegalArgumentException ukoliko je nova Lista predavaca prazna
	 */
	public void setTeachers(List<Teacher> teachers) {
		if(teachers==null) {
			throw new NullPointerException("Lista predavaca ne sme biti null!");
		}
		if(teachers.isEmpty()) {
			throw new IllegalArgumentException("Lista predavaca ne sme biti prazna!");
		}
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
	 * @throws IllegalArgumentException ukoliko je novi Id grada manji od jedan
	 */
	public void setId(Long id) {
		if(id<=0) {
			throw new IllegalArgumentException("Id mora biti veci od nule!");
		}
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
	 * @throws NullPointerException ukoliko je novi Naziv grada null
	 * @throws IllegalArgumentException ukoliko novi Naziv grada ima manje od tri slova
	 */
	public void setName(String name) {
		if(name==null) {
			throw new NullPointerException("Naziv grada ne sme biti null!");
		}
		if(name.length()<3) {
			throw new IllegalArgumentException("Naziv grada mora da ima bar tri slova!");
		}
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
	 * @throws IllegalArgumentException ukoliko je novi PTT broj manji od 11000
	 * @throws IllegalArgumentException ukoliko je novi PTT broj veci od 38900
	 */
	public void setPTT(int pTT) {
		if(pTT<11000) {
			throw new IllegalArgumentException("PTT broj ne sme biti manji od 11000!");
		}
		if(pTT>38999) {
			throw new IllegalArgumentException("PTT broj ne sme biti veci od 38900!");
		}
		PTT = pTT;
	}
}
