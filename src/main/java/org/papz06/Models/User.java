package org.papz06.Models;

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
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
}
