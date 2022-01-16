package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;

import java.sql.ResultSet;

public class TicketController {

    public static JSONArray getSeatsInfo(int purchase_id) {
        /*
        Get information about seats
         */
        JSONArray seatsList = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = String.format(
                    "select seat_id, ticketType_id from tickets where purchase_id = %d and available = 1", purchase_id);
            rs = fc.executeQuery(query);
            while (rs.next()) {
                JSONObject seatInfo = new JSONObject();
                seatInfo.put("seat_id", rs.getInt(1));
                seatInfo.put("ticketType_id", rs.getInt(2));
                seatsList.put(seatInfo);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return seatsList;
    }

    public static JSONArray getBoughtTicketsInfo(int purchaseId) {
        /*
        Get already bought tickets data - all not deleted tickets
         */
        JSONArray seatsListInfo = TicketController.getSeatsInfo(purchaseId);
        JSONArray tickets = new JSONArray();
        String ticketTypeName;
        int seat_id, ticketType_id;
        for (int i = 0; i < seatsListInfo.length(); ++i) {
            // get data for one seat
            JSONObject seatInfo = seatsListInfo.getJSONObject(i);
            seat_id = seatInfo.getInt("seat_id");
            ticketType_id = seatInfo.getInt("ticketType_id");
            JSONObject seatPos = SeatController.getSeatById(seat_id);
            ticketTypeName = TicketTypeController.getTicketTypeById(ticketType_id).getString("name");
            // put seat data to ticket
            JSONObject ticketInfo = new JSONObject();
            ticketInfo.put("seat", seatPos);
            ticketInfo.put("ticketTypeName", ticketTypeName);
            tickets.put(ticketInfo);
        }
        return tickets;
    }

    public static void deleteTicket(int purchase_id) {
        /*
        Delete ticket by setting available to 0
         */
        Function fc = new Function();
        try {
            String query = String.format(
                    "update tickets set available = 0 where purchase_id = %d", purchase_id);
            fc.executeQuery(query);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
