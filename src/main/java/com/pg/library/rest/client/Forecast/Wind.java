package com.pg.library.rest.client.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Wind {
    private double speed;
    private Integer deg;
}
