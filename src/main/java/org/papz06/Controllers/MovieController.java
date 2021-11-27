package org.papz06.Controllers;

import org.papz06.Models.*;
import org.papz06.Function;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieController {
    List<Movie> moviesList = new ArrayList<>();

    public MovieController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("movies");
            while (rs.next()) {
                moviesList.add(
                        new Movie(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayMoviesList() {
        for (Movie mv : moviesList) {
            System.out.println(mv.toString());
        }
    }
}
