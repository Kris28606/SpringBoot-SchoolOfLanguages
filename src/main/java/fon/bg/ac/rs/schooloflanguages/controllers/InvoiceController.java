package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.service.InvoiceService;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;

}
