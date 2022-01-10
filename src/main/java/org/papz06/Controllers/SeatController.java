package org.papz06.Controllers;


import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;

import java.sql.ResultSet;

public class SeatController {

    public static JSONArray getSeatListByRoomId(int roomId) {
        JSONArray resData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format(
                    "select positionX, positionY, type from seats where room_id = %d and available = 1", roomId);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject seat = new JSONObject();
                seat.put("positionX", rs.getInt(1));
                seat.put("positionY", rs.getInt(2));
                seat.put("type", rs.getString(3));
                resData.put(seat);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resData;
    }
    
    public static JSONArray getSeatsListBySchedule(int sch_id) {
        JSONArray resData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format(
                    "select * from get_seats(%d)", sch_id);
            System.out.println(sqlSelect);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject seat = new JSONObject();
                seat.put("id", rs.getInt(1));
                seat.put("positionX", rs.getInt(2));
                seat.put("positionY", rs.getInt(3));
                seat.put("type", rs.getString(4));
                seat.put("isFree", rs.getInt(5) == 1);
                resData.put(seat);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resData;
    }

    public static JSONObject getSeatById(int id){
        JSONObject seatData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats " +
                    "where seat_id = %d and available = 0", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                seatData.put("positionX", rs.getInt(1));
                seatData.put("positionY", rs.getInt(2));
                seatData.put("type", rs.getString(3));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return seatData;
    }
}

