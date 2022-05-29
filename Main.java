package com.company;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static Connection db;

    public static void main(String[] args) throws Exception {
        db=DBConnect();
        new LoginForm();
    }

    public static Connection DBConnect() {
        Connection conn = DBConnection.getConnection();
        return conn;
    }

    public static void DBDisconnect() throws SQLException {
        db.close();
    }
}
