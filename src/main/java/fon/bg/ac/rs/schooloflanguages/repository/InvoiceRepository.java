package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.Student;

/**
 * <h1>Interfejs koji predstavlja repozitorijum za entitet Faktura.</h1>
 * <p>Nasledjuje i prosiruje JpaRepository.</p>
 * <p>Vrsi komunikaciju za bazom.</p>
 * 
 * @author Kristina
 *
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	/**
	 * <h1>Metoda koja pronalazi Fakturu po Id-u, ukoliko on postoji.</h1>
	 * 
	 * @param id Long vrednost koja predstvlja kriterijum za pretragu.
	 * @return Optional koji je prazan (ukoliko ne postoji Faktura sa tim id-jem) ili sadrzi Fakturu.
	 */
	Optional<Invoice> findById(Long id);
	
	/**
	 * <h1>Metoda koja pronalazi Fakture po Studentu za kojeg su fakture.</h1>
	 * 
	 * @param student Student po kojem se pretrazuju fakture.
	 * @return Optional koji je prazan (ukoliko ne postoje Fakture za tog studenta) ili sadrzi listu faktura.
	 */
	List<Invoice> findByStudent(Student student);

}
