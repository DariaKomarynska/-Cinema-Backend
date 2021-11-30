package org.papz06.Models;

import org.json.JSONObject;
import org.papz06.Controllers.MovieCategoryController;

public class User {
    int id;
    String firstName, lastName, login, password;

    public User(int id, String firstName, String lastName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(String firstName, String lastName, String login, String password) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public int getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("login", login);
        return result;
    }
}
