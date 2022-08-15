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

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto implements Dto{
	@NonNull
	private Long id;
	@NotNull(message="Date is required field!")
	@NotEmpty(message="Date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp date;
	private double totalPrice;
	private PaymentMethod paymentMethod;
	private boolean cancelled;
	private StudentDto student;
	@NotEmpty(message="Items can't be empty!")
	private List<InvoiceItemDto> items;
	
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
	public StudentDto getStudent() {
		return student;
	}
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	public List<InvoiceItemDto> getItems() {
		return items;
	}
	public void setItems(List<InvoiceItemDto> items) {
		this.items = items;
	}
}
