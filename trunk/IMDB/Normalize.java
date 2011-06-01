/**
 * @(#)normalize.java      <version>  13/04/2011
 *
 * Copyright (c) 2011 ByteCode Team, Inc.
 * Sorocaba, São Paulo, Brazil.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ByteCode Team, Inc.
 */
package com.imdb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
Class description goes here
 *
 * @version <version>
 * @author Valter
 */
public class Normalize {

    public static void main(String args[]) {

        System.out.println("START");
        Normalize normalize = new Normalize();

        /* Normalizando a tabela genres */

//        List<String> genres = normalize.normalizeGenre();
//        normalize.setGenre(genres);

        /* Normailizando a tabela language  */

//        List<String> languages = normalize.normalizeLanguages();
//        List<String> newLanguages = new ArrayList<String>();
//
//        List<String> infos = normalize.getInfoLanguage(languages);

//        for (String info :infos){
//            System.out.println("info:"+info);
//        }


//        for (String language : languages) {
//            if (!newLanguages.contains(language) && language.length() > 1) {
//                System.out.println(language);
//                newLanguages.add(language);
//            }
//        }

        //normalize.setLanguage(newLanguages);


        /* Normalizando movie e language */
//        List<String> moviesLanguages = normalize.normalizeMovieLanguages();
//        normalize.setMovieLanguage(moviesLanguages);

        List<String> moviesGenres = normalize.getMovieAndGenre();
        List<String> normalizeGenres = normalize.normalizeGenres(moviesGenres);
        for (String resultado : normalizeGenres) {
            System.out.println(resultado);
        }

        System.out.println("FINISHED");
    }

    public List<String> normalizeGenre() {
        Normalize normalize = new Normalize();
        List<String> genres = normalize.getGenres();
        List<String> newGenres = new ArrayList<String>();

        int i = 1;
        int size = genres.size();

        String charSequence = " ";
        String genre;
        String genreAux;

        for (int j = 0; j < size; j++) {
            genre = genres.get(j);
            System.out.println(genre);

            while (i > 0) {
                i = genre.indexOf(';');

                if (i != -1) {
                    genreAux = genre.substring(0, i);

                    while (genreAux.contains(charSequence)) {
                        genreAux = genreAux.replace(" ", "");
                    }

                    if (!newGenres.contains(genreAux)) {
                        newGenres.add(genreAux);
                    }

                    genre = genre.replace(genreAux, "");
                    genre = genre.replaceFirst(";", "");
                    genre = genre.trim();

                }
            }
            i = 1;
        }

        return newGenres;
    }

    public List<String> getGenres() {

        String query = "select genres from disciplinabd..movies where genres is not null";
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
                genres.add(rs.getString("genres"));
            }

            query = "select genres from disciplinabd..moremovies where genres is not null";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                genres.add(rs.getString("genres"));
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

