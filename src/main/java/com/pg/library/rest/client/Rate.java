package com.pg.library.rest.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Rate {
    private String currency;
    private String code;
    private Double mid;
}
