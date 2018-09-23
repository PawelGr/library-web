package com.pg.Lesson007.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;

    @ManyToOne
    private User user;

}
