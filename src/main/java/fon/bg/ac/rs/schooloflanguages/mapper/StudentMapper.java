package fon.bg.ac.rs.schooloflanguages.mapper;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.model.Student;

public class StudentMapper implements GenericMapper<StudentDto, Student>{

	@Override
	public Student toEntity(StudentDto dto) {
		Student s=new Student();
		s.setId(dto.getId());
		s.setFirstName(dto.getFirstName());
		s.setLastName(dto.getLastName());
		s.setDatumRodjenja(dto.getDatumRodjenja());
		s.setCourses(dto.getCourses());
		return s;
	}

	@Override
	public StudentDto toDto(Student e) {
		StudentDto s=new StudentDto();
		s.setId(e.getId());
		s.setFirstName(e.getFirstName());
		s.setLastName(e.getLastName());
		s.setDatumRodjenja(e.getDatumRodjenja());
		s.setCourses(e.getCourses());
		return s;
	}

}
