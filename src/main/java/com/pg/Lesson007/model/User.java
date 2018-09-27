package com.pg.Lesson007.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User {

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

    @NotNull(message = "Course can't be null.")
    @NotEmpty(message = "Course can't be empty.")
    @Email(message = "Invalid format.")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Book> book;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Course> course;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
