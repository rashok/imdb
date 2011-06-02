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
public class DAOArtist {

    public int getActorId() {
        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        int movieID = -1;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT max(id) FROM actor");

            while (rs.next()) {
                movieID = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("DAOArtist: getActorId");
            System.out.println(ex.getMessage());
        }

        return movieID;
    }

    public int getDirectorId() {
        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        int movieID = -1;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT max(id) FROM director");

            while (rs.next()) {
                movieID = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("DAOArtist: getDirectorId");
            System.out.println(ex.getMessage());
        }

        return movieID;
    }

    public boolean insertActor(int id, String name, String gender) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO actor  VALUES (? , ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, gender);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOActor: insertActor");
            System.out.println(ex.getMessage());
        }

        return status;
    }

    public boolean insertDirector(int id, String name) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO director  VALUES (? , ?)");
            ps.setInt(1, id);
            ps.setString(2, name);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOActor: insertDirector");
            System.out.println(ex.getMessage());
        }

        return status;
    }

    public boolean insertActorMovie(int idMovie, int idActor, String character) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO movie_actor VALUES (? , ?, ?)");
            ps.setInt(1, idMovie);
            ps.setInt(2, idActor);
            ps.setString(3, character);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOActor: insertActorMovie");
            System.out.println(ex.getMessage());
        }

        return status;
    }

    public boolean insertDirectorMovie(int idMovie, int idDirector, String addition) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO director_movie VALUES (? , ?, ?)");
            ps.setInt(1, idMovie);
            ps.setInt(2, idDirector);
            ps.setString(3, addition);

            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOActor: insertDirectorMovie");
            System.out.println(ex.getMessage());
        }

        return status;
    }
}
