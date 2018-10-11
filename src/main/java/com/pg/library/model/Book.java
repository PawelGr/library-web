package com.pg.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Title can't be null.")
    @NotEmpty(message = "Title can't be empty.")
    @Pattern(regexp = "[0-9a-zA-Ząęźćżółń.,\\s]+", message = "Invalid value.")
    private String title;

    @NotNull(message = "ISBN can't be null.")
    @NotEmpty(message = "ISBN can't be empty.")
    @ISBN(message = "Invalid value.")
    private String isbn;

    @NotNull(message = "No. of pages can't be null.")
    @Digits(integer = 4, fraction = 0, message = "Invalid value.")
    private int numberOfPages;

    private boolean hardCover;

    @ManyToOne
    private User user;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

}
