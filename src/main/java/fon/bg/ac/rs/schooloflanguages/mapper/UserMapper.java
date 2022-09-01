package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.UserDto;
import fon.bg.ac.rs.schooloflanguages.model.User;

/**
 * Maper koji predstavlja implementaciju Generickog mapera za entitet Korisnik.
 * 
 * @author Kristina
 *
 */
public class UserMapper implements GenericMapper<UserDto, User>{

	/**
	 * Transformise Korisnik dto u Korisnik entitet.
	 * 
	 * @param dto - Korisnik dto
	 * @return Korisnik entitet
	 */
	@Override
	public User toEntity(UserDto dto) {
		User u=new User();
		u.setPassword(dto.getPassword());
		u.setUsername(dto.getUsername());
		return u;
	}

	/**
	 * Transformise Korisnik entitet u Korisnik dto.
	 * 
	 * @param e - Korisnik entitet
	 * @return Korisnik dto
	 */
	@Override
	public UserDto toDto(User e) {
		UserDto u=new UserDto();
		u.setFirstName(e.getFirstName());
		u.setLastName(e.getLastName());
		return u;
	}

}
