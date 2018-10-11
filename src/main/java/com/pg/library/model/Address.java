package com.pg.library.model;

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

    @NotNull(message = "City can't be null.")
    @NotEmpty(message = "City can't be empty.")
    private String city;

    @NotNull(message = "Street name can't be null.")
    @NotEmpty(message = "Street name can't be empty.")
    @Pattern(regexp = "[a-zA-Ząęźćżółń\\s]+", message = "Invalid value.")
    private String streetName;

    @NotNull(message = "Street number can't be null.")
    @NotEmpty(message = "Street number can't be empty.")
    @Pattern(regexp = "[a-zA-Z0-9/\\s]+", message = "Invalid value.")
    private String streetNumber;

    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "Invalid value.")
    private String apartmentNumber;

    @NotNull(message = "Name can't be null.")
    @NotEmpty
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Zip code format is XX-XXX")
    private String zipCode;
}
