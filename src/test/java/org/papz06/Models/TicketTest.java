package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest {

    Ticket ticket = new Ticket(5, 3, 18, 4, 2);

    TicketType ticketType = new TicketType(4, "comfort", 250, 4);

    private static final double DELTA = 1e-15;

    @Test
    public void testTicketGetId() {
        int id = ticket.getId();
        assertEquals(5, id);
    }

    @Test
    public void testTicketGetPurchaseId() {
        int id = ticket.getPurchaseId();
        assertEquals(3, id);
    }

    @Test
    public void testTicketGetSeatId() {
        int id = ticket.getSeatId();
        assertEquals(18, id);
    }

    @Test
    public void testTicketGetTicketType() {
        int id = ticket.getTicketTypeId();
        assertEquals(4, id);
    }

    @Test
    public void testTicketGetScheduleId() {
        int id = ticket.getScheduleId();
        assertEquals(2, id);
    }

    @Test
    public void testToString() {
        String ticketStr = ticket.toString();
        String testStr = "5 3 18 4 2";
        assertEquals(testStr, ticketStr);
    }

    @Test
    public void testTicketTypeGetId() {
        int id = ticketType.getId();
        assertEquals(4, id);
    }

    @Test
    public void testTicketTypeGetName() {
        String name = ticketType.getName();
        assertEquals("comfort", name);
    }

    @Test
    public void testTicketTypeGetPrice() {
        float id = ticketType.getPrice();
        assertEquals(250.0, id, DELTA);
    }

    @Test
    public void testTicketTypeGetCinemaId() {
        int id = ticketType.getCinemaId();
        assertEquals(4, id);
    }

}



