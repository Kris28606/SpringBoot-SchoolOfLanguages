package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.InvoiceItemDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;

class InvoiceItemMapperTest {
	
	public static InvoiceItemMapper mapper;
	public static CourseMapper courseMapper;
	public static InvoiceItem i;
	public static InvoiceItemDto dto;
	public static Course k;
	public static CourseDto dtoK;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mapper=new InvoiceItemMapper();
		courseMapper=new CourseMapper();
		
		k=new Course();
		k.setId(1L);
		k.setName("Engleskog jezika");
		k.setPrice(new BigDecimal(15000));
		Timestamp start=new Timestamp(1661975071898L);
		Timestamp end=new Timestamp(1672975071898L);
		k.setStartDate(start);
		k.setEndDate(end);
		
		dtoK=courseMapper.toDto(k);
		
		i=new InvoiceItem();
		i.setItemValue(15000);
		i.setCourse(k);
		i.setSn(1L);
		
		dto=new InvoiceItemDto();
		dto.setItemValue(15000);
		dto.setCourse(dtoK);
		dto.setSn(1L);
	}

	@DisplayName("Test za pretvaranje iz InvoiceItemDto objekta u InvoiceItem")
	@Test
	void testToEntity() {
		InvoiceItem expected=mapper.toEntity(dto);
		
		assertEquals(expected.getItemValue(), i.getItemValue());
		assertEquals(expected.getSn(), i.getSn());
	}

	@DisplayName("Test za pretvaranje iz InvoiceItemobjekta u InvoiceItemDto")
	@Test
	void testToDto() {
		InvoiceItemDto expected=mapper.toDto(i);
		
		assertEquals(expected.getItemValue(), dto.getItemValue());
		assertEquals(expected.getSn(), dto.getSn());
	}

}
