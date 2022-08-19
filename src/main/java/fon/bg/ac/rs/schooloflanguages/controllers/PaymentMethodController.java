package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;
import fon.bg.ac.rs.schooloflanguages.service.PaymentMethodService;

@RestController
@RequestMapping("payment-method")
@CrossOrigin("http://localhost:4200")
public class PaymentMethodController {
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@GetMapping("all")
	public PaymentMethod[] getAll() {
		return paymentMethodService.getAll();
	}
}
