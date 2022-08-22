package fon.bg.ac.rs.schooloflanguages.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fon.bg.ac.rs.schooloflanguages.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsernameAndPassword(String username, String password);


}
