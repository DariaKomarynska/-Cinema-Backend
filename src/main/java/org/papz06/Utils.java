package org.papz06;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.papz06.Models.User;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String hmacSha256(String data, String secret) {
        try {

            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return encode(signedBytes);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public static String createJWTToken(User myUser, String secret) {
        JSONObject payload = new JSONObject();
        JSONObject header = new JSONObject();
        long expires = (System.currentTimeMillis()) / 1000L + 86400; // Key valid in 24h

        header.put("alg", "HS256");
        payload.put("id", myUser.getId());
        payload.put("login", myUser.getLogin());
        payload.put("firstName", myUser.getFirstName());
        payload.put("lastName", myUser.getLastName());
        payload.put("exp", expires);
        String signature = hmacSha256(Base64.getEncoder().encodeToString((header.toString()).getBytes())
                + "."
                + Base64.getEncoder().encodeToString((payload.toString()).getBytes()), secret);
        return Base64.getEncoder().encodeToString((header.toString()).getBytes())
                + "." + Base64.getEncoder().encodeToString((payload.toString()).getBytes())
                + "." + signature;
    }

    static public boolean checkValidJWT(String token, String secret) {
        if (token == null)
            return false;
        String[] parts = token.split("\\.");
        JSONObject payload = new JSONObject(decode(parts[1]));
        String signature = parts[2];
        if (payload.getLong("exp") < (System.currentTimeMillis() / 1000))
            return false;
        String headerAndPayloadHashed = Utils.hmacSha256(parts[0] + "." + parts[1], secret);
        return signature.equals(headerAndPayloadHashed);
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    public static Map<String, String> getValueFromRequest(String requestBody) {
        Map<String, String> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, String>>() {
        }.getType());
        return retMap;
    }

    public static Map<String, Object> parseRequestBody(String requestBody) {
        Map<String, Object> retMap = new Gson().fromJson(requestBody, new TypeToken<Map<String, Object>>() {
        }.getType());
        return retMap;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String addressDecoding(String address) {
        try {
            String sURL = "https://maps.googleapis.com/maps/api/geocode/json?address="
                    + URLEncoder.encode(address, StandardCharsets.UTF_8.toString())
                    + "&key=AIzaSyAOrujqH1UzTEalk1FRCZgG3O97hi0Kmf4"; // just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); // from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); // Convert the input
                                                                                                    // stream to a json
                                                                                                    // element
            JsonObject rootobj = root.getAsJsonObject(); // May be an array, may be an object.
            if (rootobj.get("status").getAsString().trim().contains("OK")) {
                return rootobj.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry")
                        .getAsJsonObject().get("location").getAsJsonObject().toString();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
