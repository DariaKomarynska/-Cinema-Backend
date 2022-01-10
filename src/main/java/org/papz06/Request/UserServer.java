package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.papz06.Controllers.UserController;
import org.papz06.Function;
import org.papz06.KeyValue;
import org.papz06.Models.User;
import org.papz06.Utils;

import java.util.HashMap;
import java.util.Map;

public class UserServer {
    public static KeyValue<Integer, String> login(String requestBody) {
        /**
         Pair to return status number and JWT token.
         * */
        Map<String, String> data = new HashMap<>();
        try {

            // Create map and use Gson to parse from string to Map
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            // Get data
            String loginData = retMap.get("login");
            String passwordData = retMap.get("password");
            String passAfterHash = Utils.MD5(passwordData);
            // Check if it exists in data base?
            // Yes
            if (UserController.checkExist(loginData, passAfterHash)) {
                String JWTToken = Utils.createJWTToken(UserController.getUserFromLogin(loginData), Function.getSecret());
                data.put("token", JWTToken);
                return new KeyValue<>(200, new JSONObject(data).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        data.put("token", "");
        return new KeyValue<>(452, new JSONObject(data).toString());
    }

    public static KeyValue<Integer, String> Registration(String requestBody) {
        Map<String, String> data = new HashMap<>();
        try{
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            String firstName = retMap.get("firstName");
            String lastName = retMap.get("lastName");
            String loginData = retMap.get("login");
            String passwordData = retMap.get("password");
            if (UserController.checkExistUser(loginData)) {
                data.put("token", "User's already existed!");
                return new KeyValue<>(453, new JSONObject(data).toString());
            }
            String passwordAfterHash = Utils.MD5(passwordData);
            UserController.registerUser(new User(firstName, lastName, loginData, passwordAfterHash));
            String JWTToken = Utils.createJWTToken(UserController.getUserFromLogin(loginData), Function.getSecret());
            data.put("token", JWTToken);
            return new KeyValue<>(200, new JSONObject(data).toString());

        } catch (Exception e){
            System.out.println(e);
        }
        return new KeyValue<>(400, new JSONObject(data).toString());
    }

    public static KeyValue<Integer, String> UserList() {
        JSONArray result = UserController.getAllUser();
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> UserDetail(int id) {
        try{
            JSONObject result = UserController.getUserFromId(id).toJson();
            return new KeyValue<>(200, result.toString());
        } catch (Exception e){
            return new KeyValue<>(404, "");
        }
    }

    public static KeyValue<Integer, String> UserCreate(String requestBody){
        JSONObject response = new JSONObject();
        try{
            Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
            }.getType());
            String firstName = retMap.get("firstName");
            String lastName = retMap.get("lastName");
            String loginData = retMap.get("login");
            String passwordData = retMap.get("password");
            if (UserController.checkExistUser(loginData)) {
                // data.put("token", "User's already existed!");
                return new KeyValue<>(400, "");
            }
            String passwordAfterHash = Utils.MD5(passwordData);
            UserController.registerUser(new User(firstName, lastName, loginData, passwordAfterHash));
            User new_us = UserController.getUserFromLogin(loginData);
            response.put("login", loginData);
            response.put("id", new_us.getId());
            return new KeyValue<>(200, response.toString());

        } catch (Exception e){
            System.out.println(e);
        }
        return new KeyValue<>(400, "");
    }
}
