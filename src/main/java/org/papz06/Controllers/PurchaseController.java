package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;

import java.sql.ResultSet;
import java.time.Instant;

public class PurchaseController {

    public static JSONArray getPurchasesId() {
        /*
        Get all purchases id
         */
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select purchase_id from purchases where available = 1");
            while (rs.next()) {
                JSONObject purchaseData = new JSONObject();
                purchaseData.put("id", rs.getInt(1));
                resultData.put(purchaseData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

    public static JSONObject getPurchaseById(int id) {
        /*
        Get data of purchase by certain id
         */
        JSONObject purchaseData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select * from purchases where purchase_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                purchaseData.put("purchase_id", rs.getInt(1));
                purchaseData.put("dateTime", rs.getInt(2));
                purchaseData.put("amount", rs.getInt(3));
                purchaseData.put("paymentMethod", rs.getString(4));
                purchaseData.put("currency", rs.getString(5));
                purchaseData.put("schedule_id", rs.getInt(6));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return purchaseData;
    }

    public static JSONObject createPurchase(int scheduleId, JSONArray tickets) {
        /*
           Create new purchase
         */
        JSONObject purchaseData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        int seatId, ticketTypeId, purchaseId, price;
        int totalPrice = 0;
        long dateTime;
        try {
            // Get total price for purchase by ticket type
            for (int i = 0; i <  tickets.length(); ++i) {
                JSONObject ticket = tickets.getJSONObject(i);
                ticketTypeId = ticket.optInt("ticketTypeId");
                price = TicketTypeController.getPriceById(ticketTypeId);
                totalPrice += price;
            }
            // Adding purchase with current time
            Instant instant = Instant.now();
            dateTime = instant.toEpochMilli();
            String addPurchase = String.format("insert into purchases values " +
                            "(default, %d, %d, default, default, %d, default)",
                    dateTime, totalPrice, scheduleId);
            fc.executeQuery(addPurchase);
            // Get id of this last purchase
            String getPurchaseId = "select * from purchases where available = 1 " +
                    "order by purchase_id desc fetch next 1 rows only";
            rs = fc.executeQuery(getPurchaseId);
            rs.next();
            purchaseId = rs.getInt(1);
            // Add seats information to tickets
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
        /*
        Accept and finish payment
         */
        Function fc = new Function();
        JSONObject purchaseData = new JSONObject();
        // Set payment method and currency managed by user
        try {
            String query = String.format(
                    "update purchases set paymentMethod = '%s', currency = '%s' where purchase_id = %d and available = 1",
                    paymentMethod, currency, purchaseId);
            fc.executeQuery(query);
            // Get information of made purchase, about ticket and cinema details
            int amount = getPurchaseById(purchaseId).getInt("amount");
            int scheduleId = getPurchaseById(purchaseId).getInt("schedule_id");
            int movie_id = ScheduleController.getPurchaseInfo(scheduleId).getInt("movie_id");
            int room_id = ScheduleController.getPurchaseInfo(scheduleId).getInt("room_id");
            String movieName = MovieController.getMovieById(movie_id).getName();
            String roomName = RoomController.getRoomById(room_id).getName();
            JSONArray tickets = TicketController.getBoughtTicketsInfo(purchaseId);
            // Put got data to result
            purchaseData.put("id", purchaseId);
            purchaseData.put("amount", amount);
            purchaseData.put("movieName", movieName);
            purchaseData.put("roomName", roomName);
            purchaseData.put("tickets", tickets);
            // Decrease left seats number in certain schedule
            int seatsBusy = tickets.length();
            query = String.format("update schedules set seatleft = seatleft - %d where schedule_id = %d", seatsBusy, scheduleId);
            fc.executeQuery(query);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return null;
        }
        return purchaseData;
    }

    public static boolean checkExist(int id){
        /*
        Check if purchase exists
         */
        System.out.println(getPurchasesId());
        JSONArray purchasesId = getPurchasesId();
        JSONObject purchId = new JSONObject();
        int existId;
        for (int i = 0; i < purchasesId.length(); ++i){
            purchId = purchasesId.getJSONObject(i);
            existId = purchId.getInt("id");
            if (existId == id){
                return true;
            }
        }
        return false;
    }

    public static boolean isStringEmpty(String string) {
        // Check whether string is empty to prevent inserting empty data
        return string.length() == 0;
    }

    public static boolean checkAlreadyAccepted(int purchaseId){
        /*
        Check whether payment has been already accepted and finished
         */
        JSONObject isAccepted = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select paymentMethod, currency from purchases where purchase_id = %d and available = 1", purchaseId);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                isAccepted.put("paymentMethod", rs.getString(1) == null);
                isAccepted.put("currency", rs.getString(2) == null);
            }
            // Check if paymentMethod and currency are empty
            if (isAccepted.getBoolean("paymentMethod") && isAccepted.getBoolean("currency")){
                return false;
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return true;
    }

}


