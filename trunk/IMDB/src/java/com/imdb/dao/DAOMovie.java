/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.dao;

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
 * @author Valter
 */
public class DAOMovie {

    public boolean insertMovie(int id, String title, int year) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO movie  VALUES (? , ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setInt(3, year);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOMovie: insertMovie");
            System.out.println(ex.getMessage());
        }

        return status;
    }

    public int getMovieId() {
        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        int movieID = -1;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT max(id) FROM movie");

            while (rs.next()) {
                movieID = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("DAOMovie: insertMovie");
            System.out.println(ex.getMessage());
        }

        return movieID;
    }

    public List<String> getGenres() {
        String query = "SELECT * FROM genre";
        List<String> genres = new ArrayList<String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                genres.add(rs.getInt("id") + "#" + rs.getString("genre"));
            }

            if (con != null) {
                rs = null;
                db.closeConnection(con);
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return genres;
    }

    public List<String> getLanguages() {
        String query = "SELECT * FROM language";
        List<String> languages = new ArrayList<String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                languages.add(rs.getInt("id") + "#" + rs.getString("language"));
            }

            if (con != null) {
                rs = null;
                db.closeConnection(con);
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return languages;
    }

    public List<String> search(String search, int index) {
        String query = "";

        List<String> results = new ArrayList<String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;
        int i = 1;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            query = "SELECT TOP (20) id, title, year FROM movie WHERE (id NOT IN (SELECT TOP ("+index+" * 20 ) id FROM movie)) AND title LIKE '%"+search+"%' GROUP BY id, title, year";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                results.add(rs.getInt("id") + "&&" + rs.getString("title") + "&&" + rs.getInt("year"));
            }

            if (con != null) {
                rs = null;
                db.closeConnection(con);
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            System.out.println("DAOMovie:search");
            System.out.println(e.getMessage());
        }


        return results;
    }
}
