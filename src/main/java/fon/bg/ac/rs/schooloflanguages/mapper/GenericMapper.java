package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.Dto;
import fon.bg.ac.rs.schooloflanguages.model.MyEntity;

public interface GenericMapper<D extends Dto, E extends MyEntity> {
	E toEntity(D dto);
	D toDto(E e);

}