    /**
     * Set new genres normalized in the table 'genre' in labbd11
     * @param genres 
     */
    public void setGenre(List<String> genres) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        for (String genre : genres) {
            try {
                ps = c.prepareStatement("INSERT INTO genre (genre) VALUES  (?)");
                ps.setString(1, genre);

            } catch (SQLException se) {
                System.out.println("Uma exceção durante o preparo do Statement: provavelmente erro no SQL.");
                se.printStackTrace();
                System.exit(1);
            }
            try {
                ps.executeUpdate();
            } catch (SQLException se) {
                System.out.println("Uma exceção durante a atualização: provavelmente erro no SQL ou problema na conexão.");
                se.printStackTrace();
                System.exit(1);
            }
        }
        db.closeConnection(c);
    }

    public List<String> getLanguages() {
        String query = "SELECT DISTINCT TOP 1000 languages FROM disciplinabd..movies WHERE languages IS NOT NULL";
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
                languages.add(rs.getString("languages"));
            }

            query = "SELECT DISTINCT TOP 1000 languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                languages.add(rs.getString("languages"));
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

    public List<String> getIdAndLanguagesNormalized() {
        String query = "SELECT idLanguage, language FROM language GROUP BY idLanguage, language";
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
                languages.add(rs.getString("language") + " [" + rs.getInt("idLanguage") + "]");
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

    public Map<Integer, String> getMovieAndLanguage() {
        String query = "SELECT movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages";

        Map<Integer, String> movieLanguage = new HashMap<Integer, String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                movieLanguage.put(Integer.parseInt(rs.getString("movieid")), rs.getString("languages"));
            }

            query = "SELECT movieid,languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                movieLanguage.put(Integer.parseInt(rs.getString("movieid")), rs.getString("languages"));
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

        return movieLanguage;
    }

    public List<String> getMovieAndGenre() {
        String query = "SELECT top 100 movieid, genres FROM disciplinabd..movies WHERE genres is not null and movieid <> '11111111111111' group by movieid, genres";

        List<String> list = new ArrayList<String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                list.add("[" + rs.getString("movieid") + "][" + rs.getString("genres") + "]");
            }

            query = "SELECT top 100 movieid, genres FROM disciplinabd..moremovies WHERE genres is not null and movieid <> '11111111111111' group by movieid, genres";


            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add("[" + rs.getString("movieid") + "][" + rs.getString("genres") + "]");
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

        return list;
    }

    public List<String> normalizeMovieLanguages() {
        Normalize normalize = new Normalize();
        Map<Integer, String> movieLanguage = normalize.getMovieAndLanguage();
        List<String> languages = normalize.getIdAndLanguagesNormalized();

        List<String> normalized = new ArrayList<String>();

        Set set = movieLanguage.entrySet();
        Iterator iterator = set.iterator();
        Map.Entry me = null;

        int idMovie = -1;
        String format = "";

        String idLanguage = "";
        String language = "";
        String info = "";

        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            idMovie = (Integer) me.getKey();
            language = (String) me.getValue();

            //English; French; German
            if (language.contains(";")) {
                String[] split = language.split(";");

                for (int a = 0; a < split.length; a++) {
                    format = formatMovieLanguage(idMovie, split[a], languages);

//                    if (!normalized.contains(format)) {
                    normalized.add(format);
//                    }
                }

            } else {
                format = formatMovieLanguage(idMovie, language, languages);

//                if (!normalized.contains(format)) {
                normalized.add(format);
//                }
            }

        }
        return normalized;

    }

    public String formatMovieLanguage(int idMovie, String language, List<String> languages) {
        String format = "";

//        System.out.println(language);
        if (language.length() > 0) {

            if (language.contains("- (") && language.contains(")")) {
                String info = formatInfo(language);

                language = language.replace("- (", "");
                language = language.replace(info, "");
                language = language.replace(")", "");

                if (language.charAt(0) == ' ') {
                    language = language.substring(1, language.length());
                }

                if (language.contains(" ") && language.charAt(language.length() - 1) == ' ') {
                    language = language.substring(0, language.length() - 1);
                }

                for (String lang : languages) {

                    if (lang.contains(language)) {

                        String idLanguage = lang.substring(lang.indexOf("[") + 1, lang.indexOf("]"));

                        lang = lang.replace("[", "");
                        lang = lang.replace(idLanguage, "");
                        lang = lang.replace("]", "");

                        while (lang.contains(" ") && lang.charAt(lang.length() - 1) == ' ') {
                            lang = lang.substring(0, lang.length() - 1);
                        }

                        if (lang.equals(language)) {
                            format = "[" + idLanguage + "][" + idMovie + "][" + info + "]";

                        }
                    }
                }
            } else {
                if (language.charAt(0) == ' ') {
                    language = language.substring(1, language.length());
                }

                if (language.contains(" ") && language.charAt(language.length() - 1) == ' ') {
                    language = language.substring(0, language.length() - 1);
                }

                for (String lang : languages) {
                    if (lang.contains(language)) {

                        String idLanguage = lang.substring(lang.indexOf("[") + 1, lang.indexOf("]"));

                        lang = lang.replace("[", "");
                        lang = lang.replace(idLanguage, "");
                        lang = lang.replace("]", "");

                        while (lang.charAt(lang.length() - 1) == ' ') {
                            lang = lang.substring(0, lang.length() - 1);
                        }

                        if (lang.equals(language)) {
                            format = "[" + idLanguage + "][" + idMovie + "][]";
                        }
                    }
                }
            }
        }
        if (format.length() < 1) {
            System.out.println("********************************");
            System.out.println("language = " + language);
            System.out.println("********************************");
        }


        return format;
    }

    public String formatInfo(String language) {

        //English - (Original version)
        //Czech - (with English subtitles); Slovak - (with English subtitles)

        int left = language.indexOf("- (") + 3;
        int right = language.indexOf(")");
        String info = "";

        // Greek, Ancient (to 1453) - (some dialogue)/ left:29 / right:24
        if (right < left) {
            info = language.substring(left, language.length() - 1);
        } else {
            info = language.substring(left, right);
        }

        return info;
    }

    public List<String> normalizeLanguages() {
        Normalize normalize = new Normalize();
        List<String> languages = normalize.getLanguages();
        List<String> newLanguages = new ArrayList<String>();

        int i = 1;

        int size = languages.size();

        String language = "";
        String languageAux;

        for (int j = 0; j < size; j++) {
            language = languages.get(j);

            i = language.indexOf(';');

            while (i != -1) {
                languageAux = language.substring(0, i);

                if (!newLanguages.contains(languageAux)) {
                    newLanguages.add(languageAux);
                }

                language = language.replace(languageAux, "");
                language = language.replaceFirst(";", "");
                language = language.replaceFirst(" ", "");

                i = language.indexOf(';');
            }


            if (i == -1) {
                if (!newLanguages.contains(language)) {
                    newLanguages.add(language);
                }
            }

        }

        return newLanguages;
    }

    public List<String> getInfoLanguage(List<String> languages) {
        List<String> infos = new ArrayList<String>();
        String language = "";
        String info = "";
        int size = languages.size();
        int index = -1;

        for (int i = 0; i < size; i++) {
            language = languages.get(i);
            index = language.indexOf('-');

            if (index != -1) {
                info = language.substring(index + 1, language.length());

                language = language.replace(info, "");

                language = language.replace("-", "");
                languages.set(i, language);

                if (!infos.contains(info)) {
                    infos.add(info);
                }
            }

            index = language.indexOf('(');

            if (index != -1) {
                info = language.substring(index + 1, language.indexOf(")"));

                language = language.replace("(", "");
                language = language.replace(")", "");

                language = language.replace(info, "");

                languages.set(i, language);

                if (!infos.contains(info)) {
                    infos.add(info);
                }
            }



        }

        return infos;
    }

    public void setLanguage(List<String> languages) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        for (String language : languages) {
            try {
                ps = c.prepareStatement("INSERT INTO language (language) VALUES  (?)");
                ps.setString(1, language);

            } catch (SQLException se) {
                System.out.println("Uma exceção durante o preparo do Statement: provavelmente erro no SQL.");
                se.printStackTrace();
                System.exit(1);
            }
            try {
                ps.executeUpdate();
            } catch (SQLException se) {
                System.out.println("Uma exceção durante a atualização: provavelmente erro no SQL ou problema na conexão.");
                se.printStackTrace();
                System.exit(1);
            }
        }
        db.closeConnection(c);
    }

    public void setMovieLanguage(List<String> moviesLanguages) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        int idMovie = -1;
        int idLanguage = -1;
        String info = "";

        for (String movieLanguage : moviesLanguages) {
            if (movieLanguage.length() > 0) {

                try {
//                [82924][25613419][]
//                [82925][25613419][Original version]

                    idLanguage = Integer.parseInt(movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]")));
                    movieLanguage = movieLanguage.replace("[" + idLanguage + "]", "");

//                    System.out.println("idLanguage.length() = " + idLanguage);

                    idMovie = Integer.parseInt(movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]")));
                    movieLanguage = movieLanguage.replace("[" + idMovie + "]", "");

//                    System.out.println("idMovie = " + idMovie);

                    info = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));

//                    System.out.println("info = " + info);

                    movieLanguage = movieLanguage.replace("[" + info + "]", "");

//                    System.out.println("movieLanguage = " + movieLanguage.length());

                    ps = c.prepareStatement("INSERT INTO language_movie_2 (idLanguage, idMovie, infAdd) VALUES (?, ?, ?)");
                    ps.setInt(1, idLanguage);
                    ps.setInt(2, idMovie);
                    ps.setString(3, info);

                } catch (SQLException se) {
//                    System.out.println("Uma exceção durante o preparo do Statement: provavelmente erro no SQL.");
//                    se.printStackTrace();
//                    System.exit(1);
                }
                try {
                    ps.executeUpdate();
                } catch (SQLException se) {
                } catch (Exception e) {
                }
            }
        }
        db.closeConnection(c);
    }

    private static List<String> normalizeGenres(List<String> moviesGenres) {
        String idMovie = "";
        String genre = "";
        String[] genres;
        List<String> list = new ArrayList<String>();
        for (String movieGenre : moviesGenres) {
            //[1498346][Horror; Sci-Fi; Thriller]
            idMovie = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));

            movieGenre = movieGenre.replace("[" + idMovie + "]", "");
            genre = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));

            if (!genre.contains(";")) {
//                System.out.println("genre = " + genre);
                list.add("[" + idMovie + "][" + genre.trim() + "]");
            } else {

                genres = genre.split(";");

                for (int i = 0; i < genres.length; i++) {
                    list.add("[" + idMovie + "][" + genres[i].trim() + "]");
                }

            }

        }

        return list;
    }
}
