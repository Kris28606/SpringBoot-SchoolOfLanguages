package fon.bg.ac.rs.schooloflanguages.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.service.InvoiceService;

@RestController
@RequestMapping("invoice")
@CrossOrigin
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	
	@GetMapping("all")
	public List<InvoiceDto> vratiSve() {
		return invoiceService.getAll();
	}

}
