package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;

@Service
public class PaymentMethodService {

	public PaymentMethod[] getAll() {
		return PaymentMethod.values();
	}
}
