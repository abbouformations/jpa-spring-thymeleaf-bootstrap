package ma.formations.jpa.thymeleaf.service;

import lombok.AllArgsConstructor;
import ma.formations.jpa.thymeleaf.dtos.PersonDto;
import ma.formations.jpa.thymeleaf.entity.Person;
import ma.formations.jpa.thymeleaf.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceImpl implements IService {
    private PersonRepository personRepository;
    private ModelMapper modelMapper;

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll().stream().map(bo -> modelMapper.map(bo, PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> findByFirstnameContainingIgnoreCase(String firstname) {
        return personRepository.findByFirstnameContainingIgnoreCase(
                firstname).stream().map(bo -> modelMapper.map(bo, PersonDto.class)).toList();
    }

    @Override
    public void save(PersonDto dto) {
        personRepository.save(modelMapper.map(dto, Person.class));
    }

    @Override
    public PersonDto findById(Long id) {
        return modelMapper.map(personRepository.findById(id).orElseThrow(
                        () -> new RuntimeException(String.format("No person with ID [%s] exist !", id))),
                PersonDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            throw new RuntimeException("Enter a correct id person");
        Person personFound = personRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("No person with ID [%s] exist !", id)));
        personRepository.delete(personFound);
    }
}
