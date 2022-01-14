package org.papz06.Models;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.papz06.Controllers.MovieCategoryController;
import org.papz06.Controllers.SeatController;

public class RoomTest {
    Room room = new Room(5, "Room 5", 5, 6, 3);


    @Test
    public void testGetId() {
        int id = room.getId();
        assertEquals(5, id);
    }

    @Test
    public void testGetName() {
        String name = room.getName();
        assertEquals("Room 5", name);
    }

    @Test
    public void testGetRowsNumber() {
        assertEquals(5, room.getRowsNumber());
    }
//
    @Test
    public void testGetSeatsInRowNumber() {
        assertEquals(6, room.getSeatsInRowNumber());
    }

    @Test
    public void testGetCinemaId() {
        Integer cinId = 3;
        assertEquals(cinId, room.getCinemaId());
    }

    @Test
    public void testToJson() {
        JSONObject result = new JSONObject();
        result.put("id", 5);
        result.put("name", "Room 5");
        result.put("rowsNumber", 5);
        result.put("seatsInRowNumber", 6);
        result.put("cinemaId", 3);
        assertEquals(result.toString(), room.toJson().toString());
    }

}
