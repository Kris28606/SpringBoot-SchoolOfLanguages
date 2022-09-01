package fon.bg.ac.rs.schooloflanguages.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Domenska klasa koja predstavlja Fakturu za Korisnika.
 * Faktura ima svoj Id, Datum, Ukupnu cenu, Nacin placanja i podatak koji 
 * govori da li je stornirana
 * 
 * @author Kristina
 *
 */
@Entity
@Table(name="invoice")
public class Invoice implements MyEntity {
	/**
	 * Id fakture - jedinstveni identifikator Fakture u bazi
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Datum kreiranja fakture
	 */
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	private Timestamp date;
	
	/**
	 * Ukupna vrednost fakture
	 */
	private double totalPrice;
	
	/**
	 * Nacin placanja
	 */
	private PaymentMethod paymentMethod;
	
	/**
	 * Da li je faktura stornirana
	 */
	private boolean cancelled;
	
	/**
	 * Student na kojeg se odnosi faktura.
	 * Jedna faktura se odnosi na jednog i samo jednog studenta.
	 */
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	/**
	 * Stavke fakture.
	 * Jedna faktura ima vise stavki fakture.
	 */
	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> items;
	
	/**
	 * Bezparametarski konstruktor
	 */
	public Invoice() {
		
	}
	
	/**
	 * Parametarski konstruktor
	 * 
	 * @param id Id fakture
	 * @param date Datum kreiranja fakture
	 * @param totalPrice Ukupna vrednost fakture
	 * @param paymentMethod Nacin placanja
	 * @param cancelled Da li je stornirana
	 * @param student Student na kojeg se odnosi faktura
	 * @param items Stavke fakture
	 */
	public Invoice(Long id,Timestamp date,
			double totalPrice, PaymentMethod paymentMethod, boolean cancelled, Student student,
			List<InvoiceItem> items) {
		super();
		this.id = id;
		this.date = date;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.cancelled = cancelled;
		this.student = student;
		this.items = items;
	}

	/**
	 * Vraca Id fakture
	 * 
	 * @return Id fakture kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Postavlja vrednost za Id fakture
	 * 
	 * @param id novi Id fakture kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Datum kreiranja fakture
	 * 
	 * @return Datum kreiranja fakture
	 */
	public Timestamp getDate() {
		return date;
	}
	
	/**
	 * Postavlja vrednost za Datum kreiranja fakture
	 * 
	 * @param date novi Datum kreiranja fakture kao Timestamp
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	/**
	 * Vraca Ukupnu vrednost fakture
	 * 
	 * @return Ukupnu vrednost fakture kao decimalni broj tipa Double
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Postavlja vrednost za Ukupnu vrednost fakture
	 * 
	 * @param totalPrice nova Ukupna vrednost fakture kao decimalni broj tipa Double
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * Vraca Nacin placanja
	 * 
	 * @return Nacin placanja fakture kao enum Nacin Placanja
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * Postavlja vrednost za Nacin placanja fakture
	 * 
	 * @param paymentMethod novi Nacin placanja fakture kao enum Nacin Placanja
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * Vraca podatak o tome da li je faktura Stornirana
	 * 
	 * @return
	 * <ul>
	 * 	<li>True - ako je faktura stornirana</li>
	 * 	<li>False - ako faktura nije stornirana</li>
	 * </ul>
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
	/**
	 * Postavlja vrednost za atribut Stornirana
	 * 
	 * @param cancelled nova vrednost za atribut Stornirana tipa boolean
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	/**
	 * Vraca Studenta na kojeg se odnosi faktura 
	 * 
	 * @return Student na kojeg se odnosi faktura
	 */
	public Student getStudent() {
		return student;
	}
	
	/**
	 * Postavlja vrednost za Studenta na kojeg se odnosi faktura
	 * 
	 * @param student novi Student na kojeg se odnosi faktura
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	
	/**
	 * Vraca sve stavke fakture
	 * 
	 * @return Listu stavki fakture
	 */
	public List<InvoiceItem> getItems() {
		return items;
	}
	
	/**
	 * Postavlja vrednost za stavke fakture
	 * 
	 * @param items nova Lista stavki fakture
	 */
	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	
	
}
