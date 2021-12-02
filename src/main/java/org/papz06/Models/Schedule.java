package org.papz06.Models;

import java.util.Date;

public class Schedule {
    int id, movieId, roomId, seatLeft;
    Date datetime;
    String openSale, closeSale;

    public Schedule(int id, Date datetime, int movieId, int roomId, String openSale, String closeSale, int seatLeft) {
        this.id = id;
        this.datetime = datetime;
        this.movieId = movieId;
        this.roomId = roomId;
        this.openSale = openSale;
        this.closeSale = closeSale;
        this.seatLeft = seatLeft;
    }

    public Schedule() {
    }

    @Override
    public String toString() {
        return id + " " + datetime
                + " " + movieId
                + " " + roomId
                + " " + openSale
                + " " + closeSale
                + " " + seatLeft;
    }
}
