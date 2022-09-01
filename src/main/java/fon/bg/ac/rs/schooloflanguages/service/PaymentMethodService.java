package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;

/**
 * <h1>Servis za enum Nacin placanja.</h1>
 * 
 * @author Kristina
 *
 */
@Service
public class PaymentMethodService {

	/**
	 * <h1>Vraca niz svih vrednosti za enum Nacin placanja.</h1>
	 * 
	 * @return Niz vrednosti tipa enum Nacin placanja.
	 */
	public PaymentMethod[] getAll() {
		return PaymentMethod.values();
	}
}
