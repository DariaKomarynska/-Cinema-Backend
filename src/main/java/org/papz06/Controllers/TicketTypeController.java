package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.TicketType;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketTypeController {


    public static ArrayList<TicketType> getAllTickets() {
        ArrayList<TicketType> ticketTypeList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from ticketTypes");
            while (rs.next()) {
                ticketTypeList.add(
                        new TicketType(rs.getInt(1),
                                rs.getString(2),
                                rs.getFloat(3),
                                rs.getInt(4)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ticketTypeList;
    }

    public static JSONArray getTicketTypes(int cinemaId) {
        JSONArray resultData = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            String query = String.format(
                    "select ticketType_id, name, price from ticketTypes where cinema_id = %d and available = 1", cinemaId);
            rs = fc.executeQuery(query);
            while (rs.next()) {
                JSONObject ticketTypeData = new JSONObject();
                ticketTypeData.put("id", rs.getInt(1));
                ticketTypeData.put("name", rs.getString(2));
                ticketTypeData.put("price", rs.getInt(3));
                resultData.put(ticketTypeData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return resultData;
    }

}