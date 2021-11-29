package org.papz06.Models;

import org.json.JSONObject;

public class MovieCategory {
    int id;
    String name, description;

    public MovieCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public MovieCategory() {
    }

    public String toString() {
        return id + " " + name + ' ' + description;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        return result;
    }
}
