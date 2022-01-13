package org.papz06.Controllers;

import org.json.JSONArray;
import org.papz06.Function;
import org.papz06.Models.User;

import java.sql.ResultSet;

public class UserController {
    public static JSONArray getAllUser() {
        /*
         * Get all user
         */
        JSONArray result = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from users where available = 1");
            while (rs.next())
                result.put(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)).toJson());
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    static public boolean checkExistUser(String user) {
        /*
         * Check if a user_name exists?
         */
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select count(*) from users where available = 1 and login = \'" + user + "\'");
            rs.next();
            int cnt = rs.getInt(1);
            fc.closeQuery();
            if (cnt == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    static public boolean checkExist(String user, String password) {
        /*
         * Check if a user_name and password exist?
         */
        Function fc = new Function();
        ResultSet rs;
        try {
            String sql = "select count(*) from users where available = 1 and login = \'" + user
                    + "\' and password = \'" + password + "\'";
            rs = fc.executeQuery(sql);
            rs.next();
            int cnt = rs.getInt(1);
            fc.closeQuery();
            if (cnt == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    static public void registerUser(User us) {
        /*
         * Insert new data of user into data base
         */
        Function fc = new Function();
        try {
            String sql = "insert into users values (default, \'"
                    + us.getFirstName() + "\', \'"
                    + us.getLastName() + "\', \'"
                    + us.getLogin() + "\', \'"
                    + us.getPassword() + "\', default)";
            fc.executeQuery(sql);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static public User getUserFromLogin(String login) {
        /*
         * Get user from login
         */
        Function fc = new Function();
        ResultSet rs;
        try {
            String sql = "select * from users where available = 1 and login = \'" + login + "\'";
            rs = fc.executeQuery(sql);
            rs.next();
            User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            fc.closeQuery();
            return us;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    static public User getUserFromId(int id) {
        Function fc = new Function();
        ResultSet rs;
        try {
            String sql = "select * from users where available = 1 and user_id = " + id;
            rs = fc.executeQuery(sql);
            rs.next();
            User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            fc.closeQuery();
            return us;
        } catch (Exception e) {
            return null;
        }
    }
}
