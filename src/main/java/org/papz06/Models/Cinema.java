package org.papz06.Models;

public class Cinema {
    int id, managerId;
    String name;

    public Cinema(int id, int managerId, String name) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
    }

    public Cinema() {
    }

    public String toString() {
        return id + " " + managerId + " " + name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
