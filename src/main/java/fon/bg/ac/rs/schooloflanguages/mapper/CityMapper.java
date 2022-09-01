package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.CityDto;
import fon.bg.ac.rs.schooloflanguages.model.City;

/**
 * Maper koji predstavlja implementaciju Generickog mapera za entitet Grad.
 * 
 * @author Kristina
 *
 */
public class CityMapper implements GenericMapper<CityDto, City>{
	/**
	 * Transformise Grad dto u Grad entitet.
	 * 
	 * @param dto - Grad dto
	 * @return Grad entitet
	 */
	@Override
	public City toEntity(CityDto dto) {
		City c=new City();
		c.setId(dto.getId());
		c.setName(dto.getName());
		c.setPTT(dto.getPTT());
		return c;
	}
	
	/**
	 * Transformise Grad entitet u Grad dto.
	 * 
	 * @param e - Grad entitet
	 * @return Grad dto
	 */
	@Override
	public CityDto toDto(City e) {
		CityDto c=new CityDto();
		c.setId(e.getId());
		c.setName(e.getName());
		c.setPTT(e.getPTT());
		return c;
	}

}
