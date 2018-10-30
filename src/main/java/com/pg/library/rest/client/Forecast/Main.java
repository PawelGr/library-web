package com.pg.library.rest.client.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Main {

    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;
}
