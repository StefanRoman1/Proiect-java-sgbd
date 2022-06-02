package com.company;

import java.sql.*;

/**
 * Clasa main a aplicatie, pe care fiecare user o deschide pentru a se conecta la platforma
 *
 * @Author Roman Stefan
 */

public class Main {
    /**
     *
     */
    public static Connection db;

    /**
     * Ruland main-ul, ne conectam la baza de date unde avem stocate informatii despre utilizatori
     * dupa care deschidem un formular de login.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        db=DBConnect();
        new LoginForm();
    }

    /**
     * Functia prin care ne conectam la baza de date.
     * @return Conexiunea propriu-zisa
     */
    public static Connection DBConnect() {
        Connection conn = DBConnection.getConnection();
        return conn;
    }

    /**
     * Functia prin care ne deconectam de la baza de date.
     * @throws SQLException
     */
    public static void DBDisconnect() throws SQLException {
        db.close();
    }
}
