package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Cinema;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CinemaController {

    public static List<Cinema> getCinemasList() {
        List<Cinema> cinemasList = new ArrayList<>();
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
                                rs.getString(7),
                                rs.getInt(8)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cinemasList;
    }

    public static JSONArray getCinemaData() {
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery(
                    "select cinema_id, name, website, phoneNumber, email, address from cinemas where available = 1");
            while (rs.next()) {
                JSONObject cinemaData = new JSONObject();
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("email", rs.getString(5));
                cinemaData.put("address", rs.getString(6));
                resultData.put(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public static JSONObject getCinemaById(Integer id) {
        JSONObject cinemaData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format(
                    "select cinema_id, name, website, phoneNumber, email, address from cinemas where cinema_id = '%d' and available = 1",
                    id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("email", rs.getString(5));
                cinemaData.put("address", rs.getString(6));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public static JSONObject getCinemaByName(String newName) {
        Function fc = new Function();
        JSONObject cinemaData = new JSONObject();
        ResultSet rs;
        try {
            String sqlSelect = String.format(
                    "select cinema_id, name, website, phoneNumber, email, address from cinemas where name = '%s' and available = 1",
                    newName);
            System.out.println(newName);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                cinemaData.put("id", rs.getInt(1));
                cinemaData.put("name", rs.getString(2));
                cinemaData.put("website", rs.getString(3));
                cinemaData.put("phoneNumber", rs.getString(4));
                cinemaData.put("email", rs.getString(5));
                cinemaData.put("address", rs.getString(6));
                System.out.println(cinemaData.toString());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public static JSONObject insertNewCinema(int managerId, String newName, String website, String phoneNumber, String email, String address) {
        Function fc = new Function();
        try {
            String sqlInsert = String.format(
                    "insert into cinemas values (default, %d, '%s', '%s', '%s', '%s', '%s', default)",
                    managerId, newName, website, phoneNumber, email, address);
            fc.executeQuery(sqlInsert);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getCinemaByName(newName);
    }


    public static JSONObject updateCinemaName(Integer id, int managerId, String newName, String website,
                                              String phoneNumber, String email, String address) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format(
                    "update cinemas set manager_id = %d, name = '%s', website = '%s', phoneNumber = '%s', email = '%s', address = '%s' where cinema_id = %d and available = 1",
                    managerId, newName, website, phoneNumber, email, address, id);
            fc.executeQuery(sqlUpdate);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getCinemaById(id);
    }

    public static JSONObject deleteCinema(Integer id) {
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

    public static boolean checkExist(Integer id) {
        for (Cinema cin : getCinemasList()) {
            if (cin.getId().equals(id))
                return true;
        }
        return false;
    }

    public static boolean checkExist(String name) {
        for (Cinema cin : getCinemasList()) {
            if (cin.getName().equals(name))
                return true;
        }
        return false;
    }

    public static boolean sizeNewNameCinema(String newName) {
        return newName.length() != 0;
    }

    public static JSONArray getAnalytics() {
        JSONArray dataList = new JSONArray();
        Function fc = new Function();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs;
        try {
            String query = "SELECT \"DATE\", \"BEGINTIME\", \"ENDTIME\", (SELECT COUNT(*) FROM purchases " +
                    "WHERE datetime > begintime and datetime <=endtime) cnt_purchases, (SELECT COUNT(*) " +
                    "FROM schedules WHERE datetime > begintime and datetime <=endtime) cnt_schedules " +
                    "FROM (SELECT  sysdate - (LEVEL - 1) \"DATE\",  round(((sysdate - (LEVEL - 1)) - (DATE '1970-01-01')))*24*60*60*1000 begintime,  " +
                    "round(((sysdate - (LEVEL-2)) - (DATE '1970-01-01')))*24*60*60*1000 endtime FROM dual CONNECT BY LEVEL < 8) A";
            rs = fc.executeQuery(query);
            while (rs.next()) {
                JSONObject tmp = new JSONObject();
                tmp.put("date", df1.parse(rs.getString(1)).toString());
                tmp.put("begintime", rs.getLong(2));
                tmp.put("endtime", rs.getLong(3));
                tmp.put("cnt_purchases", rs.getLong(4));
                tmp.put("cnt_schedules", rs.getLong(5));
                dataList.put(tmp);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return dataList;
    }
}
