/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.database;

import java.sql.Connection;

/**
 *
 * @author Valter
 */
public class Administrador {

    private java.sql.Connection con = null;
    private final String url = "jdbc:sqlserver://";
    private String serverName = "shelton.sor.ufscar.br";
    private final String databaseName = "labbd11";
    private final String userName = "labbd11";
    private final String password = "hvi2011";

    private String getConnectionUrl() {
        return url + serverName + ";databaseName=" + databaseName;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
            serverName = "192.168.12.4";

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
                if (con != null) {
                    System.out.println("Connection Successful!");
                }
            } catch (Exception ex) {
                e.printStackTrace();
                System.out.println("Error Trace in getConnection() : " + ex.getMessage());
            }
        }
        return con;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
