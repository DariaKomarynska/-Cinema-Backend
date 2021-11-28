package org.papz06;

//import com.sun.tools.javac.util.List;
import java.util.List;
import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Function {
    Connection con;
    static private String secret = "SuperScretKey123123";

    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(
                "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "z06", "t4jzpt");
        Statement stmt = con.createStatement();
//        String sql = "select * from " + tableName;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    public ResultSet insertQuery(String tableName, List<Object> newData) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(
                "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "z06", "t4jzpt");
        Statement stmt = con.createStatement();
        String sql = "insert into " + tableName + "values (";
        for (Object newElement : newData){
            sql += String.valueOf(newElement);
            if (newData.indexOf(newElement) == (newData.size() -1)){
                sql += ")";
            }
            else{
                sql += ", ";
            }
        }
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
}
