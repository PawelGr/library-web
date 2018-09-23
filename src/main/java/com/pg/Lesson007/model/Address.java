package com.pg.Lesson007.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table
@Entity
@Data
@ToString
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String streetName;

    private String streetNumber;

    private String apartmentNumber;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;
}
