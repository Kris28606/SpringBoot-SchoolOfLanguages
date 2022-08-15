package fon.bg.ac.rs.schooloflanguages.mapper;

import java.util.stream.Collectors;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;

public class InvoiceMapper implements GenericMapper<InvoiceDto, Invoice>{

	private StudentMapper studentMapper;
	private InvoiceItemMapper invoiceItemMapper;
	
	public InvoiceMapper() {
		this.studentMapper=new StudentMapper();
		this.invoiceItemMapper=new InvoiceItemMapper();
	}
	
	@Override
	public Invoice toEntity(InvoiceDto dto) {
		Invoice i=new Invoice();
		i.setId(dto.getId());
		i.setDate(dto.getDate());
		i.setPaymentMethod(dto.getPaymentMethod());
		i.setCancelled(dto.isCancelled());
		i.setStudent(studentMapper.toEntity(dto.getStudent()));
		i.setTotalPrice(dto.getTotalPrice());
		i.setItems(dto.getItems().stream().map((item)-> {
			return invoiceItemMapper.toEntity(item);
		}).collect(Collectors.toList()));
		return i;
	}

	@Override
	public InvoiceDto toDto(Invoice e) {
		InvoiceDto i=new InvoiceDto();
		i.setId(e.getId());
		i.setDate(e.getDate());
		i.setPaymentMethod(e.getPaymentMethod());
		i.setCancelled(e.isCancelled());
		i.setStudent(studentMapper.toDto(e.getStudent()));
		i.setTotalPrice(e.getTotalPrice());
		i.setItems(e.getItems().stream().map((item)-> {
			return invoiceItemMapper.toDto(item);
		}).collect(Collectors.toList()));
		return i;
	}

}
