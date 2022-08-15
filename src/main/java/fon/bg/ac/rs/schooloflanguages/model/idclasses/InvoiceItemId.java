package fon.bg.ac.rs.schooloflanguages.model.idclasses;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvoiceItemId implements Serializable{
	
	private Long invoice;
	private Long sn;

}
