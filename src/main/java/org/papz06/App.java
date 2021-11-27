package org.papz06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.papz06.Function;

import java.io.Console;
import java.sql.*;

/**
 * Hello world!
 */
public class App {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public static void main(String[] args) {
//        try {
//            ResultSet rs = new Function().executeQuery();
//            System.out.println(rs);
//        }
//        catch ( SQLException e)
//        {
//            System.out.println(e);
//        }

        // Template
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.eu-frankfurt-1.oraclecloud.com))(connect_data=(service_name=gf867fe54c10b20_dbpapz06_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adwc.eucom-central-1.oraclecloud.com, OU=Oracle BMCS FRANKFURT, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))", "backend", "7R2u6S@c8Bzbspf");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from movies");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString(2));
//            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
