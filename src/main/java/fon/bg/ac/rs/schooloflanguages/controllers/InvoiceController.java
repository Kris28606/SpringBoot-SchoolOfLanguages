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

@RestController
@RequestMapping("invoice")
@CrossOrigin("http://localhost:4200")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	private InvoiceMapper invoiceMapper;
	private StudentMapper studentMapper;
	
	public InvoiceController() {
		this.invoiceMapper=new InvoiceMapper();
		this.studentMapper=new StudentMapper();
	}
	
	@GetMapping("all")
	public List<InvoiceDto> vratiSve() {
		return invoiceService.getAll();
	}
	
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
	
	@PostMapping
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
	
	@PostMapping("get-courses")
	public ResponseEntity<Object> vratiKurseveZaFakturu(@RequestBody StudentDto student) {
		try {
			return ResponseEntity.ok(invoiceService.vratiKurseveZaKorisnika(studentMapper.toEntity(student)));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@GetMapping("id")
	public ResponseEntity<Object> vratiJednu(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok(invoiceService.findOne(id));
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

}
