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
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat.format(date));
        } catch (Exception e){
            System.out.println(e);
        }
        // Convert data
        if (queryParams.containsKey("filmid")) {
            try {
                filmId = Integer.parseInt(queryParams.get("filmid"));
            } catch (Exception e){
                result.put("error", "BAD_REQUEST");
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        if (queryParams.containsKey("roomid")) {
            try {
                roomId = Integer.parseInt(queryParams.get("roomid"));
            } catch (Exception e){
                result.put("error", "BAD_REQUEST");
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        if (queryParams.containsKey("date")) {
            queryParams.put("date", queryParams.get("date").split("t")[0]);
            System.out.println(queryParams.get("date"));
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(queryParams.get("date"));
            } catch (ParseException e) {
                result.put("error", "BAD_REQUEST");
                return new KeyValue<Integer, String>(400, result.toString());
            }
        }
        return new KeyValue<Integer, String>(200, ScheduleController.getScheduleList(filmId, roomId, date).toString());
    }
    public static KeyValue<Integer, String> ScheduleCreate(String requestBody){
        // Create map and use Gson to parse from string to Map
        JSONObject result = null;
        try {
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            Date datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(retMap.get("datetime").replace("T", " "));
            int filmId = Integer.parseInt(retMap.get("filmId"));
            int roomId = Integer.parseInt(retMap.get("roomId"));
            Date openSale = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(retMap.get("openSale").replace("T", " "));
            Date closeSale = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(retMap.get("closeSale").replace("T", " "));
            Schedule sch = new Schedule(datetime, openSale, closeSale, filmId, roomId);
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
}
