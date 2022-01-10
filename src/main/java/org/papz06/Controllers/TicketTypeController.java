package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Room;
import org.papz06.Models.TicketType;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketTypeController {


    public static ArrayList<TicketType> getAllTicketTypeList() {
        ArrayList<TicketType> ticketTypeList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from ticketTypes");
            while (rs.next()) {
                ticketTypeList.add(
                        new TicketType(rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
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
                    "select ticketType_id, name, price from ticketTypes where cinema_id = %d and available = 1",
                    cinemaId);
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

    public static JSONObject getTicketTypeById(int id) {
        JSONObject tckTypeData = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            String sqlSelect = String.format("select ticketType_id, name, price from ticketTypes " +
                    "where ticketType_id = %d and available = 1", id);
            rs = fc.executeQuery(sqlSelect);
            while (rs.next()) {
                tckTypeData.put("id", rs.getInt(1));
                tckTypeData.put("name", rs.getString(2));
                tckTypeData.put("price", rs.getInt(3));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return tckTypeData;
    }

    public static JSONObject insertNewTicketType(String name, int price, int cinemaId) {
        Function fc = new Function();
        ResultSet rs;
        int tckTypeID = 0;
        try {
            String sqlInsert = String.format("insert into TicketTypes values (default, '%s', %d, %d, default)",
                    name, price, cinemaId);
            fc.executeQuery(sqlInsert);
            rs = fc.executeQuery("select * from TicketTypes where available = 1 order by ticketType_id desc fetch next 1 rows only");
            rs.next();
            tckTypeID = rs.getInt(1);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getTicketTypeById(tckTypeID);
    }

    public static JSONObject updateTicketType(int id, String name, int price) {
        Function fc = new Function();
        try {
            String sqlUpdate = String.format(
                    "update TicketTypes set name = '%s', price = %d where ticketType_id = %d and available = 1",
                    name, price, id);
            fc.executeQuery(sqlUpdate);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return getTicketTypeById(id);
    }

    public static JSONObject deleteTicketType(int id) {
        Function fc = new Function();
        try {
            String sqlDelete = String.format("update TicketTypes set available = 0 where ticketType_id = %d", id);
            fc.executeQuery(sqlDelete);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return new JSONObject();
    }

    public static boolean checkExist(String name, int cinemaId) {
        for (TicketType tckType : getAllTicketTypeList()) {
            if (tckType.getCinemaId().equals(cinemaId))
                if (tckType.getName().equals(name))
                    return true;
        }
        return false;
    }

    public static boolean notExist(int id){
        for (TicketType tckType : getAllTicketTypeList()) {
            if (tckType.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNameEmpty(String newName) {
        return newName.length() == 0;
    }

    public static boolean isPriceNegative(int price) {
        return price < 0;
    }

}