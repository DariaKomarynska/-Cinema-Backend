package org.papz06.Request;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import org.json.JSONArray;
import org.json.JSONObject;
// import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
// import org.papz06.Utils;
import org.papz06.Controllers.ScheduleController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ScheduleServer {
    public static KeyValue<Integer, String> ScheduleList(int cinema_id, Map<String, String> queryParams) {
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
}
