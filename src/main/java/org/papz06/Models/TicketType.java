package org.papz06.Models;

import java.util.Date;

public class TicketType {
    int id, cinemaId;
    float price;
    String Name;

    public TicketType(int id, int cinemaId, float price, String Name) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.price = price;
        this.Name = Name;
    }

    public TicketType() {
    }

    @Override
    public String toString() {
        return id + " " + cinemaId
                + " " + price
                + " " + Name;
    }
}
