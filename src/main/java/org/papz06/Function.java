package org.papz06;

import java.sql.*;

public class Function {
    static final private String secret = "SuperScretKey123123";
    Connection con;

    public static String getSecret() {
        return secret;
    }

    public static String getEnv(String key) {
        return System.getenv(key);
    }

    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
//        System.out.println(sql);
        // Class.forName("oracle.jdbc.driver.OracleDriver");
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@194.29.167.132:1521/pdb1.ii.pw.edu.pl", "z06", "t4jzpt");
        } catch (Exception e) {
            System.out.println(e);
        }
        Statement stmt = con.createStatement();
//        String sql = "select * from " + tableName;
        return stmt.executeQuery(sql);
    }

    public void closeQuery() throws Exception {
        con.close();
    }
}
