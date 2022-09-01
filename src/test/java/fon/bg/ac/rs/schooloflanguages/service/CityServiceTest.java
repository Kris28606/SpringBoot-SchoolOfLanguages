package fon.bg.ac.rs.schooloflanguages.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fon.bg.ac.rs.schooloflanguages.model.City;
import fon.bg.ac.rs.schooloflanguages.repository.CityRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CityServiceTest{
	
	@MockBean
	public CityRepository cityRepository;
	
	@Autowired
	public CityService cityService;

	@Test
	void testGetAll() {
		List<City> cities=new ArrayList<>();
		City c1=new City();
		c1.setId(1L);
		c1.setName("Smederevska Palanka");
		c1.setPTT(11420);
		City c2=new City();
		c2.setId(2L);
		c2.setName("Beograd");
		c2.setPTT(11000);
		cities.add(c1);
		cities.add(c2);
		when(cityService.getAll()).thenReturn(cities);
		
		assertEquals(2, cityService.getAll().size());
	}

}
