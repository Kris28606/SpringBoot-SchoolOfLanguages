package fon.bg.ac.rs.schooloflanguages.dto;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object za entitet Kurs.
 * Sadrzi Id, Naziv, Cenu, Datum pocetka i Datum zavrsetka kursa.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto implements Dto{
	
	/**
	 * Id kursa - jedinstveni identifikator Kursa u bazi
	 */
	@NonNull
	private Long id;
	
	/**
	 * Naziv kursa
	 */
	@NotNull(message="Name is required field!")
	@NotEmpty(message="Price is required field!")
	private String name;
	
	/**
	 * Cena kursa
	 */
	@NotNull(message="Price is required field!")
	@NotEmpty(message="Price is required field!")
	private BigDecimal price;
	

	/**
	 * Datum pocetka kursa.
	 * U formatu yyyy-MM-dd
	 */
	@NotNull(message="Start date is required field!")
	@NotEmpty(message="Start date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp startDate;
	
	/**
	 * Datum zavrsetka kursa.
	 * U formatu yyyy-MM-dd
	 */
	@NotNull(message="End date is required field!")
	@NotEmpty(message="End date is required field!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp endDate;
	
	/**
	 * Vraca Id kursa
	 * 
	 * @return Id kursa kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja vrednost za Id kursa
	 * 
	 * @param id novi Id za kurs
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Naziv kursa
	 * 
	 * @return Naziv kursa kao String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Postavlja vrednost za Naziv kursa
	 * 
	 * @param name novi Naziv za kurs kao String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Vraca Cenu kursa
	 * 
	 * @return Cena kursa kao decimalni broj BigDecimal
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Postavlja vrednost za Cenu kursa
	 * 
	 * @param price nova Cena za kurs kao decimalni broj BigDecimal
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * Vraca Datum pocetka kursa
	 * 
	 * @return Datum pocetka kursa kao Timestamp
	 */
	public Timestamp getStartDate() {
		return startDate;
	}
	
	/**
	 * Postavlja vrednost za Datum pocetka kursa
	 * 
	 * @param startDate novi Datum pocetka kursa kao Timestamp
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Vraca Datum zavrsetka kursa
	 * 
	 * @return Datum zavrsetka kursa kao Timestamp
	 */
	public Timestamp getEndDate() {
		return endDate;
	}
	
	/**
	 * Postavlja vrednost za Datum zavrsetka kursa
	 * 
	 * @param endDate novi Datum zavrsetka kursa kao Timestamp
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	

}
