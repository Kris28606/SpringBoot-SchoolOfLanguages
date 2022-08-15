package fon.bg.ac.rs.schooloflanguages.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fon.bg.ac.rs.schooloflanguages.model.idclasses.InvoiceItemId;

@Entity
@Table(name="invoice_item")
@IdClass(InvoiceItemId.class)
public class InvoiceItem implements MyEntity{
	@Id
	@ManyToOne
	@JoinColumn(name="invoice_id", nullable=false)
	@JsonIgnore
	private Invoice invoice;
	@Id
	private Long sn;
	private double itemValue;
	@ManyToOne
	@JoinColumn(name="course_id", nullable=false)
	private Course course;
	
	
	public InvoiceItem() {
		
	}
	
	public InvoiceItem(Invoice invoice, Long sn, double itemValue, Course course) {
		super();
		this.invoice = invoice;
		this.sn = sn;
		this.itemValue = itemValue;
		this.course = course;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	public double getItemValue() {
		return itemValue;
	}
	public void setItemValue(double itemValue) {
		this.itemValue = itemValue;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

}
