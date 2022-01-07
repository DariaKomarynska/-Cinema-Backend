package org.papz06.Request;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import org.json.JSONArray;
import org.json.JSONObject;
// import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
// import org.papz06.Utils;

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
        result.put("error", "BAD_REQUEST, id is empty");
        return new KeyValue<Integer, String>(400, result.toString());

    }
}
