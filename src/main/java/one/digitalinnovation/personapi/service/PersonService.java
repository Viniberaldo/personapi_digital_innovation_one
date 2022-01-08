package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    
    private final PersonMapper personMapper;
   
   
    public MessageResponseDTO create(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        
        MessageResponseDTO messageResponse = createMessageResponse("Person successfully created with ID ", savedPerson.getId());
        
        return messageResponse;
    }
    
    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(optionalPerson.get());
    }
    
}
