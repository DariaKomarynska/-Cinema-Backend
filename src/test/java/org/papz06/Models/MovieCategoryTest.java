package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.junit.Test;

public class MovieCategoryTest {
    @Test
    public void testToJson() {
        MovieCategory testMoCate = new MovieCategory(1, "Horror", "Funny");
        JSONObject actual_json = new JSONObject();
        actual_json.put("name", "Horror");
        actual_json.put("id", 1);
        actual_json.put("description", "Funny");
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(actual_json.toString());
        JsonElement o2 = parser.parse(testMoCate.toJson().toString());

        assertEquals(o1, o2);
    }
}
