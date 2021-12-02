package org.papz06.Controllers;

//import jdk.nashorn.internal.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Seat;

import java.sql.ResultSet;
import java.util.ArrayList;

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

    public JSONArray getSeatListByRoomId(int roomId) {
        JSONArray resData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats where room_id = %d and available = 1", roomId);
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

    private JSONArray getSeatsIdByRoomId(int roomId) {
        JSONArray resData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select seat_id from seats where room_id = %d and available = 1", roomId);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                JSONObject seat = new JSONObject();
                seat.put("seat_id", rs.getInt(1));
                resData.put(seat);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println(resData);
        return resData;
    }

    public JSONObject getSeatById(int id) {
        JSONObject seatData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select positionX, positionY, type from seats where seat_id = %d and available = 1", id);
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

    public JSONArray insertSeatList(int roomId, JSONArray seatData) {
        JSONArray resData = new JSONArray();
        for (int i = 0; i < seatData.length(); ++i) {
            JSONObject curObj = seatData.getJSONObject(i);
            int positionX = curObj.getInt("positionX");
            int positionY = curObj.getInt("positionY");
            String type = curObj.getString("type");
            JSONObject insertSeat = insertNewSeat(roomId, positionX, positionY, type);
            resData.put(insertSeat);
        }
        return resData;
    }

    public JSONObject insertNewSeat(int roomId, int positionX, int positionY, String type) {
        Function fc = new Function();
        ResultSet rs;
        int seatId = 0;
        try {
            String sqlInsert = String.format("insert into seats values (default, %d, %d, %d, '%s', default)", roomId, positionX, positionY, type);
            System.out.println("hello");
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
                JSONObject seatData = new JSONObject();
                seatData.put("id", rs.getInt(1));
                seatData.put("name", rs.getString(2));
                resultData.put(seatData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public JSONArray updateSeatListInRoom(int roomId, JSONArray seats) {
        JSONArray seatsId = getSeatsIdByRoomId(roomId);
        JSONArray result = new JSONArray();
        System.out.println(seatsId.length());
        // when 0 it does not work ((((
        if (seatsId.length() == 0) {
            return insertSeatList(roomId, seatsId);
        } else if (seatsId.length() != seats.length()) {
            return result;
        }
        for (int i = 0; i < seats.length(); ++i) {
            JSONObject curId = seatsId.getJSONObject(i);
            JSONObject curSeat = seats.getJSONObject(i);
            int positionX = curSeat.getInt("positionX");
            int positionY = curSeat.getInt("positionY");
            String type = curSeat.getString("type");
            JSONObject updated = updateSeat(curId.getInt("seat_id"), roomId, positionX, positionY, type);
            result.put(updated);
        }
        return result;
    }

    public JSONObject updateSeat(int seatId, int roomId, int positionX, int positionY, String type) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format("update seats set positionX = %d, positionY = %d, type = '%s' where seat_id = %d and room_id = %d and available = 1",
                    positionX, positionY, type, seatId, roomId);
            fc.executeQuery(sqlUpdate);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getSeatById(seatId);
    }


    public void displaySeatsList() {
        for (Seat st : seatsList) {
            System.out.println(st.toString());
        }
    }

    public void deleteSeatList(int roomId) {
        Function fc = new Function();
        try {
            String sqlDelete = String.format("update seats set available = 0 where room_id = %d", roomId);
            fc.executeQuery(sqlDelete);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
