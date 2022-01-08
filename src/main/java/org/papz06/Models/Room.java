package org.papz06.Models;

import org.json.JSONObject;

public class Room {
    int id, rowsNumber, seatsInRowNumber, cinemaId;
    String name;

    public Room(int id, String name, int rowsNumber, int seatsInRowNumber, int cinemaId) {
        this.id = id;
        this.rowsNumber = rowsNumber;
        this.seatsInRowNumber = seatsInRowNumber;
        this.cinemaId = cinemaId;
        this.name = name;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return id + " " + rowsNumber
                + " " + seatsInRowNumber
                + " " + cinemaId
                + " " + name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        result.put("rowsNumber", rowsNumber);
        result.put("seatsInRowNumber", seatsInRowNumber);
        result.put("cinemaId", cinemaId);
        return result;
    }
}
