package org.papz06.Models;

import java.util.Date;

public class Ticket {
    int id, purchaseId, seatId, ticketTypeId, scheduleId;

    public Ticket(int id, int purchaseId, int seatId, int ticketTypeId, int scheduleId) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.seatId = seatId;
        this.ticketTypeId = ticketTypeId;
        this.scheduleId = scheduleId;
    }

    public Ticket() {
    }

    @Override
    public String toString() {
        return id + " " + purchaseId
                + " " + seatId
                + " " + ticketTypeId
                + " " + scheduleId;
    }
}
