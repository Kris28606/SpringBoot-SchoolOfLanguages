package fon.bg.ac.rs.schooloflanguages.model.idclasses;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemId implements Serializable{
	
	private Long invoice;
	private Long sn;
	@Override
	public int hashCode() {
		return Objects.hash(invoice, sn);
	}
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
