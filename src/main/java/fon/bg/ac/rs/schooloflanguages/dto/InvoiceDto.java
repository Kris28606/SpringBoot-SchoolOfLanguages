package fon.bg.ac.rs.schooloflanguages.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import fon.bg.ac.rs.schooloflanguages.model.InvoiceItem;
import fon.bg.ac.rs.schooloflanguages.model.PaymentMethod;
import fon.bg.ac.rs.schooloflanguages.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet Faktura.
 * Sadrzi Id, Naziv, Datum kreiranja, Ukupnu vrednost, Nacin placanja, 
 * Studenta na kojeg se odnosi i listu stavki fakture.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto implements Dto{
	
	/**
	 * Id fakture - jedinstveni identifikator Fakture u bazi
	 */
	@NonNull
	private Long id;
	
	/**
	 * Datum kreiranja fakture.
	 * U formatu yyyy-MM-dd
	 */
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	 * Student na kojeg se odnosi faktura
	 */
	private StudentDto student;
	
	/**
	 * Stavke fakture
	 */
	@NotEmpty(message="Items can't be empty!")
	private List<InvoiceItemDto> items;
	
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
	public StudentDto getStudent() {
		return student;
	}
	
	/**
	 * Postavlja vrednost za Studenta na kojeg se odnosi faktura
	 * 
	 * @param student novi Student na kojeg se odnosi faktura
	 */
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	
	/**
	 * Vraca sve stavke fakture
	 * 
	 * @return Listu stavki fakture
	 */
	public List<InvoiceItemDto> getItems() {
		return items;
	}
	
	/**
	 * Postavlja vrednost za stavke fakture
	 * 
	 * @param items nova Lista stavki fakture
	 */
	public void setItems(List<InvoiceItemDto> items) {
		this.items = items;
	}
}
