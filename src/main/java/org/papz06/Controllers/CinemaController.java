package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Cinema;
import org.papz06.Models.User;

import java.net.Inet4Address;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaController {

    List<Cinema> cinemasList = new ArrayList<>();

//    public CinemaController(){}

    public CinemaController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from cinemas");
            while (rs.next()) {
                cinemasList.add(
                        new Cinema(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3)
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JSONArray getCinemaData() {
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select cinema_id, name from cinemas");
            while (rs.next()) {
                JSONObject cinemaData = new JSONObject();
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                resultData.put(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public JSONObject getCinemaById(Integer id) {
        JSONObject cinemaData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select cinema_id, name from cinemas where cinema_id = '%d'", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public JSONObject getCinemaByName(String newName) {
        Function fc = new Function();
        JSONObject cinemaData = new JSONObject();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select cinema_id, name from cinemas where name = '%s'", newName);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public JSONObject insertNewCinema(String newName) {
        Function fc = new Function();
        try {
            String sqlInsert = String.format("insert into cinemas values (default, null, '%s')", newName);
            fc.executeQuery(sqlInsert);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getCinemaByName(newName);
    }

    public JSONObject updateCinemaName(Integer id, String newName) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format("update cinemas set name = '%s' where cinema_id = %d", newName, id);
            fc.executeQuery(sqlUpdate);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getCinemaById(id);
    }

    public JSONObject deleteCinema(Integer id) {
        Function fc = new Function();
        try {
            String sqlDelete = String.format("update cinemas set available = 0 where cinema_id = %d", id);
            fc.executeQuery(sqlDelete);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return new JSONObject();
    }

    public boolean checkExist(Integer id) {
        for (Cinema cin : cinemasList) {
            if (cin.getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean checkExist(String name) {
        for (Cinema cin : cinemasList) {
            if (cin.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean isEmptyList() {
        return cinemasList.size() == 0;
    }

    public boolean sizeNewNameCinema(String newName) {
        return newName.length() != 0;
    }
}
