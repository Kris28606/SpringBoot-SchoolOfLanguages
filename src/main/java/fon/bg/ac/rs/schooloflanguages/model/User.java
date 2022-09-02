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
	 * Username korisnika.
	 * Jedinstvena vrednost u bazi.
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
	 * Bezparametarski konstruktor
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
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
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
	 * @throws IllegalArgumentException ukoliko je novi Id manji ili jednak nuli
	 */
	public void setId(Long id) {
		if(id<=0) {
			throw new IllegalArgumentException("Id mora biti veci od nule!");
		}
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
	 * @throws NullPointerException ukoliko je novo Ime korisnika null
	 * @throws IllegalArgumentException ukoliko novo Ime korisnika ima manje od tri slova
	 */
	public void setFirstName(String firstName) {
		if(firstName==null) {
			throw new NullPointerException("Ime ne sme biti null!");
		}
		if(firstName.length()<3) {
			throw new IllegalArgumentException("Ime mora da ima bar tri slova!");
		}
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
	 * @throws NullPointerException ukoliko je novo Prezime korisnika null
	 * @throws IllegalArgumentException ukoliko novo Prezime korisnika ima manje od cetiri slova
	 */
	public void setLastName(String lastName) {
		if(lastName==null) {
			throw new NullPointerException("Prezime ne sme biti null!");
		}
		if(lastName.length()<4) {
			throw new IllegalArgumentException("Prezime mora da ima bar cetiri slova!");
		}
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
	 * @throws NullPointerException ukoliko je novi Username korisnika null
	 * @throws IllegalArgumentException ukoliko novi Username korisnika ima manje od dva slova
	 */
	public void setUsername(String username) {
		if(username==null) {
			throw new NullPointerException("Username ne sme biti null!");
		}
		if(username.length()<2) {
			throw new IllegalArgumentException("Username mora da ima bar dva slova!");
		}
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
	 * @throws NullPointerException ukoliko je nova Sifra korisnika null
	 * @throws IllegalArgumentException ukoliko nova Sifra korisnika ima manje od sest slova
	 */
	public void setPassword(String password) {
		if(password==null) {
			throw new NullPointerException("Sifra ne sme biti null!");
		}
		if(password.length()<6) {
			throw new IllegalArgumentException("Sifra mora imati bar 6 slova!");
		}
		this.password = password;
	}
	
}
