package com.company;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static Connection db;
    public static LoginForm loginForm;

    public static void main(String[] args) throws Exception {
        db=DBConnect();
        new LoginForm();
        //new Server();
        //SQLCommand("select nume from studenti");
        //DBDisconnect();
    }

    public static Connection DBConnect() {
        Connection conn = DBConnection.getConnection();
        return conn;
    }

    public static void DBDisconnect() throws SQLException {
        db.close();
    }

    public static void SQLCommand(String s) throws SQLException {
        Statement stmt=db.createStatement();
        ResultSet rs=stmt.executeQuery(s);
        while(rs.next())
        {
            System.out.println(rs.getString(1));
        }
    }
}
