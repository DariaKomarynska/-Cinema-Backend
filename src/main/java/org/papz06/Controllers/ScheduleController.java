package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.Movie;
import org.papz06.Models.Room;
import org.papz06.Models.Schedule;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// import com.google.gson.JsonObject;

import org.json.JSONArray;

public class ScheduleController {
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    public void displaySchedulesList() {
        for (Schedule sch : scheduleList) {
            System.out.println(sch.toString());
        }
    }

    public static JSONArray getScheduleList(int filmId, int roomId, Date date) {
        JSONArray scheduleList = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = "select * from schedules where available = 1 ";
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            query += "and ( datetime between " + date.getTime() + " and " + c.getTime().getTime() + ")";
            if (filmId != -1)
                query += " and movie_id = " + filmId;
            if (roomId != -1)
                query += " and room_id = " + roomId;
            rs = fc.executeQuery(query);
            while (rs.next()) {
                int id, seatLeft;
                Date datetime, openSale, closeSale;
                Movie film;
                Room room;
                id = rs.getInt(1);
                datetime = new Date(Long.parseLong(rs.getString(2)));
                film = MovieController.getMovieById(rs.getInt(3));
                room = RoomController.getRoomById(rs.getInt(4));
                openSale = new Date(Long.parseLong(rs.getString(5)));
                closeSale = new Date(Long.parseLong(rs.getString(6)));
                seatLeft = rs.getInt(7);
                Schedule sch = new Schedule(id, seatLeft, datetime, openSale, closeSale, film, room);
                scheduleList.put(sch.toJson());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return scheduleList;
    }
}
