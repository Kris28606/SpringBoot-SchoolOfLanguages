package fon.bg.ac.rs.schooloflanguages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * Domenska klasa koja predstavlja korisnika aplikacije.
 * Korisnik ima Id, Ime, Prezime, Username i Sifru
 * 
 * @author Kristina
 *
 */
@Entity
@Table(name="user")
public class User implements MyEntity{
	/**
	 * Id korisnika - jedinstveni identifikator Korisnika u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Ime korisnika
	 */
	@NonNull
	private String firstName;
	
	/**
	 * Prezime korisnika
	 */
	@NonNull
	private String lastName;
	
	/**
	 * Username korisnika
	 * Jedinstvena vrednost u bazi
	 */
	@Column(unique = true)
	@NonNull
	private String username;
	
	/**
	 * Sifra korisnika
	 */
	@NonNull
	private String password;
	
	
	/**
	 * Neparametrizovani konstruktor
	 */
	public User() {
		
	}
	
	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param id Id korisnika
	 * @param firstName Ime korisnika
	 * @param lastName Prezime korisnika
	 * @param username Username korisnika
	 * @param password Sifra korisnika
	 */
	public User(Long id, String firstName, String lastName, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Vraca Id korisnika
	 * 
	 * @return Id korisnika kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja Id za korisnika
	 * 
	 * @param id novi Id korisnika kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Ime korisnika
	 * 
	 * @return Ime korisnika kao String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Postavlja vrednost za Ime korisnika
	 * 
	 * @param firstName novo Ime korisnika kao String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Vraca Prezime korisnika
	 * 
	 * @return Prezime korisnika kao String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Postavlja vrednost za Prezime korisnika
	 * 
	 * @param lastName novo Prezime korisnika kao String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Vraca Username korisnika
	 * 
	 * @return Username korisnika kao String
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Postavlja vrednost za Username korisnika
	 * 
	 * @param username novi Username korisnika kao String
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Vraca Sifru korisnika
	 * 
	 * @return Sifru korisnika kao String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Postavlja vrednost za Sifru korisnika
	 * 
	 * @param password nova Sifra korisnika kao String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
