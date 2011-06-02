/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.dao;

import com.imdb.database.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Valter
 */
public class DAOGenre {

    public boolean insertGenreMovie(int idGenre, int idMovie) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO genre_movie  VALUES (?,?)");
            ps.setInt(1, idGenre);
            ps.setInt(2, idMovie);
            ps.executeUpdate();
            
            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOGenre: insertGenreMovie");
            System.out.println(ex.getMessage());
        }
        
        return status;
    }
}