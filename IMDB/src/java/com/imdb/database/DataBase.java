/**
 * @(#)DataBase.java      1.0  13/04/2011
 *
 * Copyright (c) 2011 ByteCode Team, Inc.
 * Sorocaba, São Paulo, Brazil.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ByteCode Team, Inc.
 */
package com.imdb.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
Conexão com o banco de dados
 *
 * @version 1.0
 * @author Valter
 */
public class DataBase {

    private java.sql.Connection con = null;
    private final String url = "jdbc:sqlserver://";
//    private final String serverName = "shelton.sor.ufscar.br";
    private final String serverName = "192.168.12.4";
    private final String databaseName = "labbd11";
    private final String userName = "labbd11";
    private final String password = "hvi2011";

    public DataBase() {
    }

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
