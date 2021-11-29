package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Movie;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieController {

    public List<Movie> getAllMovies() {
        List<Movie> moviesList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from movies");
            while (rs.next()) {
                moviesList.add(
                        new Movie(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return moviesList;
    }

    public JSONArray getMovieList(int cinema_id) {
        JSONArray moviesList = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from movies where cinema_id = " + cinema_id);
            while (rs.next()) {
                JSONObject movieData = new JSONObject();
                movieData.put("id", rs.getInt(1));
                movieData.put("length", rs.getInt(2));
                movieData.put("ageRestriction", rs.getInt(3));
                movieData.put("name", rs.getString(5));
                movieData.put("description", rs.getString(6));
                movieData.put("movieCategory", new MovieCategoryController().getMovieCategoryById(rs.getInt(7)));
                moviesList.put(movieData);
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return moviesList;
    }
}
