package org.papz06;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < signedBytes.length; ++i) {
                sb.append(Integer.toHexString((signedBytes[i] & 0xFF) | 0x100).substring(1, 3));
            }
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
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    public static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public static String createJWTToken(String secret){
        JSONObject payload = new JSONObject();
        JSONObject header = new JSONObject();
        long expires = (System.currentTimeMillis()+10000) / 1000L;
//        try{
        header.put("alg", "HS256");
//        payload.put("sub", sub);
//        payload.put("aud", aud);
        payload.put("exp", expires);
//        } catch (Exception e) {}

//        String signature = hmacSha256(base64(header) + "." + base64(payload), secret);
//        String jwtToken = base64(header) + "." + base64(payload) + "." + signature;
        String signature = hmacSha256(Base64.getEncoder().encodeToString((header.toString()).getBytes())
                + "."
                + Base64.getEncoder().encodeToString((payload.toString()).getBytes()), secret);
        String jwtToken = Base64.getEncoder().encodeToString((header.toString()).getBytes())
                + "." + Base64.getEncoder().encodeToString((payload.toString()).getBytes())
                + "." + signature;
        return jwtToken;
    }
    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
