package fon.bg.ac.rs.schooloflanguages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pomocni Data Transfer Object koji sluzi da prihvati kriterijum za pretragu nekog entiteta.
 * Sadrzi samo taj Kriterijum.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto implements Dto{
	
	/**
	 * Kriterijum za pretragu
	 */
	private String kriterijum;

	/**
	 * Vraca Kriterijum 
	 * 
	 * @return Kriterijum za pretragu kao String
	 */
	public String getKriterijum() {
		return kriterijum;
	}
	
	/**
	 * Postavlja vrednost za Kriterijum
	 * 
	 * @param kriterijum novi Kriterijum za pretragu kao String
	 */
	public void setKriterijum(String kriterijum) {
		this.kriterijum = kriterijum;
	}
}
