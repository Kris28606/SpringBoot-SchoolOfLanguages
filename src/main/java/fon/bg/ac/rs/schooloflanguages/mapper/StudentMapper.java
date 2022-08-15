package fon.bg.ac.rs.schooloflanguages.mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.format.datetime.standard.InstantFormatter;

import fon.bg.ac.rs.schooloflanguages.dto.StudentDto;
import fon.bg.ac.rs.schooloflanguages.model.Student;

public class StudentMapper implements GenericMapper<StudentDto, Student>{

	private CourseMapper courseMapper;
	
	public StudentMapper() {
		this.courseMapper=new CourseMapper();
	}
	
	@Override
	public Student toEntity(StudentDto dto) {
		Student s=new Student();
		s.setId(dto.getId());
		s.setFirstName(dto.getFirstName());
		s.setLastName(dto.getLastName());
		s.setCourses(dto.getCourses().stream().map((course)->{
			return courseMapper.toEntity(course);
		}).collect(Collectors.toList()));
		s.setDatumRodjenja(dto.getDatumRodjenja());
		s.setSlika(dto.getSlika());
		return s;
	}

	@Override
	public StudentDto toDto(Student e) {
		StudentDto s=new StudentDto();
		s.setId(e.getId());
		s.setFirstName(e.getFirstName());
		s.setLastName(e.getLastName());
		s.setDatumRodjenja(e.getDatumRodjenja());
		s.setCourses(e.getCourses().stream().map((course)->{
			return courseMapper.toDto(course);
		}).collect(Collectors.toList()));
		s.setSlika(e.getSlika());
		long trenutnaGodina=Timestamp.from(Instant.now()).getYear();
		long godinaRodjenja=e.getDatumRodjenja().getYear();
		long mesecRodjenja=e.getDatumRodjenja().getMonth();
		long danRodjenja=e.getDatumRodjenja().getDate();
		long trenutniMesec=Timestamp.from(Instant.now()).getMonth();
		long trenutniDan=Timestamp.from(Instant.now()).getDate();
		s.setYears((int) (trenutnaGodina-godinaRodjenja));
		if(trenutniMesec<=mesecRodjenja && trenutniDan<danRodjenja) {
			s.setYears(s.getYears()-1);
		}
		return s;
	}

}
