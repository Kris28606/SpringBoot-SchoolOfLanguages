package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceItemDto;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;

public class InvoiceItemMapper implements GenericMapper<InvoiceItemDto, InvoiceItem>{

	private CourseMapper courseMapper;
	
	public InvoiceItemMapper() {
		courseMapper=new CourseMapper();
	}
	
	@Override
	public InvoiceItem toEntity(InvoiceItemDto dto) {
		InvoiceItem it=new InvoiceItem();
		it.setCourse(courseMapper.toEntity(dto.getCourse()));
		it.setItemValue(dto.getItemValue());
		it.setSn(dto.getSn());
		return it;
	}

	@Override
	public InvoiceItemDto toDto(InvoiceItem e) {
		InvoiceItemDto it=new InvoiceItemDto();
		it.setCourse(courseMapper.toDto(e.getCourse()));
		it.setItemValue(e.getItemValue());
		it.setSn(e.getSn());
		return it;
	}

}
