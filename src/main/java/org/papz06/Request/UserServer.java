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
        JSONObject jo = new JSONObject(retMap);
        // Check if it exists in data base?
        // No!
        if (!usCon.checkExist(loginData, passAfterHash)) {
            return new Pair <Integer, String>(452, "Wrong bro!");
        }
        // Yes
        String JWTToken = new Utils().createJWTToken(new Function().getSecret());
        return new Pair <Integer, String>(200, JWTToken);
    }
}
