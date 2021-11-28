package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.Cinema;
import org.papz06.Models.Movie;
import org.papz06.Models.MovieCategory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieCategoryController {
    List<MovieCategory> movieCategoriesList = new ArrayList<>();

    public MovieCategoryController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from moviecategories");
            while (rs.next()) {
                movieCategoriesList.add(
                        new MovieCategory(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayMovieCategoriesList() {
        for (MovieCategory mvc : movieCategoriesList) {
            System.out.println(mvc.toString());
        }
    }

}
