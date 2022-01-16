package org.papz06.Models;

public class TicketType {
    int id, cinemaId, price;
    String name;

    public TicketType(int id, String name, int price, int cinemaId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cinemaId = cinemaId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice(){
        return price;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    @Override
    public String toString() {
        return id + " " + name
                + " " + price
                + " " + cinemaId;
    }
}
