package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
//import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class RoomServer {
    public static KeyValue<Integer, String> RoomList(int cinema_id) {
        /**
         * GET
         *  Returns list of rooms in the cinema.
         */
        RoomController roomControl = new RoomController();
        JSONArray result = new JSONArray();

        if (roomControl.isEmptyList()) {
            result.put(new JSONObject().put("error", "Permission denied.."));
            return new KeyValue<Integer, String>(403, result.toString());
        }
        result = roomControl.getRoomListByCinema(cinema_id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomCreate(String requestBody) {
        /**
         * POST
         * Creates new room. Request body:
         * name: string;
         * cinemaId: number;
         * seats: Array<{ positionX: number;
         * positionY: number; type: string;}>;
         */
        Map<String, Object> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, Object>>() {
        }.getType());
        String newRoomName = retMap.get("name").toString();
        Double newCinemaId = Double.parseDouble(retMap.get("cinema_id").toString()) ;
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONArray seats = jsonRequest.getJSONArray("seats");

        RoomController roomControl = new RoomController();
        JSONObject result = new JSONObject();

        if (roomControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (roomControl.checkExist(newRoomName)) {
            result.put("error", "BAD_REQUEST, already exists");
            return new KeyValue<Integer, String>(400, result.toString());
        } else if (!roomControl.sizeNewRoomName(newRoomName)) {
            result.put("error", "BAD_REQUEST, name is empty");
            return new KeyValue<Integer, String>(400, result.toString());
        }
        result = roomControl.insertNewRoom(newRoomName, newCinemaId, seats);
        return new KeyValue<Integer, String>(200, result.toString());
    }


    public static KeyValue<Integer, String> RoomDetails(int id) {
        /**
         * GET
         * Returns room details.
         */
        RoomController roomControl = new RoomController();
        JSONObject result = new JSONObject();
        if (roomControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!roomControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        // return also seats

        //
        result = roomControl.getRoomWithSeatsById(id);
        return new KeyValue<Integer, String>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomUpdate(int id, String requestBody) {
        /**
         * PATCH
         * Updates room object.
         * name: string;
         * seats: Array<{
         *       positionX: number;
         *       positionY: number;
         *       type: string;
         * }>;
         */
        Map<String, Object> retMap = Utils.parseRequestBody(requestBody);
        String newRoomName = retMap.get("name").toString();
        // use also seats

        //
        // ArrayList<> seats =  retMap.get("seats");
        RoomController roomControl = new RoomController();
        JSONObject result = new JSONObject();
        if (roomControl.checkExist(newRoomName)) {
            result.put("error", "Bad Request");
            return new KeyValue<Integer, String>(400, result.toString());
        } else if (roomControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!roomControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        result = roomControl.updateRoomNameSeats(id, newRoomName);
        return new KeyValue<Integer, String>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomDelete(int id) {
        /**
         * DELETE
         * Deletes room object.
         */
        RoomController roomControl = new RoomController();
        JSONObject result = new JSONObject();
        if (roomControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!roomControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        // delete also seats
        //
        // necessary seats
        result = roomControl.deleteRoom(id);
        return new KeyValue<Integer, String>(200, result.toString());
    }

}
