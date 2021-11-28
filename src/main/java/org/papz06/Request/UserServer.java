package org.papz06.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;
import org.json.JSONObject;
import org.papz06.Controllers.UserController;
import org.papz06.Function;
import org.papz06.Utils;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;

public class UserServer {
    public Pair<Integer, String> login(String parametr) {
        /**
        Pair to return status number and JWT token.
        * */
        // Create map and use Gson to parse from string to Map
        Map<String, String> retMap = new Gson().fromJson(parametr, new TypeToken<Map<String, String>>() {
        }.getType());
        // Get data
        String loginData = retMap.get("login");
        String passwordData = retMap.get("password");
        String passAfterHash = new Utils().MD5(passwordData);
        UserController usCon = new UserController();
        // Parse to json
        Map<String, String> data = new HashMap<String, String>();
        // Check if it exists in data base?
        // No!
        if (!usCon.checkExist(loginData, passAfterHash)) {
            data.put("JWTToken", "Wrong bro!");
            return new Pair <Integer, String>(452, new JSONObject(data).toString());
        }
        // Yes
        String JWTToken = new Utils().createJWTToken(new Function().getSecret());
        data.put("JWTToken", JWTToken);
        return new Pair <Integer, String>(200, new JSONObject(data).toString());
    }
}
