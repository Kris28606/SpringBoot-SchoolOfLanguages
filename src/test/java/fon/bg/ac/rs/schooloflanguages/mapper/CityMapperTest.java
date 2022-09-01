package fon.bg.ac.rs.schooloflanguages.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fon.bg.ac.rs.schooloflanguages.dto.CityDto;
import fon.bg.ac.rs.schooloflanguages.model.City;

class CityMapperTest {

	public static CityMapper mapper;
	public static City c;
	public static CityDto dto;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mapper=new CityMapper();
		c=new City();
		c.setId(1L);
		c.setName("Smederevska Palanka");
		c.setPTT(11420);
		dto=new CityDto();
		dto.setId(1L);
		dto.setName("Smederevska Palanka");
		dto.setPTT(11420);
	}

	@DisplayName("Test za pretvaranje iz CityDto objekta u City")
	@Test
	void testToEntity() {
		City expected=mapper.toEntity(dto);
		
		assertEquals(dto.getId(), expected.getId());
		assertEquals(dto.getName(), expected.getName());
		assertEquals(dto.getPTT(), expected.getPTT());
	}

	@DisplayName("Test za pretvaranje iz City objekta u CityDto")
	@Test
	void testToDto() {
		CityDto expected=mapper.toDto(c);
		
		assertEquals(c.getId(), expected.getId());
		assertEquals(c.getName(), expected.getName());
		assertEquals(c.getPTT(), expected.getPTT());
	}

}
