package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.KeyValue;
import org.papz06.Models.Movie;
import org.papz06.Models.Room;
import org.papz06.Models.Schedule;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScheduleController {
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    public void displaySchedulesList() {
        for (Schedule sch : scheduleList) {
            System.out.println(sch.toString());
        }
    }

    public static JSONObject getSchedule(int sch_id) {
        Schedule sch = null;
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = "select * from schedules where available = 1 and schedule_id = " + sch_id;
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
                sch = new Schedule(id, seatLeft, datetime, openSale, closeSale, film, room);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        if (sch == null)
            return null;
        return sch.toJsonDetails();
    }

    public static boolean deleteSchedule(int id) {
        Function fc = new Function();
        try {
            String sql = "select count(*) from schedules where available = 1 and schedule_id = " + id;

            ResultSet rs = fc.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == 0)
                return false;
            sql = "update schedules set available = 0  where schedule_id = " + id;
            fc.executeQuery(sql);
            fc.closeQuery();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static KeyValue<Boolean, JSONObject> createSchedule(Schedule sch) {
        Function fc = new Function();
        ResultSet rs;
        try {
            String sql = MessageFormat.format(
                    "select can_create_schedule({0}, {1}, {2}, {3}, {4}) from dual", sch.getFilmId(),
                    sch.getRoomId(), sch.getDateTime(), sch.getOpenSale(), sch.getCloseSale());
            rs = fc.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == 0)
                return new KeyValue<>(false, null);
            sql = MessageFormat.format(
                    "insert into schedules values (default, {0}, {1}, {2}, {3}, {4}, {5}, default)", sch.getDateTime(),
                    sch.getFilmId(),
                    sch.getRoomId(), sch.getOpenSale(), sch.getCloseSale(), sch.getSeatLeft());
            fc.executeQuery(sql);
            rs = fc.executeQuery("select max(schedule_id) from schedules where available = 1");
            rs.next();
            sch.setId(rs.getInt(1));
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return new KeyValue<>(true, null);
        }
        return new KeyValue<>(true, sch.toJsonShort());
    }

    public static KeyValue<Boolean, JSONObject> updateSchedule(int old_id, Schedule sch) {
        Function fc = new Function();
        ResultSet rs;
        try {
            String sql = "update schedules set available = 0 where schedule_id = " + old_id;
            sql = MessageFormat.format(
                    "select can_create_schedule({0}, {1}, {2}, {3}, {4}) from dual", sch.getFilmId(),
                    sch.getRoomId(), sch.getDateTime(), sch.getOpenSale(), sch.getCloseSale());
            rs = fc.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == 0)
                return new KeyValue<>(false, null);
            sql = MessageFormat.format(
                    "update schedules set datetime = {0}, movie_id = {1}, room_id = {2}, opensale = {3}, closesale = {4} , available = 1 where schedule_id = {5}",
                    sch.getDateTime(),
                    sch.getFilmId(),
                    sch.getRoomId(), sch.getOpenSale(), sch.getCloseSale(), old_id);
            fc.executeQuery(sql);
            sch.setId(old_id);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return new KeyValue<>(true, null);
        }
        return new KeyValue<>(true, sch.toJsonShort());
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
                scheduleList.put(sch.toJsonGeneral());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return scheduleList;
    }
}
