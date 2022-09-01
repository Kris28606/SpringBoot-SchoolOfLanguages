package fon.bg.ac.rs.schooloflanguages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet Stavka fakture.
 * Sadrzi Redni broj, Vrednost stavke i Kurs na koji se odnosi;
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceItemDto implements Dto{
	
	/**
	 * Redni broj stavke
	 */
	private Long sn;
	
	/**
	 * Vrednost stavke
	 */
	private double itemValue;
	
	/**
	 * Kurs na koji se odnosi Stavka fakture
	 */
	private CourseDto course;
	
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
	 */
	public void setSn(Long sn) {
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
	 */
	public void setItemValue(double itemValue) {
		this.itemValue = itemValue;
	}
	

	/**
	 * Vraca KursDto na koji se odnosi stavka
	 * 
	 * @return KursDto na koji se odnosi stavka
	 */
	public CourseDto getCourse() {
		return course;
	}
	
	/**
	 * Postavlja KursDto stavke
	 * 
	 * @param course novi KursDto na koji se odnosi stavka
	 */
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	
}
