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
            rs = fc.executeQuery("select * from cinemas where available = 1");
            while (rs.next()) {
                cinemasList.add(
                        new Cinema(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7)
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
            rs = fc.executeQuery("select cinema_id, name, website, phoneNumber, address from cinemas where available = 1");
            while (rs.next()) {
                JSONObject cinemaData = new JSONObject();
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("address", rs.getString(5));
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
            String sqlSelect = String.format("select cinema_id, name, website, phoneNumber, address from cinemas where cinema_id = '%d' and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("address", rs.getString(5));
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
            String sqlSelect = String.format("select cinema_id, name, website, phoneNumber, address from cinemas where name = '%s' and available = 1", newName);
            System.out.println(newName);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("address", rs.getString(5));
                System.out.println(cinemaData.toString());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public JSONObject insertNewCinema(int managerId, String newName, String website, String phoneNumber, String address) {
        Function fc = new Function();
        try {
            String sqlInsert = String.format("insert into cinemas values (default, %d, '%s', '%s', '%s', '%s', default)",
                    managerId, newName, website, phoneNumber, address);
            fc.executeQuery(sqlInsert);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getCinemaByName(newName);
    }

    public JSONObject updateCinemaName(Integer id, int managerId, String newName, String website, String phoneNumber, String address) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format("update cinemas set manager_id = %d, name = '%s', website = '%s', phoneNumber = '%s', address = '%s' where cinema_id = %d and available = 1",
                    managerId, newName, website, phoneNumber, address, id);
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
