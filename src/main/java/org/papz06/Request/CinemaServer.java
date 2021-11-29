package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.papz06.Controllers.CinemaController;
import org.json.JSONObject;
import org.papz06.KeyValue;
import org.papz06.Models.Cinema;
import org.papz06.Utils;

import java.util.*;

public class CinemaServer {

    public KeyValue<Integer, String> CinemaList() {
        /** GET
         Returns list of cinemas managed by the user.
         **/
        CinemaController cinControl = new CinemaController();
        JSONArray result = new JSONArray();

        if (cinControl.isEmptyList()) {
            result.put(new JSONObject().put("error", "Permission denied.."));
            return new KeyValue<Integer, String>(403, result.toString());
        }
        result = cinControl.getCinemaData();
        return new KeyValue<>(200, result.toString());
    }


    public KeyValue<Integer, String> CinemaCreate(String requestBody) {
        /** POST
         Creates new cinema.
         **/
        System.out.println("okk");
        Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
        }.getType());
        int newManagerId =  Integer.parseInt(retMap.get("manager_id"));
        System.out.println(newManagerId);
        String newName = retMap.get("name");
        String newWebsite = retMap.get("website");
        String newPhoneNumber = retMap.get("phoneNumber");
        String newEmail = retMap.get("email");
        String newAddress = retMap.get("address");
        CinemaController cinControl = new CinemaController();
        JSONObject result = new JSONObject();
        if (cinControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (cinControl.checkExist(newName)) {
            result.put("error", "BAD_REQUEST, already exists");
            return new KeyValue<Integer, String>(400, result.toString());
        } else if (!cinControl.sizeNewNameCinema(newName)) {
            result.put("error", "BAD_REQUEST, name is empty");
            return new KeyValue<Integer, String>(400, result.toString());
        }
        result = cinControl.insertNewCinema(newManagerId, newName, newWebsite, newPhoneNumber, newEmail, newAddress);
        return new KeyValue<Integer, String>(200, result.toString());
    }

    public KeyValue<Integer, String> CinemaDetails(Integer id) {
        /** GET
         Returns cinema details.
         **/
        CinemaController cinControl = new CinemaController();
        JSONObject result = new JSONObject();
        if (cinControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!cinControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        result = cinControl.getCinemaById(id);
        return new KeyValue<Integer, String>(200, result.toString());
    }

    public KeyValue<Integer, String> CinemaUpdate(Integer id, String requestBody) {
        /**
         * PATCH
         * Update name of cinema
         */
        Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
        }.getType());
        int newManagerId =  Integer.parseInt(retMap.get("manager_id"));
        String newName = retMap.get("name");
        String newWebsite = retMap.get("website");
        String newPhoneNumber = retMap.get("phoneNumber");
        String newEmail = retMap.get("email");
        String newAddress = retMap.get("address");
        CinemaController cinControl = new CinemaController();
        JSONObject result = new JSONObject();
        if (cinControl.checkExist(newName)) {
            result.put("error", "Bad Request");
            return new KeyValue<Integer, String>(400, result.toString());
        } else if (cinControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!cinControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        result = cinControl.updateCinemaName(id, newManagerId, newName, newWebsite, newPhoneNumber, newEmail, newAddress);
        return new KeyValue<Integer, String>(200, result.toString());
    }

    public KeyValue<Integer, String> CinemaDelete(Integer id) {
        /**
         * DELETE
         * Deletes cinema object.
         */
        CinemaController cinControl = new CinemaController();
        JSONObject result = new JSONObject();
        if (cinControl.isEmptyList()) {
            result.put("error", "Permission denied");
            return new KeyValue<Integer, String>(403, result.toString());
        } else if (!cinControl.checkExist(id)) {
            result.put("error", "NOT_FOUND");
            return new KeyValue<Integer, String>(404, result.toString());
        }
        result = cinControl.deleteCinema(id);
        return new KeyValue<Integer, String>(200, result.toString());
    }
}

