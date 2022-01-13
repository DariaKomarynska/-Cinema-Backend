package org.papz06.Request;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import org.json.JSONArray;

import org.json.JSONObject;
// import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
// import org.papz06.Utils;
import org.papz06.Controllers.ScheduleController;
import org.papz06.Models.Schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ScheduleServer {
    public static KeyValue<Integer, String> ScheduleList(Map<String, String> queryParams) {
        /*
         * Authentication: JWT Token
         *
         * Returns list of scheduled events in the cinema, ordered by datetime, filtered
         * by sale, film, room, date.
         * When method = "GET", Request Data => Query Parameters
         */
        JSONObject result = new JSONObject();
        int filmId = -1, roomId = -1;
        // Get today date
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat.format(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        // Convert data
        if (queryParams.containsKey("filmid")) {
            try {
                filmId = Integer.parseInt(queryParams.get("filmid"));
            } catch (Exception e) {
                System.out.println(e);
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        if (queryParams.containsKey("roomid")) {
            try {
                roomId = Integer.parseInt(queryParams.get("roomid"));
            } catch (Exception e) {
                System.out.println(e);
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        if (queryParams.containsKey("date")) {

            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                date = df1.parse(queryParams.get("date").toUpperCase());
            } catch (ParseException e) {
                System.out.println(e);
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        return new KeyValue<Integer, String>(200, ScheduleController.getScheduleList(filmId, roomId, date).toString());
    }

    public static KeyValue<Integer, String> ScheduleDetails(int id) {
        /*
         * Authentication: JWT Token
         *
         * Returns scheduled event details.
         */
        JSONObject result = null;
        result = ScheduleController.getSchedule(id);
        if (result == null)
            return new KeyValue<>(404, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> ScheduleCreate(String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Creates new schedule event.
         */
        JSONObject result = null;
        try {
            // Parse data
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SX");
            Date datetime = df1.parse(retMap.get("datetime").toUpperCase());
            int filmId = Integer.parseInt(retMap.get("filmId"));
            int roomId = Integer.parseInt(retMap.get("roomId"));
            Date openSale = df1.parse(retMap.get("openSale").toUpperCase());
            Date closeSale = df1.parse(retMap.get("closeSale").toUpperCase());
            Schedule sch = new Schedule(datetime, openSale, closeSale, filmId, roomId);
            // Get result from creating
            // tmp.first: can create or not
            // tmp.second: data to return
            KeyValue<Boolean, JSONObject> tmp = ScheduleController.createSchedule(sch);
            if (tmp.getKey() == false)
                return new KeyValue<>(454, "");
            result = tmp.getValue();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> ScheduleUpdate(int id, String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Creates new schedule event.
         */
        JSONObject result = null;
        try {
            // Parse data
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SX");

            Date datetime = df1.parse(retMap.get("datetime"));
            int filmId = Integer.parseInt(retMap.get("filmId"));
            int roomId = Integer.parseInt(retMap.get("roomId"));
            Date openSale = df1.parse(retMap.get("openSale"));
            Date closeSale = df1.parse(retMap.get("closeSale"));
            Schedule sch = new Schedule(datetime, openSale, closeSale, filmId, roomId);
            // Get result from updating
            // tmp.first: can create or not
            // tmp.second: data to return
            KeyValue<Boolean, JSONObject> tmp = ScheduleController.updateSchedule(id, sch);
            if (tmp.getKey() == false)
                return new KeyValue<>(454, "");
            result = tmp.getValue();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> ScheduleDelete(int id) {
        /*
         * Authentication: JWT Token
         *
         * Deletes scheduled event.
         */
        if (ScheduleController.deleteSchedule(id))
            return new KeyValue<>(200, "");
        return new KeyValue<>(404, "");
    }
}
