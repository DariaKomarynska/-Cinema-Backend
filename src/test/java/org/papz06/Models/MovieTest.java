package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.papz06.Controllers.MovieCategoryController;

public class MovieTest {
    Movie basicMovie = new Movie(1, 90, "5+", 3, "Spider man", "Good", 2);
    Movie basicMovie_noId = new Movie(75, "16+", 1, "Learn in PW", "Very Good", 1);
    @Test
    public void testGetAgeRestriction() {
        assertEquals(basicMovie.getAgeRestriction(), "5+");
        assertEquals(basicMovie_noId.getAgeRestriction(), "16+");
    }

    @Test
    public void testGetCinemaId() {
        assertEquals(basicMovie.getCinemaId(), 3);
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
        JSONObject mvCate = new JSONObject();
        JSONObject actual = new JSONObject();
        mvCate.put("name", "romantic");
        mvCate.put("description", "funny");
        mvCate.put("id", 2);
        actual.put("name" , "Spider man");
        actual.put("length" , 90);
        actual.put("description" , "Good");
        actual.put("ageRestriction" , "5+");
        actual.put("movieCategory", mvCate);
        actual.put("id" , 1);

        MockedStatic<MovieCategoryController> mockedStatic = Mockito.mockStatic(MovieCategoryController.class);
        mockedStatic.when(()->MovieCategoryController.getMovieCategoryById(2)).thenReturn(mvCate);

        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(actual.toString());
        JsonElement o2 = parser.parse(basicMovie.toJson().toString());

        assertEquals(o1, o2);
    }
}
