package ma.formations.jpa.thymeleaf.service;

import ma.formations.jpa.thymeleaf.dtos.PersonDto;

import java.util.List;

public interface IService {
    List<PersonDto> findAll();

    List<PersonDto> findByFirstnameContainingIgnoreCase(String keyword);

    void save(PersonDto dto);

    PersonDto findById(Long id);

    void deleteById(Long id);
}
