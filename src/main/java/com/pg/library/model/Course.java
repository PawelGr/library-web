package com.pg.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Title can't be null.")
    @NotEmpty(message = "Title can't be empty.")
    @Pattern(regexp = "[a-zA-Z.,\\s]+", message = "Invalid value.")
    private String name;

    @ManyToMany
    private List<User> user;

}
