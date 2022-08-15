package fon.bg.ac.rs.schooloflanguages.model.idclasses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemId implements Serializable{
	
	private Long invoice;
	private Long sn;
	
}
