package org.papz06.Models;

public class MovieCategory {
    int id, cinemaId;
    String name, description;

    public MovieCategory(int id, String name, String description, int cinemaId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cinemaId = cinemaId;
    }

    public MovieCategory() {
    }

    public String toString() {
        return id + " " + name + ' ' + description + ' ' + cinemaId;
    }
}
