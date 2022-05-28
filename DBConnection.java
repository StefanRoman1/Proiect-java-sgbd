package com.company;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection = null;

    private DBConnection(){}

    public static Connection getConnection() {
        Connect();
        return connection;
    }

    private static void Connect() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:hr/hr@localhost:1521/XE",
                    "Student","student");
        } catch (Exception e) {e.printStackTrace();}
    }

}
