package fon.bg.ac.rs.schooloflanguages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto implements Dto{
	private String kriterijum;

	public String getKriterijum() {
		return kriterijum;
	}

	public void setKriterijum(String kriterijum) {
		this.kriterijum = kriterijum;
	}
}
