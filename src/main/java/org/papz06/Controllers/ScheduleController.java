package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.KeyValue;
import org.papz06.Models.Movie;
import org.papz06.Models.Room;
import org.papz06.Models.Schedule;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScheduleController {
    public static JSONObject getSchedule(int sch_id) {
        /*
         * Get all a schedule from schedule_id;
         */
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
        /*
         * Delete schedule from database
         * First check if it exists?
         * Then set available = 0
         */
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
        /*
         * Create a schdeule
         * First check if it confict?
         * Then insert into database
         * Add id to schedule and return
         */
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
        /*
         * Update schedule
         * First check if it has conflict?
         * then update
         */
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
        /*
         * Get schedules list
         * Get schedule with requirement
         * 
         */
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

    public static JSONObject getPurchaseInfo(int id) {
        /*
         * Get purchase info
         */
        JSONObject resData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = String.format(
                    "select movie_id, room_id from schedules where schedule_id = %d", id);
            rs = fc.executeQuery(query);

            while (rs.next()) {
                resData.put("movie_id", rs.getInt(1));
                resData.put("room_id", rs.getInt(2));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resData;
    }

    public static boolean checkExist(int id) {
        /*
         * Check if a schedule exist?
         */
        Function fc = new Function();
        ResultSet rs;
        int schedule_id = 0;
        try {
            String query = String.format(
                    "select schedule_id from schedules where schedule_id = %d", id);
            rs = fc.executeQuery(query);
            rs.next();
            schedule_id = rs.getInt(1);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        if (schedule_id == 0)
            return false;
        return true;

    }

}
