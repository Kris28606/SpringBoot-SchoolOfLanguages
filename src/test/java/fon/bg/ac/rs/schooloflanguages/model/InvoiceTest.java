package fon.bg.ac.rs.schooloflanguages.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class InvoiceTest {

	public static Invoice i;
	static List<InvoiceItem> items;
	public static Student s;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		i=new Invoice();
		InvoiceItem ii=new InvoiceItem();
		Course k=new Course();
		k.setId(123L);
		k.setName("Kurs spanskog jezika");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		
		Date dateEnd = dateFormat.parse("28/02/2023");
		long timeEnd = dateEnd.getTime();
		Timestamp datumEnd=new Timestamp(timeEnd);
		k.setStartDate(datumStart);
		k.setEndDate(datumEnd);
		
		List<Course> courses=new ArrayList<>();
		courses.add(k);
		
		k.setPrice(new BigDecimal(15999.99));
		ii.setCourse(k);
		ii.setItemValue(15999.99);
		ii.setSn(1L);
		
		s=new Student();
		s.setCourses(courses);
		Date dateBirth = dateFormat.parse("28/02/1999");
		long timeBirth = dateBirth.getTime();
		Timestamp datumBirth=new Timestamp(timeBirth);
		s.setDatumRodjenja(datumBirth);
		s.setFirstName("Pera");
		s.setLastName("Peric");
		s.setId(1L);
		s.setGender(Gender.Male);
		
		i.setCancelled(false);
		i.setDate(datumStart);
		i.setId(1L);
		i.setPaymentMethod(PaymentMethod.Cash);
		i.setStudent(s);
		i.setTotalPrice(20000);
		items=new ArrayList<>();
		items.add(ii);
		i.setItems(items);
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
	void testInvoiceLongTimestampDoublePaymentMethodBooleanStudentListOfInvoiceItem() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		i=new Invoice(123L,datumStart,18999.99, PaymentMethod.Cash, false,s,items );
		
		assertEquals(i.getId(), 123L);
		assertEquals(i.getDate(), datumStart);
		assertEquals(i.getTotalPrice(), 18999.99);
		assertEquals(i.getPaymentMethod(), PaymentMethod.Cash);
		assertEquals(i.getStudent(), s);
		assertEquals(i.getItems(), items);
		assertEquals(i.isCancelled(), false);
	}

	@DisplayName("Test za setovanje atributa Id-OK")
	@Test
	void testSetId() {
		i.setId(28L);
		assertEquals(i.getId(), 28L);
	}

	@DisplayName("Test za setovanje atributa Id")
	@Test
	void testSetIdSmallerThan1() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> i.setId(0L) );
	}
	
	@DisplayName("Test za setovanje atributa Date")
	@Test
	void testSetDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/10/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		i.setDate(datumStart);
		assertEquals(i.getDate(), datumStart);
	}
	
	@DisplayName("Test za setovanje atributa Date-Date je null")
	@Test
	void testSetDateNull() throws ParseException {
		assertThrows(java.lang.NullPointerException.class,
				() -> i.setDate(null) );
	}
	
	@DisplayName("Test za setovanje atributa Date-Date je proslost")
	@Test
	void testSetDateBeforeNow() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("28/04/2022");
		long time = date.getTime();
		Timestamp datumStart=new Timestamp(time);
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> i.setDate(datumStart) );
	}

	@DisplayName("Test za setovanje atributa TotalPrice-TotalPrice je manja od 5000 dinara")
	@Test
	void testSetTotalPriceSmallerThan5000() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> i.setTotalPrice(4000) );
	}
	
	@DisplayName("Test za setovanje atributa TotalPrice-OK")
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

	@DisplayName("Test za setovanje atributa Student-OK")
	@Test
	void testSetStudent() {
		i.setStudent(s);
		assertEquals(i.getStudent(), s);
	}
	
	@DisplayName("Test za setovanje atributa Student-Student je null")
	@Test
	void testSetStudentNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> i.setStudent(null) );
	}

	@DisplayName("Test za setovanje atributa Items")
	@Test
	void testSetItems() {
		i.setItems(items);
		assertEquals(i.getItems(), items);
	}

}
