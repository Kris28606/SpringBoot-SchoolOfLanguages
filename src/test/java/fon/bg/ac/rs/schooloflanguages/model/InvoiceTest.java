package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvoiceTest {

	Invoice i;
	static List<InvoiceItem> items;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		InvoiceItem ii=new InvoiceItem();
		Course k=new Course();
		k.setId(123L);
		k.setName("Kurs spanskog jezika");
		ii.setCourse(k);
		ii.setItemValue(15999.99);
		ii.setSn(1L);
		items=new ArrayList<>();
		items.add(ii);
	}

	@BeforeEach
	void setUp() throws Exception {
		i=new Invoice();
	}

	@AfterEach
	void tearDown() throws Exception {
		i=null;
	}

	@DisplayName("Test za parametarski konstruktor")
	@Test
	void testInvoiceLongTimestampDoublePaymentMethodBooleanStudentListOfInvoiceItem() {
		Timestamp datum=new Timestamp(1661559534045L);
		Student s=new Student();
		s.setFirstName("Kristina");
		s.setLastName("Stanisavljevic");
		s.setId(28L);
		i=new Invoice(123L,datum,18999.99, PaymentMethod.Cash, false,s,items );
		
		assertEquals(i.getId(), 123L);
		assertEquals(i.getDate(), datum);
		assertEquals(i.getTotalPrice(), 18999.99);
		assertEquals(i.getPaymentMethod(), PaymentMethod.Cash);
		assertEquals(i.getStudent(), s);
		assertEquals(i.getItems(), items);
		assertEquals(i.isCancelled(), false);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetId() {
		i.setId(28L);
		assertEquals(i.getId(), 28L);
	}

	@DisplayName("Test za setovanje atributa Date")
	@Test
	void testSetDate() {
		Timestamp datum=new Timestamp(1661559534045L);
		i.setDate(datum);
		assertEquals(i.getDate(), datum);
	}

	@DisplayName("Test za setovanje atributa TotalPrice")
	@Test
	void testSetTotalPrice() {
		i.setTotalPrice(17678.99);
		assertEquals(i.getTotalPrice(), 17678.99);
	}

	@DisplayName("Test za setovanje atributa PaymentMethod")
	@Test
	void testSetPaymentMethod() {
		i.setPaymentMethod(PaymentMethod.CreditCard);
		assertEquals(i.getPaymentMethod(), PaymentMethod.CreditCard);
	}

	@DisplayName("Test za setovanje atributa Cancelled")
	@Test
	void testSetCancelled() {
		i.setCancelled(true);
		assertEquals(i.isCancelled(), true);
	}

	@DisplayName("Test za setovanje atributa Student")
	@Test
	void testSetStudent() {
		Student s=new Student();
		s.setFirstName("Kristina");
		s.setLastName("Stanisavljevic");
		s.setId(28L);
		i.setStudent(s);
		assertEquals(i.getStudent(), s);
	}

	@DisplayName("Test za setovanje atributa Items")
	@Test
	void testSetItems() {
		i.setItems(items);
		assertEquals(i.getItems(), items);
	}

}
