package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.TicketType;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketTypeController {
    ArrayList<TicketType> ticketTypeList = new ArrayList<>();

    public TicketTypeController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("ticketTypes");
            while (rs.next()) {
                ticketTypeList.add(
                        new TicketType(rs.getInt(1),
                                rs.getInt(2),
                                rs.getFloat(3),
                                rs.getString(4)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayTicketTypeList() {
        for (TicketType tckTp : ticketTypeList) {
            System.out.println(tckTp.toString());
        }
    }
}