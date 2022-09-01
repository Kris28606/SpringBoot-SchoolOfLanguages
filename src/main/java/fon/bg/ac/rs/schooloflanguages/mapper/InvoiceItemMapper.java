package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceItemDto;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;

/**
 * Maper koji predstavlja implementaciju Generickog mapera za entitet Stavka fakture.
 * 
 * @author Kristina
 *
 */
public class InvoiceItemMapper implements GenericMapper<InvoiceItemDto, InvoiceItem>{

	/**
	 * Maper za entitet Kurs
	 */
	private CourseMapper courseMapper;
	
	/**
	 * Bezparametarski konstruktor u okviru kojeg se inicijalizuje vrednost za kurs maper.
	 */
	public InvoiceItemMapper() {
		courseMapper=new CourseMapper();
	}
	
	/**
	 * Transformise Stavku fakture dto u Stavku fakture entitet.
	 * 
	 * @param dto - Stavka fakture dto
	 * @return Stavka fakture entitet
	 */
	@Override
	public InvoiceItem toEntity(InvoiceItemDto dto) {
		InvoiceItem it=new InvoiceItem();
		it.setCourse(courseMapper.toEntity(dto.getCourse()));
		it.setItemValue(dto.getItemValue());
		it.setSn(dto.getSn());
		return it;
	}

	/**
	 * Transformise Stavku fakture entitet u Stavku fakture dto.
	 * 
	 * @param e - Stavka fakture entitet
	 * @return Stavka fakture dto
	 */
	@Override
	public InvoiceItemDto toDto(InvoiceItem e) {
		InvoiceItemDto it=new InvoiceItemDto();
		it.setCourse(courseMapper.toDto(e.getCourse()));
		it.setItemValue(e.getItemValue());
		it.setSn(e.getSn());
		return it;
	}

}
