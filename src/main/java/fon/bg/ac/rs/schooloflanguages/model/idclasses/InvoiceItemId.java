package fon.bg.ac.rs.schooloflanguages.model.idclasses;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa koja predstavlja slozeni primarni kljuc za entitet Faktura.
 * 
 * @author Kristina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemId implements Serializable{
	
	/**
	 * Id fakture na koju se odnosi
	 */
	private Long invoice;
	/**
	 * Redni broj stavke fakture na koju se odnosi
	 */
	private Long sn;
	
	/**
	 * Generise hash kod 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(invoice, sn);
	}
	
	/**
	 * Poredi dva kursa po nazivu kursa
	 * 
	 * @return
	 * <ul>
	 * 	<li>true - Ako je FakturaId i RedniBroj stavke isti kod Stavke fakture</li>
	 *  <li>false - U svakom drugom slucaju </li>
	 * </ul>
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceItemId other = (InvoiceItemId) obj;
		return Objects.equals(invoice, other.invoice) && Objects.equals(sn, other.sn);
	}
	
	
}
