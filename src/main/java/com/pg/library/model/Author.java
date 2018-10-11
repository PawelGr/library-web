package com.pg.library.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table
@Entity
@Data
@ToString
public class Author {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Name can't be null.")
    @NotEmpty(message = "Name can't be empty.")
    @Length(min = 3, max = 10, message = "Name must contain 3-10 characters.")
    @Pattern(regexp = "[a-zA-Ząęźćżółń\\s]+", message = "Invalid value.")
    private String name;

    @NotNull(message = "Surname can't be null.")
    @NotEmpty(message = "Surname can't be empty.")
    @Length(min = 3, max = 15, message = "Surname must contain 3-15 characters.")
    @Pattern(regexp = "[a-zA-Ząęźćżółń\\s]+", message = "Invalid value.")
    private String surname;
}
