package org.papz06.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Function;
import org.papz06.Models.MovieCategory;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieCategoryController {

    public static JSONArray getListCategory(int cinema_id) {
        JSONArray categoryList = new JSONArray();
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select distinct moviecate_id from movies where available =1 and cinema_id = " + cinema_id);
            while (rs.next()) {
                ResultSet rs2 = fc.executeQuery("select * from moviecategories where available = 1 and moviecategory_id = " + rs.getInt(1));
                rs2.next();
                MovieCategory cate = new MovieCategory(rs2.getInt(1), rs2.getString(2), rs2.getString(3));
                categoryList.put(cate.toJson());
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return categoryList;
    }

    public static JSONObject createCategory(String name, String description) {
        Function fc = new Function();
        ResultSet rs;
        String quote = "\'";
        MovieCategory mc;
        try {
            String sql = MessageFormat.format(
                    "insert into moviecategories values (default, {2}{0}{2}, {2}{1}{2}, default)"
                    , name, description, quote);
            fc.executeQuery(sql);
            rs = fc.executeQuery("select * from moviecategories where available = 1" +
                    " order by moviecategory_id desc fetch next 1 rows only");
            rs.next();
            mc = new MovieCategory(rs.getInt(1), name, description);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return mc.toJson();
    }

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

    public JSONObject getMovieCategoryById(int id) {
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
