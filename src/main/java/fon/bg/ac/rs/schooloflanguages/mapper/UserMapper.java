package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.model.User;

public class UserMapper implements GenericMapper<UserDto, User>{

	@Override
	public User toEntity(UserDto dto) {
		User u=new User();
		u.setPassword(dto.getPassword());
		u.setUsername(dto.getUsername());
		return u;
	}

	@Override
	public UserDto toDto(User e) {
		UserDto u=new UserDto();
		u.setFirstName(e.getFirstName());
		u.setLastName(e.getLastName());
		return u;
	}

}
