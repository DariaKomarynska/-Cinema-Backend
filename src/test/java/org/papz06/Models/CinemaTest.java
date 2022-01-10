package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CinemaTest {

    Cinema cinema = new Cinema(1, 1, "ABC", "www.abc.pl", "729444555", "pw@gmail.com", "Ludwika 12", 1);

    @Test
    public void testGetId() {
        int id = cinema.getId();
        assertEquals(id, 1);
    }

    @Test
    public void testGetName() {
        assertEquals(cinema.getName(), "ABC");
    }

    @Test
    public void testToString() {
        assertEquals(cinema.toString(), "1 1 ABC");
    }
}
