package io.github.yuricsg.service;

import io.github.yuricsg.exception.ResourceNotFoundException;
import io.github.yuricsg.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.yuricsg.repository.PersonRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService
            .class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info(("Finding all people!!"));
        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding one person!");

        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

    }

    public Person create(Person person){
        logger.info("Creating one Peron!");

        return repository.save(person);
    }

    public Person update(Person person){

        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName((person.getLastName()));
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);
    }

}
