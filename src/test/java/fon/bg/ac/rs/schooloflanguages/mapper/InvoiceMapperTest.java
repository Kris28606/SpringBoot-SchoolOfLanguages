package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.CourseDto;
import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.dto.InvoiceItemDto;
import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Gender;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;
import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;
import fon.bg.ac.rs.schooloflanguages.model.Student;

class InvoiceMapperTest {

	public static Invoice i;
	public static InvoiceDto dtoI;
	public static List<InvoiceItem> items;
	public static InvoiceMapper mapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mapper=new InvoiceMapper();
		InvoiceItem ii=new InvoiceItem();
		
		Course k=new Course();
		k.setId(123L);
		k.setName("Kurs spanskog jezika");
		k.setPrice(new BigDecimal(15000));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/10/2022");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		
		k.setStartDate(datumStart);
		k.setEndDate(datumEnd);
		
		CourseDto dtoK=new CourseDto();
		dtoK.setId(123L);
		dtoK.setName("Kurs spanskog jezika");
		dtoK.setPrice(new BigDecimal(15000));
		dtoK.setStartDate(datumStart);
		dtoK.setEndDate(datumEnd);
		
		InvoiceItemDto dto=new InvoiceItemDto();
		dto.setItemValue(15000);
		dto.setCourse(dtoK);
		dto.setSn(1L);
		List<InvoiceItemDto> itemsDto=new ArrayList<>();
		itemsDto.add(dto);
		
		ii.setCourse(k);
		ii.setItemValue(15000);
		ii.setSn(1L);
		items=new ArrayList<>();
		items.add(ii);

		Date dateBirth = dateFormat.parse("28/06/1999");
		long timeBirth = dateBirth.getTime();
		Timestamp datumBirth=new Timestamp(timeBirth);

		Student s=new Student();
		s.setFirstName("Kristina");
		s.setLastName("Stanisavljevic");
		s.setId(28L);
		s.setGender(Gender.Female);
		s.setDatumRodjenja(datumBirth);
		List<Course> kursevi=new ArrayList<>();
		kursevi.add(k);
		s.setCourses(kursevi);
		
		StudentDto dtoS=new StudentDto();
		
		dtoS.setFirstName("Kristina");
		dtoS.setLastName("Stanisavljevic");
		dtoS.setId(28L);
		dtoS.setGender(Gender.Female);
		dtoS.setDatumRodjenja(datumBirth);
		List<CourseDto> courses=new ArrayList<>();
		courses.add(dtoK);
		dtoS.setCourses(courses);
		
		i=new Invoice(123L,datumStart,18999.99, PaymentMethod.Cash, false,s,items );
		dtoI=new InvoiceDto();
		dtoI.setCancelled(false);
		dtoI.setDate(datumStart);
		dtoI.setId(123L);
		dtoI.setTotalPrice(18999.99);
		dtoI.setItems(itemsDto);
		dtoI.setPaymentMethod(PaymentMethod.Cash);
		dtoI.setStudent(dtoS);
	}

	@DisplayName("Test za pretvaranje iz InvoiceDto objekta u Invoice")
	@Test
	void testToEntity() {
		Invoice expected=mapper.toEntity(dtoI);
		
		assertEquals(i.getId(), expected.getId());
		assertEquals(i.getDate(), expected.getDate());
		assertEquals(i.getPaymentMethod(), expected.getPaymentMethod());
		assertEquals(i.isCancelled(), expected.isCancelled());
	}

	@DisplayName("Test za pretvaranje iz Invoice objekta u InvoiceDto")
	@Test
	void testToDto() {
		InvoiceDto dto=mapper.toDto(i);
		
		assertEquals(dtoI.getId(), dto.getId());
		assertEquals(dtoI.getDate(), dto.getDate());
		assertEquals(dtoI.getPaymentMethod(), dto.getPaymentMethod());
		assertEquals(dtoI.isCancelled(), dto.isCancelled());
	}

}
