package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.Dto;
import fon.bg.ac.rs.schooloflanguages.model.MyEntity;

/**
 * Interfejs koji predstavlja Genericki maper kojeg svi ostali maperi implementiraju.
 * @author Kristina
 *
 * @param <D> - Objekat koji nasledjuje interfejs Dto
 * @param <E> - Objekat koji nasledjuje interfejs MyEntity
 */
public interface GenericMapper<D extends Dto, E extends MyEntity> {
	/**
	 * Transformise Data Transfer Object u Entity.
	 * 
	 * @param dto - Objekat koji nasledjuje interfejs Dto
	 * @return Entitet koji nasledjuje interfejs MyEntity
	 */
	E toEntity(D dto);
	
	/**
	 * Transformira Entity u Data Transfer Object.
	 * @param e - Entitet koji nasledjuje interfejs MyEntity
	 * @return Dto objekat koji nasledjuje interfejs Dto.
	 */
	D toDto(E e);

}
