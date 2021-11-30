package org.papz06.Controllers;

import org.json.JSONArray;
import org.papz06.Function;
import org.papz06.Models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    List<User> userList = new ArrayList<>();

//    public UserController() {
//        Function fc = new Function();
//        ResultSet rs;
//        try {
//            rs = fc.executeQuery("select * from users");
//            while (rs.next()) {
//                userList.add(
//                        new User(rs.getInt(1),
//                                rs.getString(2),
//                                rs.getString(3),
//                                rs.getString(4),
//                                rs.getString(5)
//                        )
//                );
//            }
//            fc.closeQuery();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public static JSONArray getAllUser() {
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
        Function fc = new Function();
        try {
            String sql = "insert into users values (default, \'"
                    + us.getFirstName() + "\', \'"
                    + us.getLastName() + "\', \'"
                    + us.getLogin() + "\', \'"
                    + us.getPassword() + "\', default)";
            // Implement
            fc.executeQuery(sql);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static public User getUserFromLogin(String login) {
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
}
