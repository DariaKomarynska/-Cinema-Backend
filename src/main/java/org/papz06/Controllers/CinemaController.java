package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Cinema;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.papz06.Utils.addressDecoding;

public class CinemaController {

    public static List<Cinema> getCinemasList() {
        /*
        Get list with data of all Cinema objects
         */
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
        /*
        Get data about all cinemas
         */
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
                cinemaData.put("location", new JSONObject(addressDecoding(rs.getString(6))));
                resultData.put(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public static JSONObject getCinemaById(Integer id) {
        /*
        Get cinema data by id
         */
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
        /*
        Get cinema data by cinema name
         */
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
                System.out.println(cinemaData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return cinemaData;
    }

    public static JSONObject insertNewCinema(int managerId, String newName, String website, String phoneNumber,
                                             String email, String address) {
        /*
        Create new cinema
         */
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

    public static JSONObject updateCinemaData(Integer id, int managerId, String newName, String website,
                                              String phoneNumber, String email, String address) {
        /*
        Update data of existing cinema
         */
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
        /*
        Delete cinema from database
        set available to 0
         */
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
        /*
        Check whether cinema exists by id
         */
        for (Cinema cin : getCinemasList()) {
            if (cin.getId().equals(id))
                return true;
        }
        return false;
    }

    public static boolean checkExist(String name) {
        /*
        Check whether cinema exists by name
         */
        for (Cinema cin : getCinemasList()) {
            if (cin.getName().equals(name))
                return true;
        }
        return false;
    }

    public static boolean sizeNewNameCinema(String newName) {
        /*
        Check size of cinema name to prevent empty name
         */
        return newName.length() != 0;
    }

    public static JSONArray getAnalytics() {
        /*
        Get data for cinemas about number of purchases, link to number of schedules
        in certain time
         */
        JSONArray dataList = new JSONArray();
        Function fc = new Function();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs;
        try {
            String query = "SELECT \"DATE\", \"BEGINTIME\", \"ENDTIME\", (SELECT COUNT(*) FROM purchases " +
                    "WHERE datetime > begintime and datetime <=endtime) cnt_purchases, (SELECT COUNT(*) " +
                    "FROM schedules WHERE datetime > begintime and datetime <=endtime) cnt_schedules " +
                    "FROM (SELECT  sysdate - (LEVEL - 1) \"DATE\",  round(((sysdate - (LEVEL)) - (DATE '1970-01-01')))*24*60*60*1000 begintime,  "
                    +
                    "round(((sysdate - (LEVEL-1)) - (DATE '1970-01-01')))*24*60*60*1000 endtime FROM dual CONNECT BY LEVEL < 8) A";
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

    public static JSONObject getStatistic() {
        /*
        Get data about daily income connected to purchase and schedules
        in cinemas
         */
        JSONObject data = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = "select count(*) from movies";
            rs = fc.executeQuery(query);
            rs.next();
            data.put("total_movies", rs.getInt(1));

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat.format(date));
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);

            query = "select count(*) from schedules where datetime between " + date.getTime() + " and "
                    + c.getTime().getTime();
            System.out.println(query);
            rs = fc.executeQuery(query);
            rs.next();
            data.put("today_schedules", rs.getInt(1));
            query = "select count(*) from purchases where datetime between " + date.getTime() + " and "
                    + c.getTime().getTime();
            System.out.println(query);
            rs = fc.executeQuery(query);
            rs.next();
            data.put("today_purchases", rs.getInt(1));
            query = "select sum(amount) from purchases where datetime between " + date.getTime() + " and "
                    + c.getTime().getTime();
            System.out.println(query);
            rs = fc.executeQuery(query);
            rs.next();
            data.put("today_income", rs.getInt(1));
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return data;
    }
}
