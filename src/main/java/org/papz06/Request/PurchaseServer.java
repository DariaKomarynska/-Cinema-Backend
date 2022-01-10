package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.PurchaseController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class PurchaseServer {

    public static KeyValue<Integer, String> PurchaseCreate(String requestBody) {
        /**
         * POST
         * Create new purchase. Request body:
         * scheduleId: number;
         * tickets: Array<{
         * seatId: number;
         * ticketTypeId: number;
         */
        Map<String, Object> retMap = Utils.parseRequestBody(requestBody);
        int scheduleId =  ((Double) retMap.get("scheduleId")).intValue();
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONArray tickets = jsonRequest.getJSONArray("tickets");

        JSONObject result = PurchaseController.createPurchase(scheduleId, tickets);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> PaymentAccepted(int id, String requestBody) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }
}
