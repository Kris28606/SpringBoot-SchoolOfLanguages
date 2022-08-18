package fon.bg.ac.rs.schooloflanguages.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.InvoiceMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceItemRepository;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	private InvoiceMapper invoiceMapper;
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;
	
	public InvoiceService() {
		this.invoiceMapper=new InvoiceMapper();
	}

	public List<InvoiceDto> getAll() {
		List<Invoice> invoices=invoiceRepository.findAll();
		return invoices.stream().map((invoice)-> {
			return invoiceMapper.toDto(invoice);
		}).collect(Collectors.toList());
	}

	public InvoiceDto storniraj(Long id) throws ErrorException {
		Optional<Invoice> optional=invoiceRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Invoice doesn't exist!");
		}
		Invoice i=optional.get();
		if(i.isCancelled()) {
			throw new ErrorException("Invoice is allredy reverted!");
		}
		i.setCancelled(true);
		i= invoiceRepository.save(i);
		return invoiceMapper.toDto(i);
	}
	
	@Transactional
	public InvoiceDto sacuvaj(Invoice invoice) throws Exception {
		List<Invoice> lista=invoiceRepository.findByStudent(invoice.getStudent());
		if(!lista.isEmpty()) {
			for(Invoice inv : lista) {
			for (InvoiceItem ii : inv.getItems()) {
				if(invoice.getItems().contains(ii.getCourse())) {
					throw new ErrorException("Ne moze da se napravi ponovo faktura za istog korisnika!");
				}
			}
			}
		}
		List<InvoiceItem> items=invoice.getItems();
		invoice.setItems(null);
		invoice=invoiceRepository.save(invoice);
		for(InvoiceItem ii: items) {
			ii.setInvoice(invoice);
			invoiceItemRepository.save(ii);
		}
		invoice.setItems(items);
		return invoiceMapper.toDto(invoice);
	}
	
	public List<Course> vratiKurseveZaKorisnika(Student s) {
		List<Invoice> optional=invoiceRepository.findByStudent(s);
		if(optional.isEmpty()) {
			return s.getCourses();
		}
		List<Course> kursevi=new ArrayList<>();
		for(Invoice i : optional) {
			for(InvoiceItem ii : i.getItems()) {
				kursevi.add(ii.getCourse());
			}
		}
		List<Course> kurseviFinal=s.getCourses();
		for(int i =0;i<kursevi.size();i++) {
			if(kursevi.contains(kurseviFinal.get(i))) {
				kurseviFinal.remove(i);
			}
		}
		return kurseviFinal;
	}
}
