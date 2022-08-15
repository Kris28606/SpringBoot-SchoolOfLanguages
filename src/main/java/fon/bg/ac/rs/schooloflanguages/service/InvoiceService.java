package fon.bg.ac.rs.schooloflanguages.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.InvoiceMapper;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	private InvoiceMapper invoiceMapper;
	
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
}
