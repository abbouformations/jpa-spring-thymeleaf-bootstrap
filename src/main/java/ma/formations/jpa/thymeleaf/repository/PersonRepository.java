package ma.formations.jpa.thymeleaf.repository;

import java.util.List;


import ma.formations.jpa.thymeleaf.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  List<Person> findByFirstnameContainingIgnoreCase(String firstname);
}
