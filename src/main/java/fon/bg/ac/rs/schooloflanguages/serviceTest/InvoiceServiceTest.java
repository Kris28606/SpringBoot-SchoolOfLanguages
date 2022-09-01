package fon.bg.ac.rs.schooloflanguages.serviceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.bg.ac.rs.schooloflanguages.dto.InvoiceDto;
import fon.bg.ac.rs.schooloflanguages.exception.ErrorException;
import fon.bg.ac.rs.schooloflanguages.mapper.InvoiceMapper;
import fon.bg.ac.rs.schooloflanguages.model.Course;
import fon.bg.ac.rs.schooloflanguages.model.Invoice;
import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceItemRepository;
import fon.bg.ac.rs.schooloflanguages.repository.InvoiceRepository;

/**
 * <h3>Servis za entitet Faktura.</h3>
 * <p>Odgovoran za pozivanje Faktura repozitorijuma i upravljanje podacima iz baze.</p>
 * 
 * @author Kristina
 *
 */
@Service
public class InvoiceServiceTest {
	
	/**
	 * Repozitorijum za entitet Faktura.
	 */
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	/**
	 * Repozitorijum za entitet Stavka fakture
	 */
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;

	/**
	 * <h3>Vraca sve fakture iz baze.</h3>
	 * 
	 * @return Listu svih faktura koje se nalaze u bazi i mapira ih u FakturaDto objekte.
	 */
	public List<Invoice> getAll() {
		List<Invoice> invoices=invoiceRepository.findAll();
		return invoices;
	}

	/**
	 * <h3>Stornira fakturu ciji Id je prosledjen.</h3>
	 * <p>Ukoliko ne postoji Faktura sa tim id-jem baca korisnicki definisan izuzetak klase ErrorException. Ukoliko Faktura postoji, ali je vec stornirana takodje baza izuzetak iste klase.</p>
	 * <p>Ukoliko faktura postoji i nije stornirana, menja vrednost atributa Stornirana na true i cuva fakturu.
	 * Kao rezultat vraca izmenjenu Fakturu mapiranu u FakturaDto. </p>
	 * 
	 * @param id Fakture koja treba da se stornira.
	 * @return Stoniranu fakturu.
	 * @throws ErrorException Ukoliko faktura sa tim id-jem ne postoji ili je faktura vec stornirana.
	 */
	public Invoice storniraj(Long id) throws ErrorException {
		Optional<Invoice> optional=invoiceRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Invoice doesn't exist!");
		}
		Invoice i=optional.get();
		if(i.isCancelled()) {
			throw new ErrorException("Invoice is allredy reverted!");
		}
		i.setCancelled(true);
		i= invoiceRepository.save(i);
		return i;
	}
	
	/**
	 * <h3>Cuva novu Fakturu u bazi podataka.</h3>
	 * <p>Metoda proverava da li Student vec ima neke fakture u bazi.
	 * Ukoliko nema, prvo se cuva samo Faktura i uzima se njen Id, kako bi se postavio kao vrednost za sve stavke fakture.
	 * Nakon toga cuvaju se jedna po jedna stavka fakture i kompletno sacuvan objekat se mapira u FakturaDto i prosledjuje kao rezultat.</p>
	 * <p>Ukoliko Student u bazi vec ima neke fakture, proverava se da li su Kursevi ili Kurs na novoj Fakturi koju treba sacuvati isti. 
	 * Ukoliko jesu, baca se korisnicki definisan izuzetak klase ErrorException, a ukoliko nisu proces se odvija normalno kao sto je vec objasnjeno.</p>
	 * 
	 * @param invoice Nova Faktura koju treba sacuvati u bazi podataka.
	 * @return Sacuvanu Fakturu.
	 * @throws ErrorException Ukoliko postoji vec takva Faktura za istog Studenta i isti/e kurs/eve. 
	 */
	@Transactional
	public Invoice sacuvaj(Invoice invoice) throws ErrorException {
		List<Invoice> lista=invoiceRepository.findByStudent(invoice.getStudent());
		if(!lista.isEmpty()) {
			for(Invoice inv : lista) {
			for (InvoiceItem ii : inv.getItems()) {
				if(invoice.getItems().contains(ii.getCourse())) {
					throw new ErrorException("Ne moze da se napravi ponovo faktura za istog korisnika!");
				}
			}
			}
		}
		List<InvoiceItem> items=invoice.getItems();
		invoice.setItems(null);
		invoice=invoiceRepository.save(invoice);
		for(InvoiceItem ii: items) {
			ii.setInvoice(invoice);
			invoiceItemRepository.save(ii);
		}
		invoice.setItems(items);
		return invoice;
	}
	
	/**
	 * <h3>Vraca Kurseve koje pohadja zadati Student, ali samo one za koje Student nema kreirane fakture. </h3>
	 * <p>Ukoliko Student nema nijednu fakturu u bazi, metoda vraca sve Kurseve koje Student slusa.</p>
	 * <p>Ukoliko Student ima fakturu/e, metoda vraca samo one Kurseve koji se ne nalaze vec na nekoj stavki fature.</p>
	 * 
	 * @param s Student po kojem se pretrazuju fakture.
	 * @return Listu Kurseva za koje Student nema fakturu.
	 */
	public List<Course> vratiKurseveZaKorisnika(Student s) {
		List<Invoice> optional=invoiceRepository.findByStudent(s);
		if(optional.isEmpty()) {
			return s.getCourses();
		}
		List<Course> kursevi=new ArrayList<>();
		for(Invoice i : optional) {
			for(InvoiceItem ii : i.getItems()) {
				kursevi.add(ii.getCourse());
			}
		}
		List<Course> kurseviFinal=s.getCourses();
		for(int i =0;i<kursevi.size();i++) {
			if(kursevi.contains(kurseviFinal.get(i))) {
				kurseviFinal.remove(i);
			}
		}
		return kurseviFinal;
	}

	/**
	 * <h3>Pronalazi jednu Fakturu u bazi po Id-ju fakture.</h3>
	 * <p> Ukoliko faktura sa takvim id-jem ne postoji, baca korisnicki definisan izuzetak klase ErrorException.</p>
	 * <p> Ukoliko faktura postoji, mapira je u FakturaDto i prosledjuje kao rezultat metode. </p>
	 * 
	 * @param id Id fakture po kojoj se pretrazuje.
	 * @return Pronadjenu Fakturu mapiranu u FakturaDto.
	 * @throws ErrorException Ukoliko faktura sa prosledjenim Id-jem ne postoji.
	 */
	public Invoice findOne(long id) throws ErrorException {
		Optional<Invoice> optional=invoiceRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ErrorException("Can't find invoice!");
		}
		Invoice in=optional.get();
		return in;
	}
}
