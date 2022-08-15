package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.CityDto;
import fon.bg.ac.rs.schooloflanguages.model.City;

public class CityMapper implements GenericMapper<CityDto, City>{

	@Override
	public City toEntity(CityDto dto) {
		City c=new City();
		c.setId(dto.getId());
		c.setName(dto.getName());
		c.setPTT(dto.getPTT());
		return c;
	}

	@Override
	public CityDto toDto(City e) {
		CityDto c=new CityDto();
		c.setId(e.getId());
		c.setName(e.getName());
		c.setPTT(e.getPTT());
		return c;
	}

}
