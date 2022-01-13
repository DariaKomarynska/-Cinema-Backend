package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.PurchaseController;
import org.papz06.Controllers.ScheduleController;
import org.papz06.Controllers.SeatController;
import org.papz06.Controllers.TicketController;
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
        int scheduleId = ((Double) retMap.get("scheduleId")).intValue();
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONArray tickets = jsonRequest.getJSONArray("tickets");

        if (!SeatController.checkSeats(scheduleId, tickets)) {
            return new KeyValue<>(455, "");
        }
        if (!ScheduleController.checkExist(scheduleId)) {
            return new KeyValue<>(400, "");
        }
        if (tickets == null) {
            return new KeyValue<>(400, "");
        }
        if (tickets.length() == 0) {
            return new KeyValue<>(400, "");
        }
        JSONObject result = PurchaseController.createPurchase(scheduleId, tickets);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> PaymentAccepted(int id, String requestBody) {
        /**
         * PATCH
         * Accept purchase. Request body:
         * paymentMethod: String;
         * currency: String
         *
         * Result:
         * id: number;
         * amount: number;
         * movieName: string;
         * roomName: string;
         * tickets: Array<{
         *      seat: {positionX: number;
         *             positionY: number;
         *             type: string;};
         *      ticketTypeName: string;}>;
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        String paymentMethod = retMap.get("paymentMethod");
        String currency = retMap.get("currency");
        if (!PurchaseController.checkExist(id)) {
            return new KeyValue<>(400, "");
        }
        if (PurchaseController.isStringEmpty(paymentMethod)) {
            return new KeyValue<>(400, "");
        }
        if (PurchaseController.isStringEmpty(currency)) {
            return new KeyValue<>(400, "");
        }
        if (PurchaseController.checkAlreadyAccepted(id)) {
            return new KeyValue<>(400, "");
        }
        JSONObject result = PurchaseController.acceptPayment(id, paymentMethod, currency);
        if (result == null) {
            TicketController.deleteTicket(id);
            return new KeyValue<>(400, "");
        }
        return new KeyValue<>(200, result.toString());
    }
}
