package org.papz06.Request;

import org.json.JSONArray;
import org.papz06.KeyValue;

public class MovieServer {
    public static KeyValue<Integer, String> MovieList(int cinema_id) {
        JSONArray result = new JSONArray();

        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> MovieCreate(String requestBody) {
        return null;
    }

    public static KeyValue<Integer, String> MovieDetails(int id, String requestBody) {
        return null;
    }

    public static KeyValue<Integer, String> MovieUpdate(int id, String requestBody) {
        return null;
    }

    public static KeyValue<Integer, String> MovieDelete(int id) {
        return null;
    }

    public static KeyValue<Integer, String> MovieCategoryList(int cinema_id) {
        return null;
    }

    public static KeyValue<Integer, String> MovieCategoryCreate(String requestBody) {
        return null;
    }

    public static KeyValue<Integer, String> MovieCategoryUpdate(int id, String requestBody){
        return null;
    }
}
