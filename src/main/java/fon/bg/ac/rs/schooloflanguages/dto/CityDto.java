package fon.bg.ac.rs.schooloflanguages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object za entitet Grad.
 * Sadrzi Id, Naziv i PTT broj grada.
 * 
 * @author Kristina
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDto implements Dto{
	/**
	 * Id grada - jedinstveni identifikator Grada u bazi
	 */
	private Long id;

	/**
	 * Naziv grada
	 * Vrednost mora biti jedinstvena u bazi
	 */
	private String name;
	
	/**
	 * PTT broj grada
	 * Duzina broja mora biti tacno 5 broja
	 */
	private int PTT;
	
	/**
	 * Vraca Id grada
	 * 
	 * @return Id grada kao ceo broj tipa Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Postavlja vrednost za Id grada
	 * 
	 * @param id novi Id grada kao ceo broj tipa Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Vraca Naziv grada
	 * 
	 * @return Naziv kursa kao String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Postavlja vrednost za Naziv grada
	 * 
	 * @param name novi Naziv za grada kao String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Vraca PTT grada
	 * 
	 * @return PTT broj grada kao ceo broj tipa int
	 */
	public int getPTT() {
		return PTT;
	}
	
	/**
	 * Postavlja vrednost za PTT broj grada
	 * 
	 * @param pTT novi PTT broj grada kao ceo broj tipa int
	 */
	public void setPTT(int pTT) {
		PTT = pTT;
	}
}
