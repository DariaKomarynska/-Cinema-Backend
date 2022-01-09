package org.papz06.Request;

import org.json.JSONArray;
import org.papz06.Controllers.RoomController;
import org.papz06.Controllers.TicketTypeController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class TicketServer {

    public static KeyValue<Integer, String> TicketTypesList(int cinema_id) {
        /**
         * GET
         * Returns list with data about ticket types in the cinema.
         */
        JSONArray result = TicketTypeController.getTicketTypes(cinema_id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> TicketTypeUpdate (int id, String requestBody) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> TicketTypeDelete (int id) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> TicketTypeCreate (String requestBody) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }
}
