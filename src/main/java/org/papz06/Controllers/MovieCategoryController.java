package org.papz06.Controllers;

import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.Cinema;
import org.papz06.Models.Movie;
import org.papz06.Models.MovieCategory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieCategoryController {

    public List<MovieCategory> getAllCategories() {
        List<MovieCategory> movieCategoriesList = new ArrayList<>();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from moviecategories");
            while (rs.next()) {
                movieCategoriesList.add(
                        new MovieCategory(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return movieCategoriesList;
    }
    public JSONObject getMovieCategoryById(int id){
        JSONObject result = new JSONObject();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from moviecategories where moviecategory_id = " + id);
            rs.next();
            result.put("id", rs.getInt(1));
            result.put("name", rs.getString(2));
            result.put("description", rs.getString(3));

            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
