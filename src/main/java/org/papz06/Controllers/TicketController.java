package org.papz06.Controllers;

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
}
