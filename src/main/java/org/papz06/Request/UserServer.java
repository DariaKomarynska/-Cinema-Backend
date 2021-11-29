package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.papz06.Controllers.UserController;
import org.papz06.Function;
import org.papz06.KeyValue;
import org.papz06.Utils;
import org.papz06.Models.*;

import java.util.*;

public class UserServer {
    public static KeyValue<Integer, String> login(String parametr) {
        /**
        Pair to return status number and JWT token.
        * */
        // Create map and use Gson to parse from string to Map
        Map<String, String> retMap = new Gson().fromJson(parametr, new TypeToken<Map<String, String>>() {
        }.getType());
        // Get data
        String loginData = retMap.get("login");
        String passwordData = retMap.get("password");
        String passAfterHash = Utils.MD5(passwordData);
        UserController usCon = new UserController();
        // Parse to json
        Map<String, String> data = new HashMap<String, String>();
        // Check if it exists in data base?
        // No!
        if (!usCon.checkExist(loginData, passAfterHash)) {
            data.put("token", "Wrong bro!");
            return new KeyValue <Integer, String>(452, new JSONObject(data).toString());
        }
        // Yes
        String JWTToken = Utils.createJWTToken(Function.getSecret());
        data.put("token", JWTToken);
        return new KeyValue <Integer, String>(200, new JSONObject(data).toString());
    }
    public static KeyValue<Integer, String> Registration(String requestBody) {
        Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
        }.getType());
        String firstName = retMap.get("firstName");
        String lastName = retMap.get("lastName");
        String loginData = retMap.get("login");
        String passwordData = retMap.get("password");
        Map<String, String> data = new HashMap<String, String>();
        UserController usCon = new UserController();
        if (usCon.checkExistUser(loginData)){
            data.put("token", "User's already existed!");
            return new KeyValue<>(452, new JSONObject(data).toString());
        }
        String passwordAfterHash = Utils.MD5(passwordData);
        new UserController().registerUser(new User(firstName, lastName, loginData, passwordAfterHash));
        String JWTToken = Utils.createJWTToken(Function.getSecret());
        data.put("token", JWTToken);
        return new KeyValue <>(200, new JSONObject(data).toString());
    }
}
