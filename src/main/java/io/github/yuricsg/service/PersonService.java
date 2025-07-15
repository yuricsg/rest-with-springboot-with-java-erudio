package io.github.yuricsg.service;

import io.github.yuricsg.exception.ResourceNotFoundException;
import io.github.yuricsg.model.Person;
import io.github.yuricsg.model.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.yuricsg.repository.PersonRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static io.github.yuricsg.model.mapper.ObjectMapper.parseListObjects;
import static io.github.yuricsg.model.mapper.ObjectMapper.parseObject;


@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService
            .class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        logger.info(("Finding all people!!"));

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one person!");

        var entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one Peron!");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person){

        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName((person.getLastName()));
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);
    }

}
