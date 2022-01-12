package org.papz06;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Function {
    Connection con;

    public static String getSecret() {
        try {
            File myObj = new File("key.txt");
            Scanner myReader = new Scanner(myObj);
            String secret;
            myReader.hasNextLine();
            secret = myReader.nextLine();
            myReader.close();
            return secret;
        } catch (FileNotFoundException e){
            System.out.println("File key is not found!");
            System.out.println(e);
        }
        return null;
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
