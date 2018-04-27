/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Backf
 */
public class Provider {

    /**
     * @return database connection, return null when fail.
     */
    public static Connection connect() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "qlnv";

        Properties info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        info.setProperty("useSSL", "false");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, info);
        } catch (ClassNotFoundException e) {
            System.out.println("Fail when try load Driver, throws " + e);
        } catch (SQLException e) {
            System.out.println("Fail when try get connection, throws " + e);
        }
        return conn;
    }
}
