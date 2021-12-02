package org.papz06.Models;

public class Seat {
    int id, roomId, positionX, positionY, available;
    String type;

    public Seat(int id, int roomId, int positionX, int positionY, String type, int available) {
        this.id = id;
        this.roomId = roomId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
        this.available = available;
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

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }
}
