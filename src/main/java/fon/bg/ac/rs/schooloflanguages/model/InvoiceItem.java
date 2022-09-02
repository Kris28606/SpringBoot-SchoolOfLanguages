package fon.bg.ac.rs.schooloflanguages.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fon.bg.ac.rs.schooloflanguages.model.idclasses.InvoiceItemId;

/**
 * Domenska klasa koja predstavlja Stavku fakture.
 * Stavka fakture sadrzi Id fakture, Redni broj stavke, Vrednost stavke i Kurs na koji se odnosi.
 * @author Kristina
 *
 */
@Entity
@Table(name="invoice_item")
@IdClass(InvoiceItemId.class)
public class InvoiceItem implements MyEntity{
	/**
	 * Faktura na koju se Stavka fakture odnosi.
	 * Jedna stavka se odnosi na jednu i samo jednu fakturu.
	 */
	@Id
	@OneToOne
	@JoinColumn(name="invoice_id", nullable=false)
	@JsonIgnore
	private Invoice invoice;
	/**
	 * Redni broj stavke, zajedno sa FakturaId cini slozeni primarni kljuc u bazi
	 */
	@Id
	private Long sn;
	/**
	 * Vrednost stavke
	 */
	private double itemValue;
	
	/**
	 * Kurs na koji se odnosi Stavka fakture.
	 * Jedna stavka se odnosi na jedan i samo jedan kurs.
	 */
	@OneToOne
	@JoinColumn(name="course_id", nullable=false)
	private Course course;
	
	/**
	 * Generise hash kod za Kurs
	 */
	@Override
	public int hashCode() {
		return Objects.hash(course);
	}
	
	/**
	 * Uporedjuje dve stavke fakture
	 * 
	 * @return 
	 * <ul>
	 * 	<li>true - ako obe stavke imaju isti Kurs</li>
	 *  <li>false - u svim ostalim slucajevima</li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceItem other = (InvoiceItem) obj;
		return Objects.equals(course, other.course);
	}

	/**
	 * Bezparametarski konstruktor
	 */
	public InvoiceItem() {
		
	}
	
	/**
	 * Parametrizovani konstruktor
	 * 
	 * @param invoice Faktura na koju se odnosi stavka
	 * @param sn Redni broj stavke
	 * @param itemValue Vrednost stavke
	 * @param course Kurs na koji se odnosi stavka
	 * @throws NullPointerException ukoliko su Faktura i/ili Kurs null
	 * @throws IllegalArgumentException ukoliko su unete neodgovarajuce vrednosti za atribute Redni broj stavke i Item Value
	 */
	public InvoiceItem(Invoice invoice, Long sn, double itemValue, Course course) {
		super();
		setInvoice(invoice);
		setSn(sn);
		setItemValue(itemValue);
		setCourse(course);
	}
	
	/**
	 * Vraca Fakturu na koju se odnosi Stavka
	 * 
	 * @return Fakturu na koju se odnosi Stavka
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	
	/**
	 * Postavlja vrednost za Fakturu na koju se odnosi Stavka
	 * 
	 * @param invoice nova Faktura na koju se odnosi Stavka
	 * @throws IllegalArgumentException ukoliko je nova Faktura null
	 */
	public void setInvoice(Invoice invoice) {
		if(invoice==null) {
			throw new NullPointerException("Faktura ne sme biti null!");
		}
		this.invoice = invoice;
	}
	
	/**
	 * Vraca Redni broj stavke
	 * 
	 * @return Redni broj stavke kao ceo broj tipa Long
	 */
	public Long getSn() {
		return sn;
	}
	/**
	 * Postavlja Redni broj stavke
	 * 
	 * @param sn novi Redni broj stavke kao ceo broj tipa Long
	 * @throws IllegalArgumentException ukoliko je novi Redni broj stavke manji od jedan
	 */
	public void setSn(Long sn) {
		if(sn<=0) {
			throw new IllegalArgumentException("Redni broj ne sme biti manji od jedan!");
		}
		this.sn = sn;
	}
	
	/**
	 * Vraca Vrednost stavke
	 * 
	 * @return Vrednost stavke kao decimalni broj tipa Double
	 */
	public double getItemValue() {
		return itemValue;
	}
	
	/**
	 * Postavlja vrednost za Vrednost stavke
	 * 
	 * @param itemValue nova Vrednost stavke kao decimalni broj tipa Double
	 * @throws IllegalArgumentException ukoliko je nova Vrednost stavke manja od 5000 dinara
	 */
	public void setItemValue(double itemValue) {
		if(itemValue<5000) {
			throw new IllegalArgumentException("Vrednost stavke ne sme biti manja od 5000din!");
		}
		this.itemValue = itemValue;
	}
	
	/**
	 * Vraca Kurs na koji se odnosi stavka
	 * 
	 * @return Kurs na koji se odnosi stavka
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Postavlja Kurs stavke
	 * 
	 * @param course novi Kurs na koji se odnosi stavka
	 * @throws NullPointerException ukoliko je novi Kurs null
	 */
	public void setCourse(Course course) {
		if(course==null) {
			throw new NullPointerException("Kurs ne sme biti null!");
		}
		this.course = course;
	}

}
