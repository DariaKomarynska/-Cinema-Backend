package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

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
        System.out.print(basicSchedule.toJsonDetails());
    }

    @Test
    public void testToJsonGeneral() {
        
    }

    @Test
    public void testToJsonShort() {

    }
}
