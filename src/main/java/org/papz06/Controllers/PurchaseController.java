package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Purchase;
import org.papz06.Models.Room;
import org.papz06.Models.Ticket;

import java.sql.ResultSet;
import java.time.Instant;
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

    public static Purchase getPurchaseById(int id) {
        Purchase purchaseData = new Purchase();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select * from purchases where purchase_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                purchaseData = new Purchase(rs.getInt(1),
                        rs.getDate(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return purchaseData;
    }

    public static JSONObject createPurchase(int scheduleId, JSONArray tickets) {
        JSONObject purchaseData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        int seatId, ticketTypeId, purchaseId, price;
        int totalPrice = 0;
        long dateTime;
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
            Instant instant = Instant.now();
            dateTime = instant.getEpochSecond();
            String addPurchase = String.format("insert into purchases values " +
                        "(default, %d, %d, default, default, %d, default)",
                        dateTime, totalPrice, scheduleId);
            fc.executeQuery(addPurchase);

            String getPurchaseId = "select * from purchases where available = 1 " +
                        "order by purchase_id desc fetch next 1 rows only";
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

    public static JSONObject acceptPayment(int purchaseId, String paymentMethod, String currency) {
        Function fc = new Function();
        JSONObject purchaseData = new JSONObject();
        try {
            String sqlUpdate = String.format(
                    "update purchases set paymentMethod = '%s', currency = '%s' where purchase_id = %d and available = 1",
                    paymentMethod, currency, purchaseId);
            fc.executeQuery(sqlUpdate);

            float amount = getPurchaseById(purchaseId).getAmount();
            int scheduleId = getPurchaseById(purchaseId).getScheduleId();
            int movie_id = (int) ScheduleController.getPurchaseInfo(scheduleId).get("movie_id");
            int room_id = (int) ScheduleController.getPurchaseInfo(scheduleId).get("room_id");

            String movieName = MovieController.getMovieById(movie_id).getName();
            String roomName = RoomController.getRoomById(room_id).getName();
            JSONArray tickets = TicketController.getBoughtTicketsInfo(purchaseId);

            purchaseData.put("id", purchaseId);
            purchaseData.put("amount", amount);
            purchaseData.put("movieName", movieName);
            purchaseData.put("roomName", roomName);
            purchaseData.put("tickets", tickets);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return purchaseData;
    }




}


