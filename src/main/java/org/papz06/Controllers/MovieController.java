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
        /*
         * Create a new movie
         */
        Function fc = new Function();
        ResultSet rs;
        String quote = "\'";
        try {
            String sql = MessageFormat.format(
                    "Insert into movies values (default, {0}, {6}{1}{6}, {2}, {6}{3}{6}, {6}{4}{6}, {5}, default)",
                    mv.getLength(), mv.getAgeRestriction(), mv.getCinemaId(), mv.getName(), mv.getDescription(),
                    mv.getMovieCateId(), quote);
            fc.executeQuery(sql);
            rs = fc.executeQuery(
                    "select * from movies where available = 1 order by movie_id desc fetch next 1 rows only");
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
        /*
         * Get movie by id
         */
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
            return null;
        }
    }

    public static JSONArray getMovieList(int cinema_id) {
        /*
         * Get list of movies by cinema_id
         */
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

    public static JSONObject updateMovie(Movie mv) {
        /*
         * Update movie
         */
        Function fc = new Function();
        try {
            String sql = "update movies set" +
                    " length = " + mv.getLength() +
                    ", agerestriction = \'" + mv.getAgeRestriction() +
                    "\', name = \'" + mv.getName() +
                    "\', description = \'" + mv.getDescription() +
                    "\', moviecate_id = " + mv.getMovieCateId() +
                    " where available = 1 and movie_id = " + mv.getId();
            fc.executeQuery(sql);
            fc.closeQuery();
            return mv.toJson();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean deleteMovie(int id) {
        /*
         * Delete movie
         * First check if movie exists?
         * then set available to 0
         */
        Function fc = new Function();
        try {
            String sql = "select count(*) from movies where available =1 and movie_id = " + id;
            ResultSet rs = fc.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == 0)
                return false;
            sql = "update movies set available = 0  where movie_id = " + id;
            fc.executeQuery(sql);
            fc.closeQuery();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Movie> getAllMovies() {
        /*
         * Get all movies
         */
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
                                rs.getInt(7)));
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return moviesList;
    }
}
