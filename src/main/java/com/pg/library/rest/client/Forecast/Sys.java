package com.pg.library.rest.client.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Sys {
    private Integer type;
    private Integer id;
    private double message;
    private String country;
    private String sunrise;
    private String sunset;

}
