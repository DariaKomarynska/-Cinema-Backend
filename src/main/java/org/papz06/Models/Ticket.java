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

    public int getId(){
        return id;
    }

    public int getPurchaseId(){
        return purchaseId;
    }

    public int getSeatId(){
        return seatId;
    }

    public int getTicketTypeId(){
        return ticketTypeId;
    }

    public int getScheduleId(){
        return scheduleId;
    }

    @Override
    public String toString() {
        return id + " " + purchaseId
                + " " + seatId
                + " " + ticketTypeId
                + " " + scheduleId;
    }
}
