package fon.bg.ac.rs.schooloflanguages.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;
import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.repository.CourseRepository;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceItemRepository;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class InvoiceServiceTestTest {

	@MockBean
	public InvoiceRepository invoiceRepository;
	
	@MockBean
	public InvoiceItemRepository invoiceItemRepository;
	
	@Autowired
	@InjectMocks
	public InvoiceServiceTest invoiceService;
	
	public static Invoice i1;
	public static List<Invoice> invoices;
	public static List<Course> courses;
	public static Student s;
	public static Course k;
	
	@AfterEach
	void tearDown() throws Exception {
		setUpBeforeClass();
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		i1=new Invoice();
		i1.setId(1L);
		i1.setCancelled(false);
		i1.setPaymentMethod(PaymentMethod.Cash);
		i1.setTotalPrice(18500.00);
		
		k=new Course();
		k.setId(1L);
		k.setName("Spanski jezik");
		List<Course> courses=new ArrayList<>();
		courses.add(k);
		
		s=new Student();
		s.setId(3L);
		s.setFirstName("Kristina");
		s.setLastName("Stanisavljevic");
		s.setCourses(courses);
		
		InvoiceItem ii=new InvoiceItem();
		ii.setInvoice(i1);
		ii.setItemValue(18500.00);
		ii.setSn(1L);
		ii.setCourse(k);
		List<InvoiceItem> items=new ArrayList<>();
		items.add(ii);
		
		i1.setStudent(s);
		i1.setItems(items);
		
		invoices=new ArrayList<>();
		invoices.add(i1);
	}

	
	@DisplayName("Test za vracanje svih faktura")
	@Test
	void testGetAll() {
		when(invoiceService.getAll()).thenReturn(invoices);
		assertNotNull(invoiceService.getAll());
		assertEquals(invoiceService.getAll().size(), 1);
		assertEquals(invoiceService.getAll().get(0), i1);
	}

	@DisplayName("Test za storniranje fakture-OK")
	@Test
	void testStorniraj() throws ErrorException {
		given(invoiceRepository.findById(i1.getId())).willReturn(Optional.of(i1));
		
		i1.setCancelled(true);
		given(invoiceRepository.save(i1)).willReturn(i1);
		
		i1.setCancelled(false);
		Invoice expected=invoiceService.storniraj(i1.getId());
		
		i1.setCancelled(true);
		assertThat(expected).isNotNull();

	    assertEquals(expected, i1);
	    
	    verify(invoiceRepository).findById(any(Long.class));
	    verify(invoiceRepository).save(any(Invoice.class));
	
	}

	@DisplayName("Test za storniranje fakture-Faktura nije pronadjena")
	@Test
	void testStornirajNotFound() {
		given(invoiceRepository.findById(i1.getId())).willReturn(Optional.empty());
		
		assertThrows(ErrorException.class,() -> {
            invoiceService.storniraj(i1.getId());
        });

	    verify(invoiceRepository,never()).save(any(Invoice.class));
	}
	
	@DisplayName("Test za storniranje fakture-Faktura je vec stornirana")
	@Test
	void testStornirajAlreadyCancelled() {
		i1.setCancelled(true);
		given(invoiceRepository.findById(i1.getId())).willReturn(Optional.of(i1));
	
		assertThrows(ErrorException.class,() -> {
            invoiceService.storniraj(i1.getId());
        });
		
		verify(invoiceRepository,never()).save(any(Invoice.class));
	}
	
	@Test
	void testSacuvaj() throws ErrorException {
		List<Invoice> prazna=new ArrayList<>();
		given(invoiceRepository.findByStudent(i1.getStudent())).willReturn(prazna);
		
		given(invoiceRepository.save(i1)).willReturn(i1);
		
		for(InvoiceItem ii: i1.getItems()) {
			given(invoiceItemRepository.save(ii)).willReturn(ii);
		}
		
		Invoice expected=invoiceService.sacuvaj(i1);
		
		assertThat(expected).isNotNull();

	    assertEquals(expected, i1);
	    
	    verify(invoiceRepository).findByStudent(any(Student.class));
	    verify(invoiceRepository).save(any(Invoice.class));
	    verify(invoiceItemRepository).save(any(InvoiceItem.class));
	}

	@Test
	void testVratiKurseveZaKorisnikaKojiNemaFakturu() {
		List<Invoice> prazna=new ArrayList<>();
		given(invoiceRepository.findByStudent(s)).willReturn(prazna);
		
		List<Course> expected=invoiceService.vratiKurseveZaKorisnika(s);
		
		assertNotNull(expected);
		assertThat(expected.size()>0);
		assertEquals(expected, s.getCourses());
		
		verify(invoiceRepository).findByStudent(any(Student.class));
	}
	
	@Test
	void testVratiKurseveZaKorisnikaKojiImaFakturu() {
		List<Course> noviKursevi=new ArrayList<>();
		noviKursevi.add(k);
		Course k2=new Course();
		k2.setId(22L);
		k2.setName("Norveski jezik");
		noviKursevi.add(k2);
		s.setCourses(noviKursevi);
		
		given(invoiceRepository.findByStudent(s)).willReturn(invoices);
		
		List<Course> expected=invoiceService.vratiKurseveZaKorisnika(s);
		
		s.setCourses(courses);
		
		assertNotNull(expected);
		assertThat(expected.size()>0);
		//assertEquals(expected, s.getCourses());
		
		verify(invoiceRepository).findByStudent(any(Student.class));
	}

	@DisplayName("Test za pronalazenje fakture po id-ju-OK")
	@Test
	void testFindOne() throws ErrorException {
		given(invoiceRepository.findById(i1.getId())).willReturn(Optional.of(i1));
		
		Invoice expected=invoiceService.findOne(i1.getId());
		
		assertThat(expected).isNotNull();

	    assertEquals(expected, i1);
	    
	    verify(invoiceRepository).findById(any(Long.class));
	}
	
	@DisplayName("Test za pronalazenje fakture po id-ju-Faktura nije pronadjena")
	@Test
	void testFindOneNotFound() {
		given(invoiceRepository.findById(i1.getId())).willReturn(Optional.empty());
	
		assertThrows(ErrorException.class,() -> {
            invoiceService.findOne(i1.getId());
        });
	}
}
