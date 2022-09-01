package fon.bg.ac.rs.schooloflanguages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;
import fon.bg.ac.rs.schooloflanguages.service.PaymentMethodService;

/**
 * <h1>Kontroler za enum Payment method</h1>
 * <p> Odgovora za definisanje End Point-a i pozivanje odgovarajucih servis metoda</p>
 * @author Kristina
 *
 */
@RestController
@RequestMapping("payment-method")
@CrossOrigin("http://localhost:4200")
public class PaymentMethodController {
	/**
	 * Servis za enum PaymentMethod
	 */
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	/**
	 * <h1>End point za vracanje svih metoda placanje koje postoje</h1>
	 * <p>Get ruta "payment-method/all"</p>
	 * @return Niz svih metoda koje postoje
	 */
	@GetMapping("all")
	public PaymentMethod[] getAll() {
		return paymentMethodService.getAll();
	}
}
