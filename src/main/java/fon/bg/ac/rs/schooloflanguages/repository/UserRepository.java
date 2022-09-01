package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.User;

/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Korisnik.</h1>
 * <p>Nasledjuje i prosiruje JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * <H3>Metoda koja pretrazuje Korisnika po username-u i password-u.</H3>
	 * 
	 * @param username Username korisnika po kojem se pretrazuje
	 * @param password Password korisnika po kojem se pretrazuje
	 * @return Optional koji je prazan (ukoliko ne postoji Korisnik sa tim username-om i password-om) ili sadrzi Korisnika.
	 */
	Optional<User> findByUsernameAndPassword(String username, String password);


}
