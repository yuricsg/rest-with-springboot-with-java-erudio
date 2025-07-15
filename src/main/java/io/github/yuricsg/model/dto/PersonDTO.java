package io.github.yuricsg.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}



