package org.papz06.Models;

public class Ticket {
    int id, purchaseId, seatId, ticketTypeId, scheduleId;

    public Ticket(int id, int purchaseId, int seatId, int ticketTypeId, int scheduleId) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.seatId = seatId;
        this.ticketTypeId = ticketTypeId;
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return id + " " + purchaseId
                + " " + seatId
                + " " + ticketTypeId
                + " " + scheduleId;
    }
}
