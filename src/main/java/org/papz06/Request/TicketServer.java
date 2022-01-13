package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.CinemaController;
import org.papz06.Controllers.TicketTypeController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class TicketServer {

    public static KeyValue<Integer, String> TicketTypesList(int cinema_id) {
        /*
         * Authentication: JWT Token
         *
         * Returns list with data about ticket types in the cinema.
         */
        JSONArray result = TicketTypeController.getTicketTypes(cinema_id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> TicketTypeCreate(String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Creates new ticket type.
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        String name = retMap.get("name");
        int price = Integer.parseInt(retMap.get("price"));
        int cinemaId = Integer.parseInt(retMap.get("cinemaId"));

        if (TicketTypeController.checkExist(name, cinemaId))
            return new KeyValue<>(400, "");
        if (!CinemaController.checkExist(cinemaId))
            return new KeyValue<>(400, "");
        if (TicketTypeController.isNameEmpty(name))
            return new KeyValue<>(400, "");
        if (TicketTypeController.isPriceNegative(price))
            return new KeyValue<>(400, "");
        JSONObject result = TicketTypeController.insertNewTicketType(name, price, cinemaId);
        return new KeyValue<>(200, result.toString());
    }


    public static KeyValue<Integer, String> TicketTypeUpdate(int id, String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Updates ticket type object.
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        String name = retMap.get("name");
        int price = Integer.parseInt(retMap.get("price"));

        if (TicketTypeController.notExist(id)) {
            return new KeyValue<>(404, "");
        }
        if (TicketTypeController.isNameEmpty(name)) {
            return new KeyValue<>(400, "");
        }
        if (TicketTypeController.isPriceNegative(price)) {
            return new KeyValue<>(400, "");
        }
        JSONObject result = TicketTypeController.updateTicketType(id, name, price);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> TicketTypeDelete(int id) {
        /*
         * Authentication: JWT Token
         *
         * Deletes ticket type.
         */
        if (TicketTypeController.notExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = TicketTypeController.deleteTicketType(id);
        return new KeyValue<>(200, result.toString());
    }

}
