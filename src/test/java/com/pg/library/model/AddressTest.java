package com.pg.library.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    Address address = new Address();

    @Test
    public void setId() {
        address.setId(27);
        assertEquals(27L, address.getId().longValue());
    }

    @Test
    public void setCity() {
        address.setCity("Warsaw");
        assertEquals("Warsaw", address.getCity());
    }

    @Test
    public void setStreetName() {
        address.setStreetName("Szkolna");
        assertEquals("Szkolna", address.getStreetName());
    }

    @Test
    public void setStreetNumber() {
        address.setStreetNumber("25A");
        assertEquals("25A", address.getStreetNumber());
    }

    @Test
    public void setApartmentNumber() {
        address.setApartmentNumber("101");
        assertEquals("101", address.getApartmentNumber());
    }

    @Test
    public void setZipCode() {
        address.setZipCode("03-214");
        assertEquals("03-214", address.getZipCode());
    }
}