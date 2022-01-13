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

    public static JSONArray getSeatIdBySchedule(int sch_id) {
        JSONArray resData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format(
                    "select seat_id, is_free from get_seats(%d)", sch_id);
            System.out.println(sqlSelect);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject seat = new JSONObject();
                seat.put("id", rs.getInt(1));
                seat.put("isFree", rs.getInt(2) == 1);
                resData.put(seat);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resData;
    }

    public static JSONObject getSeatById(int id) {
        JSONObject seatData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats " +
                    "where seat_id = %d and available = 1", id);
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

    public static boolean checkSeats(int scheduleId, JSONArray tickets) {
        int seatId, avSeatId;
        JSONObject ticket, avSeatInfo;
        int full = 0;
        JSONArray availableSeats = getSeatIdBySchedule(scheduleId);
        System.out.println(availableSeats);
        for (int i = 0; i < availableSeats.length(); ++i) {
            avSeatInfo = availableSeats.getJSONObject(i);
            avSeatId = avSeatInfo.getInt("id");
            boolean canSeat = avSeatInfo.getBoolean("isFree");
            for (int j = 0; j < tickets.length(); ++j) {
                ticket = tickets.getJSONObject(j);
                seatId = ticket.optInt("seatId");
                if ((seatId == avSeatId) && canSeat) {
                    ++full;
                    if (full == tickets.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
