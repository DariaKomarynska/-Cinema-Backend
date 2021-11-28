package org.papz06.Controllers;

import org.papz06.Models.*;
import org.papz06.Function;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SeatController {
    ArrayList<Seat> seatsList = new ArrayList<>();

    public SeatController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("seats");
            while (rs.next()) {
                seatsList.add(
                        new Seat(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displaySeatsList() {
        for (Seat st : seatsList) {
            System.out.println(st.toString());
        }
    }
}
