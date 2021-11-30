package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Models.*;
import org.papz06.Function;
import org.papz06.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class SeatController {
    ArrayList<Seat> seatsList = new ArrayList<>();

    public SeatController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from seats where available = 1");
            while (rs.next()) {
                seatsList.add(
                        new Seat(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getInt(6)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JSONObject getSeatById(int id) {
        JSONObject roomData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats where seat_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                roomData.put("positionX", rs.getInt(1));
                roomData.put("positionY", rs.getInt(2));
                roomData.put("type", rs.getString(3));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return roomData;
    }

    public JSONArray insertSeatList(int roomId, JSONArray seatData){
        JSONArray resData = new JSONArray();
        for (int i = 0; i < seatData.length(); ++i){
            JSONObject curObj = seatData.getJSONObject(i);
            int positionX = curObj.getInt("positionX");
            int positionY = curObj.getInt("positionY");
            String type = curObj.getString("type");
            JSONObject insertSeat = insertNewSeat(roomId, positionX, positionY, type);
            resData.put(insertSeat);
        }
        return resData;
    }

    private JSONObject insertNewSeat(int roomId, int positionX, int positionY, String type) {
        Function fc = new Function();
        ResultSet rs;
        int seatId = 0;
        try {
            String sqlInsert = String.format("insert into seats values (default, %d, %d, %d, '%s', default)", roomId, positionX, positionY, type);
            fc.executeQuery(sqlInsert);
            rs = fc.executeQuery("select * from seats where available = 1 order by seat_id desc fetch next 1 rows only");
            rs.next();
            seatId = rs.getInt(1);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getSeatById(seatId);
    }

    public JSONArray getSeatList(int id) {
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats where cinema_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject roomData = new JSONObject();
                roomData.put("id", rs.getInt(1));
                roomData.put("name", rs.getString(2));
                resultData.put(roomData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public JSONObject getRoomByNameCinema(String newName, double cinemaId) {
        Function fc = new Function();
        JSONObject roomData = new JSONObject();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select room_id, name, cinema_id from rooms where name = '%s' and cinema_id = %.2f and available = 1", newName, cinemaId);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                roomData.put("room_id", rs.getInt(1));
                roomData.put("name", rs.getString(2));
                roomData.put("cinema_id", rs.getInt(3));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return roomData;
    }

    public void displaySeatsList() {
        for (Seat st : seatsList) {
            System.out.println(st.toString());
        }
    }
}
