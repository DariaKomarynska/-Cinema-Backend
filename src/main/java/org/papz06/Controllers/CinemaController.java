package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.Cinema;
import org.papz06.Models.Movie;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CinemaController {
    List<Cinema> cinemasList = new ArrayList<>();
    public CinemaController(){
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("cinemas");
            while (rs.next()) {
                cinemasList.add(
                        new Cinema(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void displayCinemas(){
        for (Cinema ci:cinemasList)
            System.out.println(ci);
    }
}
