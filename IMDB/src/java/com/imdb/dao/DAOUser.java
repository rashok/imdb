/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.dao;

import com.imdb.beans.User;
import com.imdb.database.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 317306
 */
public class DAOUser {

    public boolean insertUser(String email,String name, String password, int profile) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO users  VALUES (? , ? , ?, ?)");
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setInt(4, profile);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOUser: insertUser");
            System.out.println(ex.getMessage());
        }

        return status;
    }

    public User login(String email, String password) {
        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        User user = new User();
        int id;
        String name;


        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND password = "+password+"");

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setName(rs.getString("name"));
                user.setPassword(password);
                user.setProfile(rs.getInt("profile"));
            }

        } catch (SQLException ex) {
            System.out.println("DAOMovie: insertMovie");
            System.out.println(ex.getMessage());
        }

        return user;
    }

}
