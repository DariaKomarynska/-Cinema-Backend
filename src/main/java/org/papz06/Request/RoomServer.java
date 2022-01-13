package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.CinemaController;
import org.papz06.Controllers.RoomController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class RoomServer {
    public static KeyValue<Integer, String> RoomList(int cinema_id) {
        /*
         * Authentication: JWT Token
         * Returns list of rooms in the cinema.
         */
        JSONArray result = RoomController.getRoomListByCinema(cinema_id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomCreate(String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Create new room.
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        String newRoomName = retMap.get("name");
        int rowsNumber = Integer.parseInt(retMap.get("rowsNumber"));
        int seatsInRowNumber = Integer.parseInt(retMap.get("seatsInRowNumber"));
        int newCinemaId = Integer.parseInt(retMap.get("cinemaId"));

        if (RoomController.checkExist(newRoomName, newCinemaId)) {
            return new KeyValue<>(400, "");
        } else if (RoomController.isNameEmpty(newRoomName)) {
            return new KeyValue<>(400, "");
        } else if (!CinemaController.checkExist(newCinemaId)) {
            return new KeyValue<>(400, "");
        }

        JSONObject result = RoomController.insertNewRoom(newRoomName, rowsNumber, seatsInRowNumber, newCinemaId);
        return new KeyValue<>(200, result.toString());
    }


    public static KeyValue<Integer, String> RoomDetails(int id) {
        /*
         * Authentication: JWT Token
         *
         * Returns room details.
         */

        if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = RoomController.getRoomWithSeatsById(id, true);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomUpdate(int id, String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Updates room object.
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        String roomName = retMap.get("name");
        int rowsNumber = Integer.parseInt(retMap.get("rowsNumber"));
        int seatsInRowNumber = Integer.parseInt(retMap.get("seatsInRowNumber"));
        if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        } else if (RoomController.isNameEmpty(roomName)) {
            return new KeyValue<>(400, "");
        }
        JSONObject result = RoomController.updateRoomData(id, roomName, rowsNumber, seatsInRowNumber);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> RoomDelete(int id) {
        /*
         * Authentication: JWT Token
         *
         * Deletes room object.
         */

        if (!RoomController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = RoomController.deleteRoom(id);
        return new KeyValue<>(200, result.toString());
    }

}
