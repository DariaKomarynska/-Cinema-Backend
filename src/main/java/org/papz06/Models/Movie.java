package org.papz06.Models;

public class Movie {
    int id, length, ageRestriction, cinemaId;
    String name, description;

    public Movie(int id, int length, int ageRestriction, int cinemaId, String name, String description) {
        this.id = id;
        this.length = length;
        this.ageRestriction = ageRestriction;
        this.cinemaId = cinemaId;
        this.name = name;
        this.description = description;
    }
    public Movie(){}
    public String Movie(){
        String result = Integer.toString(id)
                + " " + Integer.toString(length)
                + " " + Integer.toString(ageRestriction)
                + " " + Integer.toString(cinemaId)
                + " " + name
                + " " + description;
        return result;
    }
}
