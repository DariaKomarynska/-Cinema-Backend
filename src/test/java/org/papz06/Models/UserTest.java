package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.junit.Test;

public class UserTest {

    User us = new User(1, "Poli", "Technik", "pw", "abc");

    @Test
    public void testGetFirstName() {
        assertEquals(us.getFirstName(), "Poli");
    }

    @Test
    public void testGetId() {
        assertEquals(us.getId(), 1);
    }

    @Test
    public void testGetLastName() {
        assertEquals(us.getLastName(), "Technik");
    }

    @Test
    public void testGetLogin() {
        assertEquals(us.getLogin(), "pw");
    }

    @Test
    public void testToJson() {
        JSONObject actual = new JSONObject();
        actual.put("firstName", "Poli");
        actual.put("lastName", "Technik");
        actual.put("id", 1);
        actual.put("login", "pw");
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(us.toJson().toString());
        JsonElement o2 = parser.parse(actual.toString());

        assertEquals(o1, o2);
    }
}
