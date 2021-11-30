package org.papz06.Controllers;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Cinema;
import org.papz06.Models.Room;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomController {

    List<Room> roomsList = new ArrayList<>();


    public RoomController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from rooms where available = 1");
            while (rs.next()) {
                roomsList.add(
                        new Room(rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5)
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JSONArray getRoomListByCinema(int id) {
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select room_id, name from rooms where cinema_id = %d and available = 1", id);
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

    public JSONObject getRoomWithSeatsById(Integer id) {
        JSONObject roomData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select room_id, name from rooms where room_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                roomData.put("id", rs.getInt(1));
                roomData.put("name", rs.getString(2));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return roomData;
    }

    public JSONObject getRoomById(int id) {
        JSONObject roomData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select room_id, name, cinema_id from rooms where room_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                roomData.put("id", rs.getInt(1));
                roomData.put("name", rs.getString(2));
                roomData.put("cinema_id", rs.getInt(3));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return roomData;
    }

    public JSONObject insertNewRoom(String newRoomName, Double newCinemaId, JSONArray seats) {
        SeatController seatCon = new SeatController();
        Function fc = new Function();
        ResultSet rs;
        int roomId = 0;
        try {
            String sqlInsert = String.format("insert into rooms values (default, '%s', null, null, %.2f, default)", newRoomName, newCinemaId);
            fc.executeQuery(sqlInsert);
            rs = fc.executeQuery("select * from rooms where available = 1 order by room_id desc fetch next 1 rows only");
            rs.next();
            roomId = rs.getInt(1);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        JSONObject result = getRoomById(roomId);
        result.put("seats", seatCon.insertSeatList(roomId, seats));
        return result;
    }

    public JSONObject updateRoomNameSeats(Integer id, String newName) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format("update rooms set name = '%s' where room_id = %d and available = 1", newName, id);
            fc.executeQuery(sqlUpdate);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getRoomById(id);
    }

    public JSONObject deleteRoom(Integer id) {
        Function fc = new Function();
        try {
            String sqlDelete = String.format("update rooms set available = 0 where room_id = %d", id);
            fc.executeQuery(sqlDelete);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return new JSONObject();
    }

    public boolean isEmptyList() {
        return roomsList.size() == 0;
    }

    public String toString() {
        return roomsList.toString();
    }

    public boolean sizeNewRoomName(String newName) {
        return newName.length() != 0;
    }

    public boolean checkExist(String name) {
        for (Room rm : roomsList) {
            if (rm.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean checkExist(Integer id) {
        for (Room rm : roomsList) {
            if (rm.getId().equals(id))
                return true;
        }
        return false;
    }
}
