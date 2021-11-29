package org.papz06.Models;

public class Movie {
    int id, length, cinemaId, movieCateId;
    String name, description, ageRestriction;

    public Movie(int id, int length, String ageRestriction, int cinemaId, String name, String description, int movieCateId) {
        this.id = id;
        this.length = length;
        this.ageRestriction = ageRestriction;
        this.cinemaId = cinemaId;
        this.name = name;
        this.description = description;
        this.movieCateId = movieCateId;
    }

    public Movie(int length, String ageRestriction, int cinemaId, String name, String description, int movieCateId) {
        this.length = length;
        this.ageRestriction = ageRestriction;
        this.cinemaId = cinemaId;
        this.name = name;
        this.description = description;
        this.movieCateId = movieCateId;
    }

    public Movie() {
    }

    public String toString() {
        return id + " " + length
                + " " + ageRestriction
                + " " + cinemaId
                + " " + name
                + " " + description;
    }
}
