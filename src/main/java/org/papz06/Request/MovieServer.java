package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.MovieCategoryController;
import org.papz06.Controllers.MovieController;
import org.papz06.KeyValue;
import org.papz06.Models.Movie;
import org.papz06.Models.MovieCategory;
import org.papz06.Utils;

import java.util.Map;

public class MovieServer {
    public static KeyValue<Integer, String> MovieList(int cinema_id) {
        /*
         * Authentication: JWT Token
         *
         * Returns list of movies in the cinema.
         */
        JSONArray result = MovieController.getMovieList(cinema_id);
        if (result == null)
            return new KeyValue<>(200, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> MovieCreate(String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Creates new movie.
         */
        JSONObject result = null;
        try {
            Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
            String name = retMap.get("name");
            String description = retMap.get("description");
            int length = Integer.parseInt(retMap.get("length"));
            String ageRestriction = retMap.get("ageRestriction");
            int movieCategoryId = Integer.parseInt(retMap.get("movieCategoryId"));
            int cinemaId = Integer.parseInt(retMap.get("cinemaId"));
            Movie myMovie = new Movie(length, ageRestriction, cinemaId, name, description, movieCategoryId);
            result = MovieController.createMovie(myMovie);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> MovieDetails(int id) {
        /*
         * Authentication: JWT Token
         *
         * Returns movie details.
         */
        Movie result = MovieController.getMovieById(id);
        if (result == null)
            return new KeyValue<>(404, "");
        return new KeyValue<>(200, result.toJson().toString());
    }

    public static KeyValue<Integer, String> MovieUpdate(int id, String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Updates movie object.
         */
        JSONObject result;
        try {
            Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
            String name = retMap.get("name");
            String description = retMap.get("description");
            int length = Integer.parseInt(retMap.get("length"));
            String ageRestriction = retMap.get("ageRestriction");
            int movieCategoryId = Integer.parseInt(retMap.get("movieCategoryId"));
            Movie myMovie = new Movie(id, length, ageRestriction, -1, name, description, movieCategoryId);
            result = MovieController.updateMovie(myMovie);
        } catch (Exception e) {
            System.out.println(e);
            return new KeyValue<>(400, "");
        }
        if (result == null)
            return new KeyValue<>(404, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> MovieDelete(int id) {
        /*
         * Authentication: JWT Token
         *
         * Deletes movie object.
         */
        if (MovieController.deleteMovie(id))
            return new KeyValue<>(200, "");
        return new KeyValue<>(404, "");
    }

    public static KeyValue<Integer, String> MovieCategoryList(int cinema_id) {
        /*
         * Authentication: JWT Token
         *
         * Returns list of movie categories in the cinema.
         */
        return new KeyValue<>(200, MovieCategoryController.getListCategory(cinema_id).toString());
    }

    public static KeyValue<Integer, String> MovieCategoryCreate(String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Creates new movie category.
         */
        JSONObject result = null;
        try {
            Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
            String name = retMap.get("name");
            String description = retMap.get("description");
            result = MovieCategoryController.createCategory(name, description);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> MovieCategoryUpdate(int id, String requestBody) {
        /*
         * Authentication: JWT Token
         *
         * Updates movie category object.
         */
        JSONObject result;
        try {
            Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
            String name = retMap.get("name");
            String description = retMap.get("description");
            MovieCategory mvCate = new MovieCategory(id, name, description);
            result = MovieCategoryController.updateMovieCategory(mvCate);
        } catch (Exception e) {
            System.out.println(e);
            return new KeyValue<>(400, "");
        }
        if (result == null)
            return new KeyValue<>(404, "");
        return new KeyValue<>(200, result.toString());
    }
}
