package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Ticket;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketController {
    ArrayList<Ticket> ticketList = new ArrayList<>();

    public TicketController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from tickets");
            while (rs.next()) {
                ticketList.add(
                        new Ticket(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayTicketList() {
        for (Ticket tck : ticketList) {
            System.out.println(tck.toString());
        }
    }

    public static JSONArray getSeatsInfo(int purchase_id) {
        JSONArray seatsList = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = String.format(
                    "select seat_id, ticketType_id from tickets where purchase_id = %d", purchase_id);
            rs = fc.executeQuery(query);
            while (rs.next()) {
                JSONObject seatInfo = new JSONObject();
                seatInfo.put("seat_id", rs.getInt(3));
                seatInfo.put("ticketType_id", rs.getInt(4));
                seatsList.put(seatInfo);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return seatsList;
    }

    public static JSONArray getBoughtTicketsInfo(int purchaseId){
        JSONArray seatsInfo = TicketController.getSeatsInfo(purchaseId);
        JSONObject seatInfo = new JSONObject();
        JSONArray tickets = new JSONArray();
        JSONObject ticketInfo = new JSONObject();
        JSONObject seatPos = new JSONObject();
        String ticketTypeName;
        int seat_id, ticketType_id;
        for (int i = 0; i < seatsInfo.length(); ++i){
            seatInfo = seatsInfo.getJSONObject(i);
            seat_id = seatInfo.getInt("seat_id");
            ticketType_id = seatInfo.getInt("ticketType_id");
            seatPos = SeatController.getSeatById(seat_id);
            ticketTypeName = TicketTypeController.getTicketTypeById(ticketType_id).getString("name");
            ticketInfo.put("seat", seatPos);
            ticketInfo.put("ticketTypeName", ticketTypeName);
            tickets.put(ticketInfo);
        }
        return tickets;
    }
}
