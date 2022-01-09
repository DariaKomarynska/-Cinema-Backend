package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class RoomServer {
    public static KeyValue<Integer, String> RoomList(int cinema_id) {
        /*
          GET
           Returns list of rooms in the cinema.
         */
        JSONArray result = RoomController.getRoomListByCinema(cinema_id);
//        if (result == null)
//            return new KeyValue<>(200, "");
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
        Double newCinemaId = Double.parseDouble(retMap.get("cinemaId").toString());
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONArray seats = jsonRequest.getJSONArray("seats");


        if (RoomController.checkExist(newRoomName, newCinemaId.intValue())) {
            return new KeyValue<>(400, "");
        } else if (!RoomController.nameNotEmpty(newRoomName)) {
            return new KeyValue<>(400, "");
        }

        JSONObject result = RoomController.insertNewRoom(newRoomName, newCinemaId, seats);
        return new KeyValue<>(200, result.toString());
    }


    public static KeyValue<Integer, String> RoomDetails(int id) {
        /**
         * GET
         * Returns room details.
         */

        if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = RoomController.getRoomWithSeatsById(id, true);
        return new KeyValue<>(200, result.toString());
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
        //      SHOULD BE CINEMA ID ALSO
        Map<String, Object> retMap = Utils.parseRequestBody(requestBody);
        String roomName = retMap.get("name").toString();
//        Double cinemaId = Double.parseDouble(retMap.get("cinemaId").toString());
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONArray seats = jsonRequest.getJSONArray("seats");
//        if (RoomController.checkExist(roomName, cinemaId)) {
//            return new KeyValue<>(400, "");
//        } else
            if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        } else if (!RoomController.nameNotEmpty(roomName)) {
                return new KeyValue<>(400, "");
        }
        JSONObject result = RoomController.updateRoomNameSeats(id, roomName, seats);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomDelete(int id) {
        /**
         * DELETE
         * Deletes room object.
         */

        if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = RoomController.deleteRoom(id);
        return new KeyValue<>(200, result.toString());
    }

}
