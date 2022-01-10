package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MovieTest {
    Movie basicMovie = new Movie(1, 90, "5+", 3, "Spider man", "Good", 2);
    Movie basicMovie_noId = new Movie(75, "16+", 3, "Learn in PW", "Very Good", 1);
    @Test
    public void testGetAgeRestriction() {
        assertEquals(basicMovie.getAgeRestriction(), "5+");
        assertEquals(basicMovie_noId.getAgeRestriction(), "16+");
    }

    @Test
    public void testGetCinemaId() {
        assertEquals(basicMovie.getCinemaId(), 2);
        assertEquals(basicMovie_noId.getCinemaId(), 1);
    }

    @Test
    public void testGetDescription() {
        assertEquals(basicMovie.getDescription(), "Good");
        assertEquals(basicMovie_noId.getDescription(), "Very Good");
    }

    @Test
    public void testGetId() {
        assertEquals(basicMovie.getId(), 1);
    }

    @Test
    public void testGetLength() {
        assertEquals(basicMovie.getLength(), 90);
        assertEquals(basicMovie_noId.getLength(), 75);
    }

    @Test
    public void testGetMovieCateId() {
        assertEquals(basicMovie.getMovieCateId(), 2);
        assertEquals(basicMovie_noId.getMovieCateId(), 1);
    }

    @Test
    public void testGetName() {
        assertEquals(basicMovie.getName(), "Spider man");
        assertEquals(basicMovie_noId.getName(), "Learn in PW");
    }

    @Test
    public void testSetId() {
        basicMovie_noId.setId(7);
        assertEquals(basicMovie_noId.getId(), 7);
    }

    @Test
    public void testToJson() {

    }

    @Test
    public void testToJsonDetails() {

    }

    @Test
    public void testToString() {

    }
}
