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
public class DAOLanguage {

    public boolean insertLanguageMovie(int idLanguage, int idMovie, String info) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        boolean status = false;
        try {
            ps = c.prepareStatement("INSERT INTO language_movie  VALUES (?, ?, ?)");
            ps.setInt(1, idLanguage);
            ps.setInt(2, idMovie);
            ps.setString(3, info);
            ps.executeUpdate();

            status = true;
        } catch (SQLException ex) {
            System.out.println("DAOlanguage: insertLanguageMovie");
            System.out.println(ex.getMessage());
        }

        return status;
    }
}
