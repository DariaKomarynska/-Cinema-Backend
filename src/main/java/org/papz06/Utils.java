package org.papz06;

import org.json.JSONObject;
import org.papz06.Models.User;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
//import org.apache.commons.codec.binary.Base64;


public class Utils {
    //    private static String encode(byte[] bytes) {
//        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
//    }
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

//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < signedBytes.length; ++i) {
//                sb.append(Integer.toHexString((signedBytes[i] & 0xFF) | 0x100).substring(1, 3));
//            }
//            return sb.toString();

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
        long expires = (System.currentTimeMillis()) / 1000L + 3600; // by second

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
        if (token == null) return false;
        String[] parts = token.split("\\.");
        JSONObject payload = new JSONObject(decode(parts[1]));
        String signature = parts[2];
        if (payload.getLong("exp") < (System.currentTimeMillis() / 1000)) return false;
        String headerAndPayloadHashed = Utils.hmacSha256(parts[0] + "." + parts[1], secret);
        return signature.equals(headerAndPayloadHashed);
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
