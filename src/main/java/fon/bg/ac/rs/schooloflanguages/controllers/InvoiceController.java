package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.InvoiceMapper;
import fon.bg.ac.rs.schooloflanguages.mapper.StudentMapper;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.service.InvoiceService;

/**
 * <h1>Kontroler za entitet Invoice</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("invoice")
@CrossOrigin("http://localhost:4200")
public class InvoiceController {
	/**
	 * Servis za entite Invoice
	 */
	@Autowired
	private InvoiceService invoiceService;
	
	/**
	 * Mapper za entitet Invoice koji pretvara iz InvoiceDto objekta u Invoice i obrnuto
	 */
	private InvoiceMapper invoiceMapper;
	
	/**
	 * Mapper za entitet Student koji pretvara iz StudentDto objekta u Student i obrnuto
	 */
	private StudentMapper studentMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se samo inicijalizuju vrednosti za Invoice i Student mapere
	 */
	public InvoiceController() {
		this.invoiceMapper=new InvoiceMapper();
		this.studentMapper=new StudentMapper();
	}
	
	/**
	 * <h1>End point za vracanje svih faktura koje postoje u bazi </h1>
	 * <p>Get ruta "invoice/all"</p>
	 * @return Listu svih Faktura koje postoje u bazi kao Dto objekte
	 */
	@GetMapping("all")
	public List<InvoiceDto> vratiSve() {
		return invoiceService.getAll();
	}
	
	/**
	 * <h1>End point za storniranje zadate Fakture </h1>
	 * <p>Delete ruta "invoice/id"</p>
	 * @param id - Id fakture koju treba stornirati
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(InvoiceDto)-ukoliko je Fakture uspesno stornirana</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Faktura ne postoji ili je vec stornirana</li>
	 *  <li>ResponseEntity.InternalServerError()-ukoliko upit ne moze da se izvrsi</li>
	 * </ul>
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Object> stornirajFakturu(@PathVariable("id") Long id){
		try {
			return ResponseEntity.ok(invoiceService.storniraj(id));
		} catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * <h1>End point za cuvanje nove Fakture </h1>
	 * <p>Post ruta "invoice/new"</p>
	 * @param invoice - Nova faktura koju treba sacuvati u bazi
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(InvoiceDto)-ukoliko je Fakture uspesno sacuvana</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Faktura vec postoji</li>
	 *  <li>ResponseEntity.InternalServerError()-ukoliko upit ne moze da se izvrsi</li>
	 * </ul>
	 */
	@PostMapping("new")
	public ResponseEntity<Object> sacuvajNovu(@RequestBody InvoiceDto invoice) {
		try {
			return ResponseEntity.ok(invoiceService.sacuvaj(invoiceMapper.toEntity(invoice)));
		} catch(ErrorException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za vracanje svih kurseva za odredjenog korisnika za koje on nema fakturu </h1>
	 * <p>Post ruta "invoice/get-courses"</p>
	 * @param student - Student po kojem se pretrazuju fakture
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(List Course)-ukoliko je upit uspesno izvrsen</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko upit nije izvrsen</li>
	 * </ul>
	 */
	@PostMapping("get-courses")
	public ResponseEntity<Object> vratiKurseveZaFakturu(@RequestBody StudentDto student) {
		try {
			return ResponseEntity.ok(invoiceService.vratiKurseveZaKorisnika(studentMapper.toEntity(student)));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	/**
	 * <h1>End point za pronalazenje jedne fakture po id-ju </h1>
	 * <p>Get ruta "invoice/id"</p>
	 * @param id - Id fakture koju treba pronaci
	 * @return
	 * <ul> 
	 * 	<li>ResponseEntity.ok(InvoiceDto)-ukoliko je pronadjena Faktura</li>
	 *  <li>ResponseEntity.BadRequest()-ukoliko Faktura sa tim id-jem ne postoji</li>
	 * </ul>
	 */
	@GetMapping("id")
	public ResponseEntity<Object> vratiJednu(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok(invoiceService.findOne(id));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

}
