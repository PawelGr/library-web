package com.pg.library.rest.client.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;

}
