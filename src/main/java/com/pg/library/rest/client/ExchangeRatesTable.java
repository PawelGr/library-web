package com.pg.library.rest.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
public class ExchangeRatesTable {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
