package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Purchase;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PurchaseController {

    public static ArrayList<Purchase> getPurchasesList() {
        ArrayList<Purchase> purchasesList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from purchases");
            while (rs.next()) {
                purchasesList.add(
                        new Purchase(rs.getInt(1),
                                rs.getDate(2),
                                rs.getFloat(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6))
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return purchasesList;
    }

    public static JSONObject createPurchase(int scheduleId, JSONArray tickets) {

        // DATE IS NOT FINISHED

        JSONObject purchaseData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        int seatId, ticketTypeId, purchaseId, price, dateTime;
        int totalPrice = 0;

        try {
            for (int i = 0; i <  tickets.length(); ++i) {
                JSONObject ticket = tickets.getJSONObject(i);
                seatId = ticket.optInt("seatId");
                ticketTypeId = ticket.optInt("ticketTypeId");
                String seatUnavailable = String.format("update seats set available = 0 where seat_id = %d", seatId);
                fc.executeQuery(seatUnavailable);
                price = TicketTypeController.getPriceById(ticketTypeId);
                totalPrice += price;
            }
            dateTime = 0;
            String addPurchase = String.format("insert into purchases values " +
                        "(default, %d, %d, default, default, %d, default)",
                        dateTime, totalPrice, scheduleId);
            fc.executeQuery(addPurchase);

            String getPurchaseId = String.format("select * from purchases where available = 1 " +
                        "order by purchase_id desc fetch next 1 rows only");
            rs = fc.executeQuery(getPurchaseId);
            rs.next();
            purchaseId = rs.getInt(1);

            for (int i = 0; i <  tickets.length(); ++i) {
                JSONObject ticket = tickets.getJSONObject(i);
                seatId = ticket.optInt("seatId");
                ticketTypeId = ticket.optInt("ticketTypeId");
                String addTicket = String.format("insert into tickets values (default, %d, %d, %d, %d, default)",
                        purchaseId, seatId, ticketTypeId, scheduleId);
                fc.executeQuery(addTicket);
            }
            purchaseData.put("id", purchaseId);
            purchaseData.put("amount", totalPrice);
            purchaseData.put("scheduleId", scheduleId);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return purchaseData;
    }

}


