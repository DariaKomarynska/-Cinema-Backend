package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Movie;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieController {
    public static JSONObject createMovie(Movie mv) {
        Function fc = new Function();
        ResultSet rs;
        String quote = "\'";
        try {
            String sql = MessageFormat.format(
                    "Insert into movies values (default, {0}, {6}{1}{6}, {2}, {6}{3}{6}, {6}{4}{6}, {5}, default)"
                    , mv.getLength(), mv.getAgeRestriction(), mv.getCinemaId(), mv.getName()
                    , mv.getDescription(), mv.getMovieCateId(), quote);
            fc.executeQuery(sql);
            rs = fc.executeQuery("select * from movies where available = 1 order by movie_id desc fetch next 1 rows only");
            rs.next();
            mv.setId(rs.getInt(1));
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return mv.toJson();
    }

    public static Movie getMovieById(int id) {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from movies where available = 1 and movie_id = " + id);
            rs.next();
            Movie result = new Movie(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7));
            fc.closeQuery();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        List<Movie> moviesList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from movies where available = 1");
            while (rs.next()) {
                moviesList.add(
                        new Movie(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
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
            rs = fc.executeQuery("select * from movies where available = 1 and cinema_id = " + cinema_id);
            while (rs.next()) {
                Movie mvData = new Movie(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                moviesList.put(mvData.toJson());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return moviesList;
    }
}
