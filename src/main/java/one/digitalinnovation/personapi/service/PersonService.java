package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = (@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personMapper = PersonMapper.INSTANCE;
        this.personRepository = personRepository;
    }

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully created with ID ", savedPerson.getId());

        return messageResponse;
    }

    public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(optionalPerson.get());
    }

    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        Person updatedPerson = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(updatedPerson);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully updated with ID ", savedPerson.getId());

        return messageResponse;
    }

    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

}
