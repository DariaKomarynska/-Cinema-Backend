package org.papz06;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Function {
    public ResultSet executeQuery() throws SQLException {
        // Template
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println(e);
        }
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@adb.eu-frankfurt-1.oraclecloud.com:1522:gf867fe54c10b20_dbpapz06_medium.adb.oraclecloud.com", "backend", "7R2u6S@c8Bzbspf");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from movies");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        con.close();
        return rs;
    }
}
