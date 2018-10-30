package com.pg.library.rest.client.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Forecast {
    private Coord coord;
    private Weather weather[];
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private String dt;
    private Sys sys;
    private Integer id;
    private String name;
    private String code;
}
