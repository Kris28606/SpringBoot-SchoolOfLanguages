package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvoiceItemTest {

	InvoiceItem ii;

	@BeforeEach
	void setUp() throws Exception {
		ii=new InvoiceItem();
	}

	@AfterEach
	void tearDown() throws Exception {
		ii=null;
	}

	@DisplayName("Test za equals metodu")
	@Test
	void testEqualsObject() {
		Course k=new Course();
		k.setName("Kurs italijanskog jezika");
		ii.setCourse(k);
		ii.setCourse(k);
		InvoiceItem ii2=new InvoiceItem();
		ii2.setCourse(k);
		assertEquals(true, ii.equals(ii2));
	}

	@DisplayName("Test za parametrizovani konstruktor")
	@Test
	void testInvoiceItemInvoiceLongDoubleCourse() {
		Invoice i=new Invoice();
		i.setId(1L);
		Course k=new Course();
		k.setName("Kurs italijanskog jezika");
		ii=new InvoiceItem(i,2L,13560.78,k);
		
		assertEquals(ii.getInvoice(), i);
		assertEquals(ii.getCourse(), k);
		assertEquals(ii.getItemValue(), 13560.78);
		assertEquals(ii.getSn(), 2L);
	}

	@DisplayName("Test za setovanje atributa Invoice")
	@Test
	void testSetInvoice() {
		Invoice i=new Invoice();
		i.setId(1L);
		ii.setInvoice(i);
		assertEquals(ii.getInvoice(), i);
	}

	@DisplayName("Test za setovanje atributa Sn")
	@Test
	void testSetSn() {
		ii.setSn(3L);
		assertEquals(ii.getSn(), 3L);
	}

	@DisplayName("Test za setovanje atributa ItemValue")
	@Test
	void testSetItemValue() {
		ii.setItemValue(14000.00);
		assertEquals(ii.getItemValue(), 14000.00);
	}

	@DisplayName("Test za setovanje atributa Course")
	@Test
	void testSetCourse() {
		Course k=new Course();
		k.setName("Kurs italijanskog jezika");
		ii.setCourse(k);
		assertEquals(ii.getCourse(), k);
	}

}
