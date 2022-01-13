package org.papz06.Models;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

public class ScheduleTest {
    String epoch_1 = "1641823200000"; // 10 January 2022 14:00:00
    String epoch_2 = "1641196800000"; // 3 January 2022 08:00:00
    Date datetime = new Date(Long.parseLong(epoch_1));
    Date closeSale = datetime;
    Date openSale = new Date(Long.parseLong(epoch_2));
    Movie basicMovie = new Movie(1, 90, "5+", 3, "Spider man", "Good", 2);
    Room basicRoom = new Room(3, "Room 1", 5, 7, 2);
    Schedule basicSchedule = new Schedule(5, 70, datetime, openSale, closeSale, basicMovie, basicRoom);

    @Test
    public void testGetCloseSale() {
        assertEquals(basicSchedule.getCloseSale(), epoch_1);
    }

    @Test
    public void testGetDateTime() {
        assertEquals(basicSchedule.getDateTime(), epoch_1);
    }

    @Test
    public void testGetFilmId() {
        assertEquals(basicSchedule.getFilmId(), 1);
    }

    @Test
    public void testGetOpenSale() {
        assertEquals(basicSchedule.getOpenSale(), epoch_2);
    }

    @Test
    public void testGetRoomId() {
        assertEquals(basicSchedule.getRoomId(), 3);
    }

    @Test
    public void testGetSeatLeft() {
        assertEquals(basicSchedule.getSeatLeft(), 70);
    }

    @Test
    public void testToJsonDetails() {
        String actual = "{" +
                "    \"datetime\": \"Mon Jan 10 15:00:00 CET 2022\"," +
                "    \"closeSale\": \"Mon Jan 10 15:00:00 CET 2022\"," +
                "    \"seatLeft\": 70," +
                "    \"openSale\": \"Mon Jan 03 09:00:00 CET 2022\"," +
                "    \"id\": 5," +
                "    \"film\": {" +
                "        \"name\": \"Spider man\"," +
                "        \"length\": 90," +
                "        \"description\": \"Good\"," +
                "        \"ageRestriction\": \"5+\"," +
                "        \"movieCategory\": {}," +
                "        \"id\": 1" +
                "    }," +
                "    \"room\": {" +
                "        \"rowsNumber\": 5," +
                "        \"seatsInRowNumber\": 7," +
                "        \"cinemaId\": 2," +
                "        \"name\": \"Room 1\"," +
                "        \"id\": 3," +
                "        \"seats\": []" +
                "    }" +
                "}";
        String filmOut = "{    \"film\": {" +
                "        \"name\": \"Spider man\"," +
                "        \"length\": 90," +
                "        \"description\": \"Good\"," +
                "        \"ageRestriction\": \"5+\"," +
                "        \"movieCategory\": {}," +
                "        \"id\": 1" +
                "    }},";
        String roomOut = "{    \"room\": {" +
                "        \"rowsNumber\": 5," +
                "        \"seatsInRowNumber\": 7," +
                "        \"cinemaId\": 2," +
                "        \"name\": \"Room 1\"," +
                "        \"id\": 3," +
                "        \"seats\": []" +
                "    }}";
        Movie mock_mv = Mockito.mock(Movie.class);
        Room mock_rm = Mockito.mock(Room.class);
        when(mock_mv.toJson()).thenReturn(new JSONObject(filmOut));
        when(mock_rm.toJsonDetails(anyInt())).thenReturn(new JSONObject(roomOut));
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(actual);
        JsonElement o2 = parser.parse(basicSchedule.toJsonDetails().toString());

        assertEquals(o1, o2);
    }

    @Test
    public void testToJsonGeneral() {
        String actual = "{" +
                "    \"datetime\": \"Mon Jan 10 15:00:00 CET 2022\"," +
                "    \"closeSale\": \"Mon Jan 10 15:00:00 CET 2022\"," +
                "    \"seatLeft\": 70," +
                "    \"openSale\": \"Mon Jan 03 09:00:00 CET 2022\"," +
                "    \"id\": 5," +
                "    \"film\": {" +
                "        \"name\": \"Spider man\"," +
                "        \"length\": 90," +
                "        \"description\": \"Good\"," +
                "        \"ageRestriction\": \"5+\"," +
                "        \"movieCategory\": {}," +
                "        \"id\": 1" +
                "    }," +
                "    \"room\": {" +
                "        \"rowsNumber\": 5," +
                "        \"seatsInRowNumber\": 7," +
                "        \"cinemaId\": 2," +
                "        \"name\": \"Room 1\"," +
                "        \"id\": 3" +
                "    }" +
                "}";
        String filmOut = "{    \"film\": {" +
                "        \"name\": \"Spider man\"," +
                "        \"length\": 90," +
                "        \"description\": \"Good\"," +
                "        \"ageRestriction\": \"5+\"," +
                "        \"movieCategory\": {}," +
                "        \"id\": 1" +
                "    }},";
        String roomOut = "{    \"room\": {" +
                "        \"rowsNumber\": 5," +
                "        \"seatsInRowNumber\": 7," +
                "        \"cinemaId\": 2," +
                "        \"name\": \"Room 1\"," +
                "        \"id\": 3" +
                "    }}";
        Movie mock_mv = Mockito.mock(Movie.class);
        Room mock_rm = Mockito.mock(Room.class);
        when(mock_mv.toJson()).thenReturn(new JSONObject(filmOut));
        when(mock_rm.toJson()).thenReturn(new JSONObject(roomOut));
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(actual);
        JsonElement o2 = parser.parse(basicSchedule.toJsonGeneral().toString());

        assertEquals(o1, o2);
    }

    @Test
    public void testToJsonShort() {
        String actual = "{" +
        "    \"datetime\": \"Mon Jan 10 15:00:00 CET 2022\"," +
        "    \"closeSale\": \"Mon Jan 10 15:00:00 CET 2022\"," +
        "    \"seatLeft\": 70," +
        "    \"filmId\": 1," +
        "    \"openSale\": \"Mon Jan 03 09:00:00 CET 2022\"," +
        "    \"id\": 5," +
        "    \"roomId\": 3" +
        "}";
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(actual);
        JsonElement o2 = parser.parse(basicSchedule.toJsonShort().toString());

        assertEquals(o1, o2);
    }
}
