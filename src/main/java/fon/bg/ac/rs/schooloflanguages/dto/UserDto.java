package fon.bg.ac.rs.schooloflanguages.dto;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet User.
 * Sadrzi Id, Ime, Prezime, Username i Password user-a.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Dto{
	
	/**
	 * Id korisnika - jedinstveni identifikator Korisnika u bazi
	 */
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
