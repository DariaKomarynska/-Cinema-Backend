package org.papz06;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Function {
    Connection con;
    static private String secret = "SuperScretKey123123";
    public ResultSet executeQuery(String tableName) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con=DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle-59559-0.cloudclusters.net:11002/XE","admin","7R2u6S@c8Bzbspf");
        Statement stmt = con.createStatement();
        String sql = "select * from " + tableName;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    public static String getSecret(){
        return secret;
    }

    public static String getEnv(String key) {
        String env = System.getenv(key);
        return env;
    }

    public void closeQuery() throws Exception{
        con.close();
    }
}
