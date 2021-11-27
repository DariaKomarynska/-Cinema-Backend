package org.papz06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oracle.jdbc.datasource.impl.OracleDataSource;
import oracle.jdbc.driver.OracleConnection;
import org.papz06.Function;

import java.io.Console;
import java.sql.*;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    final static String DB_URL= "jdbc:oracle:thin:@(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.eu-frankfurt-1.oraclecloud.com))(connect_data=(service_name=gf867fe54c10b20_dbpapz06_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adwc.eucom-central-1.oraclecloud.com, OU=Oracle BMCS FRANKFURT, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))?TNS_ADMIN=/Users/congminh1254/wallet_DBPAPZ06";
    // For ATP and ADW - use the TNS Alias name along with the TNS_ADMIN when using 18.3 JDBC driver
    // final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=/Users/test/wallet_dbname";
    // In case of windows, use the following URL
    // final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=C:/Users/test/wallet_dbname";
    final static String DB_USER = "backend";
    final static String DB_PASSWORD = "7R2u6S@c8Bzbspf";

    /*
     * The method gets a database connection using
     * oracle.jdbc.pool.OracleDataSource. It also sets some connection
     * level properties, such as,
     * OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH,
     * OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, etc.,
     * There are many other connection related properties. Refer to
     * the OracleConnection interface to find more.
     */
    public static void main(String args[]) throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");


        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        // With AutoCloseable, the connection is closed automatically.
        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
            // Get the JDBC driver name and version
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            // Print some connection properties
            System.out.println("Default Row Prefetch Value is: " +
                    connection.getDefaultRowPrefetch());
            System.out.println("Database Username is: " + connection.getUserName());
            System.out.println();
            // Perform a database operation
            printEmployees(connection);
        }
    }
    /*
     * Displays first_name and last_name from the employees table.
     */
    public static void printEmployees(Connection connection) throws SQLException {
        // Statement and ResultSet are AutoCloseable and closed automatically.
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement
                    .executeQuery("select first_name, last_name from employees")) {
                System.out.println("FIRST_NAME" + "  " + "LAST_NAME");
                System.out.println("---------------------");
                while (resultSet.next())
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2) + " ");
            }
        }
    }
}
