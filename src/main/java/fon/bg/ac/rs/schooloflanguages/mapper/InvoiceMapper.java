package fon.bg.ac.rs.schooloflanguages.mapper;

import java.util.List;
import java.util.stream.Collectors;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.dto.InvoiceItemDto;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;

/**
 * Maper koji predstavlja implementaciju Generickog mapera za entitet Faktura.
 * 
 * @author Kristina
 *
 */
public class InvoiceMapper implements GenericMapper<InvoiceDto, Invoice>{

	/**
	 * Maper za entitet Student
	 */
	private StudentMapper studentMapper;
	
	/**
	 * Maper za entitet stavka fakture
	 */
	private InvoiceItemMapper invoiceItemMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se inicijalizuju vrednosti za Student i Stavka fakture maper.
	 */
	public InvoiceMapper() {
		this.studentMapper=new StudentMapper();
		this.invoiceItemMapper=new InvoiceItemMapper();
	}
	
	/**
	 * Transformise Faktura dto u Stavku Faktura entitet.
	 * Koristi Student maper da mapira Studenta na kojeg se odnosi Faktura.
	 * Koristi Stavka fakture maper da mapira sve Stavke fakture koje se odnose na tu Fakturu.
	 * 
	 * @param dto - Faktura dto
	 * @return Faktura entitet
	 */
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
	
	/**
	 * Transformise Faktura entitet u Faktura dto.
	 * Koristi Student maper da mapira Studenta na kojeg se odnosi Faktura.
	 * Koristi Stavka fakture maper da mapira sve Stavke fakture koje se odnose na tu Fakturu.
	 * 
	 * @param e - Faktura entitet
	 * @return Faktura dto
	 */
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
