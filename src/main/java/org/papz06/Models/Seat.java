package org.papz06.Models;

import java.util.Date;

public class Seat {
    int id, roomId, positionX, positionY;
    String type;

    public Seat(int id, int roomId, int positionX, int positionY, String type) {
        this.id = id;
        this.roomId = roomId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
    }

    public Seat() {
    }

    @Override
    public String toString() {
        return id + " " + roomId
                + " " + positionX
                + " " + positionY
                + " " + type;
    }
}
