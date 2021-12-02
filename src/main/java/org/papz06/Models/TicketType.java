package org.papz06.Models;

public class TicketType {
    int id, cinemaId;
    float price;
    String name;

    public TicketType(int id, String name, float price, int cinemaId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cinemaId = cinemaId;
    }

    public TicketType() {
    }

    @Override
    public String toString() {
        return id + " " + name
                + " " + price
                + " " + cinemaId;
    }
}
