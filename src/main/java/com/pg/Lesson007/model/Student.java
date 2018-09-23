package com.pg.Lesson007.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Pole nie może być null")
    @NotEmpty(message = "Pole nie może być puste")
    @Length(min = 3, max = 10, message = "Długość pola min 3 znaki, max 10 znaków")
    @Pattern(regexp = "[a-zA-Ząęźćżółń\\s]+", message = "Niepoprawna wartość pola")
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 10, message = "Długość pola min 3 znaki, max 10 znaków")
    @Pattern(regexp = "[a-zA-Ząęźćżółń\\s]+", message = "Niepoprawna wartość pola")
    private String surname;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 10, message = "Długość pola min 3 znaki, max 10 znaków")
    private String course;

//    @Digits(integer = 2, fraction = 0)
//    @Min, @Max
//    private int age;

    @OneToMany(mappedBy = "student")
    private List<Book> book;

}
