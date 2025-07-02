package io.github.yuricsg.service;

import io.github.yuricsg.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService
            .class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Yuri");
        person.setLastName("França");
        person.setAddress("Recife - BR");
        person.setGender("Male");
        return person;
    }

}
