package org.papz06.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.CinemaController;
import org.papz06.KeyValue;
import org.papz06.Utils;

import java.util.Map;

public class CinemaServer {

    public static KeyValue<Integer, String> CinemaList() {
        /* GET
         Return list of cinemas managed by the user
         */
        JSONArray result = CinemaController.getCinemaData();
        return new KeyValue<>(200, result.toString());
    }


    public static KeyValue<Integer, String> CinemaCreate(String requestBody) {
        /*POST
         Create new cinema
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        int newManagerId = Integer.parseInt(retMap.get("manager_id"));
        System.out.println(newManagerId);
        String newName = retMap.get("name");
        String newWebsite = retMap.get("website");
        String newPhoneNumber = retMap.get("phoneNumber");
        String newEmail = retMap.get("email");
        String newAddress = retMap.get("address");

        if (CinemaController.checkExist(newName)) {
            return new KeyValue<>(400, "");
        } else if (!CinemaController.sizeNewNameCinema(newName)) {
            return new KeyValue<>(400, "");
        }
        JSONObject result = CinemaController.insertNewCinema(newManagerId, newName, newWebsite, newPhoneNumber, newEmail, newAddress);
        return new KeyValue<>(200, result.toString());
    }


    public static KeyValue<Integer, String> CinemaDetails(Integer id) {
        /*
         * GET
         * Return cinema details
         */
        if (!CinemaController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = CinemaController.getCinemaById(id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> CinemaUpdate(Integer id, String requestBody) {
        /*
         * PATCH
         * Update data about cinema
         */
        Map<String, String> retMap = Utils.getValueFromRequest(requestBody);
        int newManagerId = Integer.parseInt(retMap.get("manager_id"));
        String newName = retMap.get("name");
        String newWebsite = retMap.get("website");
        String newPhoneNumber = retMap.get("phoneNumber");
        String newEmail = retMap.get("email");
        String newAddress = retMap.get("address");

        if (CinemaController.checkExist(newName)) {
            return new KeyValue<>(400, "");
        } else if (!CinemaController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = CinemaController.updateCinemaData(id, newManagerId, newName, newWebsite, newPhoneNumber, newEmail, newAddress);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> CinemaDelete(Integer id) {
        /*
         * DELETE
         * Deletes cinema object.
         */
        if (!CinemaController.checkExist(id)) {
            return new KeyValue<>(404, "");
        }
        JSONObject result = CinemaController.deleteCinema(id);
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> AnalyticsDetail(){
        /*
        Get analytic data in cinemas
         */
        JSONArray result = null;
        result = CinemaController.getAnalytics();
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> StatisticDetail(){
        /*
        Get statistic of income
         */
        JSONObject result = null;
        result = CinemaController.getStatistic();
        if (result == null)
            return new KeyValue<>(400, "");
        return new KeyValue<>(200, result.toString());
    }
}

