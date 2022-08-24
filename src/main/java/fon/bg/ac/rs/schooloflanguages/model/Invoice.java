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
	private PaymentMethod paymentMethod;
	private boolean cancelled;
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> items;
	
	public Invoice() {
		
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	
	
}
