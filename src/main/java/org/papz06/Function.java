package org.papz06;

import org.json.JSONObject;

import java.util.List;
import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static org.papz06.Utils.decode;

public class Function {
    Connection con;
    static private String secret = "SuperScretKey123123";

    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
//        System.out.println(sql);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try{
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@194.29.167.132:1521/pdb1.ii.pw.edu.pl", "z06", "t4jzpt");
        } catch (Exception e) {System.out.println(e);}
        Statement stmt = con.createStatement();
//        String sql = "select * from " + tableName;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getEnv(String key) {
        String env = System.getenv(key);
        return env;
    }

    public void closeQuery() throws Exception {
        con.close();
    }

    static public boolean checkValidJWT(String token){
        System.out.println(token);
        String[] parts = token.split("\\.");
        JSONObject header = new JSONObject(decode(parts[0]));
        JSONObject payload = new JSONObject(decode(parts[1]));
        String signature = decode(parts[2]);
        if (payload.getLong("exp") > (System.currentTimeMillis() / 1000)) return false;
        String headerAndPayloadHashed = Utils.hmacSha256(parts[0] + "." + parts[1], secret);
//        if (signature.equals(headerAndPayloadHashed, secret)) return true;
        return false;
    }
}
