package io.github.yuricsg.controllers;

import io.github.yuricsg.model.Person;
import io.github.yuricsg.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.Serializable;

@RestController
@RequestMapping("/person")
public class PersonController{

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET
    )
    public Person findById(@PathVariable("id")String id){
        return service.findById(id);
    }

}
