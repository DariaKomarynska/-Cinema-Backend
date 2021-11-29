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

    public JSONArray getCinemaData(){
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
            System.out.println("Exception: "+ e);
        }
        return resultData;
    }

    public JSONArray getCinemaById(Integer id){
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select cinema_id, name from cinemas where cinema_id = %d", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject cinemaData = new JSONObject();
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                resultData.put(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
        return resultData;
    }

    public void insertNewCinema(String newName){
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlInsert = String.format("insert into cinemas values (default, null, '%s')", newName);
            rs = fc.executeQuery(sqlInsert);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
    }

    public JSONArray getInsertedNewCinema(String newName){
        JSONArray resultData =  new JSONArray();
        insertNewCinema(newName);
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select cinema_id, name from cinemas where name = '%s'", newName);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject cinemaData = new JSONObject();
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                resultData.put(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
        return resultData;
    }

    public boolean checkExist(Integer id) {
        for (Cinema cin : cinemasList){
            if (cin.getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean checkExist(String name) {
        for (Cinema cin : cinemasList){
            if (cin.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean isEmptyList() {
        if (cinemasList.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean sizeNewNameCinema(String newName) {
        if (newName.length() == 0) {
            return false;
        }
        return true;
    }
}
