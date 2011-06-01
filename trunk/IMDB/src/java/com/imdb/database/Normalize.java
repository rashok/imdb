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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
Class description goes here
 *
 * @version <version>
 * @author Valter
 */
public class Normalize {

    public static void main(String args[]) throws FileNotFoundException, IOException {

        System.out.println("START");
        Normalize normalize = new Normalize();

        /* Normalizando a tabela genres */

//        List<String> genres = normalize.normalizeGenre();
//        normalize.setGenre(genres);

        /* Normailizando a tabela language  */

//        List<String> languages = normalize.normalizeLanguages();
//        normalize.setLanguage(languages);

        /* Normalizando movie e language */

//        normalize.writeMovieLanguage();

//        List<String> movieLanguages = normalize.normalizeMovieLanguages();
//        normalize.setMovieLanguageFile(movieLanguages);
//        normalize.writeMovieLanguageNormalized(movieLanguages);

//        FileReader reader = new FileReader(new File("movieLanguageNormalizados.txt"));
//        BufferedReader leitor = new BufferedReader(reader);
//
//        List<String> list = new ArrayList<String>();
//
//        String linha = leitor.readLine();
//        while (linha != null && linha.length() > 0) {
//            linha = leitor.readLine();
//            list.add(linha);
//        }
//        
//        reader.close();
//        leitor.close();
//
//        normalize.setMovieLanguage(list);



        /* Normalizando a tabela genres */
//        normalize.writeMovieGenre();
//        List<String> genres = normalize.normalizeGenres();
//        normalize.setGenre(genres);

//        List<String> movieGenres = normalize.normalizeMovieGenres();
//        normalize.setMovieGenre(movieGenres);


        /* Normalizando a tabela character */
        List<String> characters = normalize.normalizeMovieActor();
        normalize.setMovieActorFile(characters);
//        normalize.writeFile(movieActors, "movieActorNaoNormalizados.txt");




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
        String query = "SELECT languages FROM disciplinabd..movies WHERE languages IS NOT NULL group by languages";
//        String query = "SELECT top 1000 languages FROM disciplinabd..movies WHERE languages IS NOT NULL group by languages";
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

            query = "SELECT languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL group by languages";
//            query = "SELECT top 1000 languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL group by languages";
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

    public Map<String, Integer> getLanguagesAndId() {
        String query = "SELECT language, id FROM language GROUP BY language,id";
        Map<String, Integer> languages = new HashMap<String, Integer>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                languages.put(rs.getString("language"), rs.getInt("id"));
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

    public Map<String, Integer> getGenresAndId() {
        String query = "SELECT genre, id FROM genre GROUP BY genre,id";
        Map<String, Integer> languages = new HashMap<String, Integer>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                languages.put(rs.getString("genre"), rs.getInt("id"));
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

    public List<String> getMovieAndLanguage() {
        String query = "SELECT movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages";

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
                list.add("[" + rs.getString("movieid") + "][" + rs.getString("languages") + "]");
            }

            query = "SELECT movieid,languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add("[" + rs.getString("movieid") + "][" + rs.getString("languages") + "]");
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

    public List<String> getMovieAndGenres() {
        String query = "SELECT movieid,genres FROM disciplinabd..movies WHERE genres IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, genres";

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

            query = "SELECT movieid,genres FROM disciplinabd..moremovies WHERE genres IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, genres";

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

    public List<String> getMovieAndActor() {
        String query = "";

        List<String> list = new ArrayList<String>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt;
        ResultSet rs = null;
        int i = 1;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            FileWriter fstream = new FileWriter("movieActorNaoNormalizados.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);

            while (i > 0) {
                query = "SELECT TOP (1000000) movieid, actorid, as_character FROM disciplinabd..movies WHERE (movieid NOT IN (SELECT TOP (" + i + " * 1000000 ) movieid FROM disciplinabd..movies)) AND movieid IS NOT NULL AND actorid IS NOT NULL AND as_character IS NOT NULL AND as_character <> ' ' AND movieid <> '11111111111111' GROUP BY movieid, actorid, as_character";
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    list.add("[" + rs.getString("movieid") + "][" + rs.getString("actorid") + "][" + rs.getString("as_character") + "]");
                }


                for (String line : list) {
                    out.write(line + "\n");

                }

                if (list.size() < 1000000) {
                    i = -1;
                }

                list.clear();
            }

            out.close();



//            query = "SELECT movieid, actorid, as_character FROM disciplinabd..moremovies WHERE movieid <> '11111111111111' AND movieid IS NOT NULL AND actorid IS NOT NULL AND as_character IS NOT NULL GROUP BY movieid, actorid, as_character";

//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                list.add("[" + rs.getString("movieid") + "][" + rs.getString("actorid") + "][" + rs.getString("as_character") + "]");
//            }
//
//            fstream = new FileWriter("movieActorNaoNormalizados.txt", true);
//            out = new BufferedWriter(fstream);
//
//            for (String line : list) {
//                out.write(line + "\n");
//            }
//
//            out.close();
//            list.clear();

            if (con != null) {
                rs = null;
                db.closeConnection(con);
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<String> normalizeMovieLanguages() {
        List<String> old = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        Map<String, Integer> newLanguages = new HashMap<String, Integer>();


        try {
            Normalize normalize = new Normalize();

            Map<String, Integer> languages = normalize.getLanguagesAndId(); // leva 2 segundos para ler


            FileReader reader = new FileReader(new File("movieLanguageNaoNormalizados.txt"));
            BufferedReader leitor = new BufferedReader(reader);

            Integer idLanguage;

            String idMovie = "";
            String language = "";
            String info = "";

            String linha = leitor.readLine();
            while (linha != null && linha.length() > 0) {
                linha = leitor.readLine();
                old.add(linha);
            }
            reader.close();
            leitor.close();


            for (String movieLanguage : old) {

                if (movieLanguage != null && !movieLanguage.isEmpty()) {
                    idMovie = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));
                    movieLanguage = movieLanguage.replace("[" + idMovie + "]", "");
                    language = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));

                    if (!language.isEmpty()) {

                        if (language.contains("- (")) {
                            // English; Japanese
                            // Arabic - (some dialogue); Indonesian
                            //System.out.println("language = " + language);
                            if (language.contains(";")) {
                                String[] split = language.split(";");

                                for (int i = 0; i < split.length; i++) {
                                    language = split[i];

                                    info = formatInfo(language);
                                    language = language.replace("[" + info + "]", "");

                                    if (language.contains(" -")) {
                                        language = language.substring(0, language.indexOf(" -"));
                                    }

                                    while (language.charAt(0) == ' ') {
                                        language = language.substring(1, language.length());
                                    }

                                    while (language.charAt(language.length() - 1) == ' ') {
                                        language = language.substring(0, language.length() - 1);
                                    }


                                    idLanguage = languages.get(language);
                                    newLanguages.put("[" + idLanguage + "][" + idMovie + "][" + info + "]", 0);

                                    if (languages.get(language) == null) {
                                        System.out.println("****************** movieLanguage = " + movieLanguage);
                                        System.out.println("****************** language = " + language);
                                    }
                                }

                            } else {

                                info = formatInfo(language);
                                language = language.replace("[" + info + "]", "");

                                if (language.contains(" -")) {
                                    language = language.substring(0, language.indexOf(" -"));
                                }

                                while (language.charAt(0) == ' ') {
                                    language = language.substring(1, language.length());
                                }

                                while (language.charAt(language.length() - 1) == ' ') {
                                    language = language.substring(0, language.length() - 1);
                                }


                                idLanguage = languages.get(language);
                                newLanguages.put("[" + idLanguage + "][" + idMovie + "][" + info + "]", 0);


                                if (languages.get(language) == null) {
                                    System.out.println("****************** movieLanguage = " + movieLanguage);
                                    System.out.println("****************** language = " + language);
                                }
                            }



                        } else if (language.contains(";")) {
                            String[] split = language.split(";");
                            for (int i = 0; i < split.length; i++) {
                                language = split[i];

                                if (language.contains(" -")) {
                                    language = language.substring(0, language.indexOf(" -"));
                                }

                                while (language.charAt(0) == ' ') {
                                    language = language.substring(1, language.length());
                                }

                                while (language.charAt(language.length() - 1) == ' ') {
                                    language = language.substring(0, language.length() - 1);
                                }


                                idLanguage = languages.get(language);
                                newLanguages.put("[" + idLanguage + "][" + idMovie + "][" + info + "]", 0);


                                if (languages.get(language) == null) {
                                    System.out.println("****************** movieLanguage = " + movieLanguage);
                                    System.out.println("****************** language = " + language);
                                }

                            }


                        } else {
                            // Russian - English subtitle 
                            // English - 1
                            if (language.contains(" -")) {
                                language = language.substring(0, language.indexOf(" -"));
                            }

                            idLanguage = languages.get(language);
                            newLanguages.put("[" + idLanguage + "][" + idMovie + "][" + info + "]", 0);

                            if (languages.get(language) == null) {
                                System.out.println("****************** movieLanguage = " + movieLanguage);
                                System.out.println("****************** language = " + language);
                            }

                        }
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException ioe) {
            System.out.println("ioe = " + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it = newLanguages.entrySet().iterator();
        String language = "";
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            language = (String) pairs.getKey();
            list.add(language);
        }

        return list;

    }

    public List<String> normalizeGenres() {
        List<String> old = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        Map<String, Integer> newGenres = new HashMap<String, Integer>();

        try {
            FileReader reader = new FileReader(new File("movieGenreNaoNormalizados.txt"));
            BufferedReader leitor = new BufferedReader(reader);

            String idMovie = "";
            String genre = "";

            String linha = leitor.readLine();
            while (linha != null && linha.length() > 0) {
                linha = leitor.readLine();
                old.add(linha);
            }
            reader.close();
            leitor.close();


            for (String movieGenre : old) {

                if (movieGenre != null && !movieGenre.isEmpty()) {
                    idMovie = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));
                    movieGenre = movieGenre.replace("[" + idMovie + "]", "");
                    genre = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));

                    if (!genre.isEmpty()) {

                        if (genre.contains(";")) {
                            String[] split = genre.split(";");

                            for (int i = 0; i < split.length; i++) {
                                genre = split[i];

                                while (genre.charAt(0) == ' ') {
                                    genre = genre.substring(1, genre.length());
                                }

                                while (genre.charAt(genre.length() - 1) == ' ') {
                                    genre = genre.substring(0, genre.length() - 1);
                                }

                                newGenres.put(genre, 0);
                            }
                        } else {
                            newGenres.put(genre, 0);
                        }
                    }
                }
            }

            Iterator it = newGenres.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                genre = (String) pairs.getKey();
                list.add(genre);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException ioe) {
            System.out.println("ioe = " + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<String> normalizeMovieActor() {
        List<String> old = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        Map<String, Integer> movieActor = new HashMap<String, Integer>();

        String idMovie = "";
        String idActor = "";
        String character = "";
        try {
            FileReader reader = new FileReader(new File("movieActorNaoNormalizados.txt"));
            BufferedReader leitor = new BufferedReader(reader);


            String linha = leitor.readLine();
            while (linha != null && linha.length() > 0) {
                idMovie = linha.substring(linha.indexOf("[") + 1, linha.indexOf("]"));
                linha = linha.replace("[" + idMovie + "]", "");

                idActor = linha.substring(linha.indexOf("[") + 1, linha.indexOf("]"));
                linha = linha.replace("[" + idActor + "]", "");

                character = linha;

                if (character.contains("'")) {
                    character = character.replace("'", "");
                }

                movieActor.put("[" + idMovie + "][" + idActor + "][" + character + "]", 0);

                linha = leitor.readLine();

            }

            System.out.println("movieActor.size() = " + movieActor.size());
            reader.close();
            leitor.close();

            Iterator it = movieActor.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                character = (String) pairs.getKey();
                list.add(character);
            }

            System.out.println("list.size() = " + list.size());

        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException ioe) {
            System.out.println("ioe = " + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<String> normalizeCharacter() {
        List<String> old = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        Map<String, Integer> characters = new HashMap<String, Integer>();

        try {
            FileReader reader = new FileReader(new File("movieActorNaoNormalizados.txt"));
            BufferedReader leitor = new BufferedReader(reader);

            String character = "";

            String linha = leitor.readLine();
            while (linha != null && linha.length() > 0) {
                //[569489][1942991][[Ellen]  <1>]

                character = linha.substring(linha.lastIndexOf("[") + 1, linha.lastIndexOf("]"));
                if (character.contains("]")) {
                    character = character.substring(0, character.lastIndexOf("]"));
                }

                if (character.contains("'")) {
                    character = character.replace("'", "");
                }

                if (character.length() > 0 && character.contains("<") && character.contains(">")) {
                    character = character.substring(0, character.indexOf("<"));
                }

                if (character.length() > 0) {
                    characters.put(character, 0);
                }

                linha = leitor.readLine();
            }
            reader.close();
            leitor.close();

            Iterator it = characters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                character = (String) pairs.getKey();
                list.add(character);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException ioe) {
            System.out.println("ioe = " + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<String> normalizeMovieGenres() {
        List<String> old = new ArrayList<String>();
        List<String> list = new ArrayList<String>();



        try {
            Normalize normalize = new Normalize();

            Map<String, Integer> genres = normalize.getGenresAndId(); // leva 2 segundos para ler

            FileReader reader = new FileReader(new File("movieGenreNaoNormalizados.txt"));
            BufferedReader leitor = new BufferedReader(reader);

            Integer idGenre;

            String idMovie = "";
            String genre = "";

            String linha = leitor.readLine();
            while (linha != null && linha.length() > 0) {
                linha = leitor.readLine();
                old.add(linha);
            }
            reader.close();
            leitor.close();


            for (String movieGenre : old) {

                if (movieGenre != null && !movieGenre.isEmpty()) {
                    idMovie = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));
                    movieGenre = movieGenre.replace("[" + idMovie + "]", "");
                    genre = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));

                    if (!genre.isEmpty()) {

                        if (genre.contains(";")) {
                            String[] split = genre.split(";");

                            for (int i = 0; i < split.length; i++) {
                                genre = split[i];

                                while (genre.charAt(0) == ' ') {
                                    genre = genre.substring(1, genre.length());
                                }

                                while (genre.charAt(genre.length() - 1) == ' ') {
                                    genre = genre.substring(0, genre.length() - 1);
                                }

                                idGenre = genres.get(genre);
                                list.add("[" + idGenre + "][" + idMovie + "]");

                                if (genres.get(genre) == null) {
                                    System.out.println("****************** movieGenre = " + movieGenre);
                                    System.out.println("****************** genre = " + genre);
                                }
                            }

                        } else {

                            while (genre.charAt(0) == ' ') {
                                genre = genre.substring(1, genre.length());
                            }

                            while (genre.charAt(genre.length() - 1) == ' ') {
                                genre = genre.substring(0, genre.length() - 1);
                            }

                            idGenre = genres.get(genre);
                            list.add("[" + idGenre + "][" + idMovie + "]");

                            if (genres.get(genre) == null) {
                                System.out.println("****************** movieGenre = " + movieGenre);
                                System.out.println("****************** genre = " + genre);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException ioe) {
            System.out.println("ioe = " + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("list.size() = " + list.size());

        return list;

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
            if (right > 0 && left > 0) {
                info = language.substring(left, language.length() - 1);
            }
        } else {
            if (right > 0 && left > 0) {
                info = language.substring(left, right);
            }
        }

        return info;
    }

    public List<String> normalizeLanguages() {
        Normalize normalize = new Normalize();
        List<String> languages = normalize.getLanguages();
        List<String> newLanguages = new ArrayList<String>();

        Map<String, Integer> newLanguage = new HashMap<String, Integer>();

        int size = languages.size();

        for (String language : languages) {

            if (language.contains("- (")) {
                //English - (with French subtitles)
                language = language.substring(0, language.indexOf("- (") - 1);

                if (language.contains(";")) {
                    String[] split = language.split(";");

                    for (int i = 0; i < split.length; i++) {

                        while (split[i].charAt(0) == ' ') {
                            split[i] = split[i].substring(1, split[i].length());
                        }

                        newLanguages.add(split[i]);
                    }

                } else {

                    while (language.charAt(0) == ' ') {
                        language = language.substring(1, language.length());
                    }

                    newLanguages.add(language);
                }

            } else {

                if (language.contains(";")) {
                    String[] split = language.split(";");

                    for (int i = 0; i < split.length; i++) {
                        while (split[i].charAt(0) == ' ') {
                            split[i] = split[i].substring(1, split[i].length());
                        }

                        newLanguages.add(split[i]);
                    }
                } else {
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
//                System.out.println("Uma exceção durante o preparo do Statement: provavelmente erro no SQL.");
//                se.printStackTrace();
//                System.exit(1);
            }
            try {
                ps.executeUpdate();
            } catch (SQLException se) {
//                System.out.println("Uma exceção durante a atualização: provavelmente erro no SQL ou problema na conexão.");
//                se.printStackTrace();
//                System.exit(1);
            }
        }
        db.closeConnection(c);
    }

    public void setGenres(List<String> genres) {
        DataBase db = new DataBase();
        Connection c = db.getConnection();
        PreparedStatement ps = null;

        for (String genre : genres) {
            try {
                ps = c.prepareStatement("INSERT INTO genre (genre) VALUES  (?)");
                ps.setString(1, genre);

            } catch (SQLException se) {
            }
            try {
                ps.executeUpdate();
            } catch (SQLException se) {
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
            try {

                idLanguage = Integer.parseInt(movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]")));
                movieLanguage = movieLanguage.replace("[" + idLanguage + "]", "");

                idMovie = Integer.parseInt(movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]")));
                movieLanguage = movieLanguage.replace("[" + idMovie + "]", "");

                info = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));

                ps = c.prepareStatement("INSERT INTO language_movie (idLanguage, idMovie, infAdd) VALUES (?, ?, ?)");
                ps.setInt(1, idLanguage);
                ps.setInt(2, idMovie);
                ps.setString(3, info);

                ps.executeUpdate();

            } catch (SQLException se) {
            } catch (Exception e) {
            }

        }
        db.closeConnection(c);
    }

    public void setMovieGenre(List<String> movieGenres) {
        String idMovie = "";
        String idGenre = "";

        File file = new File("insertMovieGenre.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }

                PrintWriter out = new PrintWriter(file);

                String query = "USE labbd11 GO ";
                query += "INSERT INTO genre_movie (idGenre, idMovie) ";

                for (String movieGenre : movieGenres) {

                    idGenre = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));
                    movieGenre = movieGenre.replace("[" + idGenre + "]", "");

                    idMovie = movieGenre.substring(movieGenre.indexOf("[") + 1, movieGenre.indexOf("]"));

                    query += "SELECT " + idGenre + "," + idMovie;
                    query += " UNION ALL ";

                    out.println(query);
                    query = "";
                }
                out.println("GO");

            } catch (IOException ex) {
            } catch (Exception e) {
            }

        }
    }

    public void setMovieLanguageFile(List<String> movieLanguages) {
        String idMovie = "";
        String idLanguage = "";
        String info = "";
        File file = new File("insertMovieLanguage.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }

                PrintWriter out = new PrintWriter(file);

                String query = "USE labbd11 GO ";
                query += "INSERT INTO language_movie (idLanguage, idMovie, infAdd) ";

                for (String movieLanguage : movieLanguages) {

                    idLanguage = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));
                    movieLanguage = movieLanguage.replace("[" + idLanguage + "]", "");

                    idMovie = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));
                    movieLanguage = movieLanguage.replace("[" + idMovie + "]", "");

                    info = movieLanguage.substring(movieLanguage.indexOf("[") + 1, movieLanguage.indexOf("]"));
                    if (info.contains("'")) {
                        info = info.replace("'", "");
                    }

                    query += "SELECT " + idLanguage + "," + idMovie + ", '" + info + "'";
                    query += " UNION ALL ";

                    out.println(query);
                    query = "";
                }
                out.println("GO");

            } catch (IOException ex) {
            } catch (Exception e) {
            }

        }
    }

    public void setMovieActorFile(List<String> movieActors) {
        String idMovie = "";
        String idActor = "";
        String info = "";
        File file = new File("insertMovieActor.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }

                PrintWriter out = new PrintWriter(file);

                String query = "USE labbd11 GO ";
                query += "INSERT INTO movie_actor (idMovie, idActor, character) ";

                for (String movieActor : movieActors) {
                    idMovie = movieActor.substring(movieActor.indexOf("[") + 1, movieActor.indexOf("]"));
                    movieActor = movieActor.replace("[" + idMovie + "]", "");

                    idActor = movieActor.substring(movieActor.indexOf("[") + 1, movieActor.indexOf("]"));
                    movieActor = movieActor.replace("[" + idActor + "]", "");

                    while (movieActor.contains("[")){
                        movieActor = movieActor.replace("[", "");
                    }
                    
                    while (movieActor.contains("]")){
                        movieActor = movieActor.replace("]", "");
                    }
                    
                    while (movieActor.contains("'")){
                        movieActor = movieActor.replace("'", "");
                    }
                    
                    
                    info = movieActor; 
                    
//                    info = movieActor.substring(movieActor.lastIndexOf("[") + 1, movieActor.indexOf("]"));
                    
                    /*if (info.contains("'")) {
                        info = info.replace("'", "");
                    }*/

                    query += "SELECT " + idMovie + "," + idActor + ", '" + info + "'";
                    query += " UNION ALL ";

                    out.println(query);
                    query = "";
                }
                out.println("GO");

            } catch (IOException ex) {
            } catch (Exception e) {
            }

        }

    }

    public void setCharacterFile(List<String> characters) {
        String idMovie = "";
        String idLanguage = "";
        String info = "";
        File file = new File("insertCharacter.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }

                PrintWriter out = new PrintWriter(file);

                String query = "USE labbd11 GO ";
                query += "INSERT INTO actor_character (character) ";

                for (String character : characters) {

                    if (character.length() > 3) {

                        query += "SELECT '" + character + "'";
                        query += " UNION ALL ";

                        out.println(query);
                        query = "";
                    }
                }
                out.println("GO");

            } catch (IOException ex) {
            } catch (Exception e) {
            }

        }
    }

    public void writeMovieLanguage() {
        List<String> list = getMovieAndLanguage();
        File file = new File("movieLanguageNaoNormalizados.txt");

        if (!file.isDirectory()) {
            System.out.println("Não é um diretório válido");
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
                System.out.println("Arquivo não existe");
            } catch (IOException ex) {
            }
        }

        if (!file.canWrite()) {
            System.out.println("Permissão para escrita negada!");
        }
        try {
            PrintWriter out = new PrintWriter(file);
            for (String movieLanguage : list) {
                out.println(movieLanguage);
            }
            out.close();
        } catch (FileNotFoundException ex) {
        }
    }

    public void writeMovieGenre() {
        List<String> list = getMovieAndGenres();
        File file = new File("movieGenreNaoNormalizados.txt");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
                System.out.println("Arquivo não existe");
            } catch (IOException ex) {
            }
        }

        if (!file.canWrite()) {
            System.out.println("Permissão para escrita negada!");
        }
        try {
            PrintWriter out = new PrintWriter(file);
            for (String movieLanguage : list) {
                out.println(movieLanguage);
            }
            out.close();
        } catch (FileNotFoundException ex) {
        }
    }

    private void writeMovieLanguageNormalized(List<String> list) throws IOException {
        File file = new File("movieLanguageNormalizados.txt");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
                System.out.println("Arquivo não existe");
            } catch (IOException ex) {
            }
        } else {
            if (file.delete()) {
                System.out.println("Arquivo antigo apagado com sucesso!");
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
            }
        }

        if (!file.canWrite()) {
            System.out.println("Permissão para escrita negada!");
        }
        try {
            PrintWriter out = new PrintWriter(file);
            for (String movieLanguage : list) {
                out.println(movieLanguage);
            }
            out.close();
        } catch (FileNotFoundException ex) {
        }
    }

    private void writeFile(List<String> list, String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
                System.out.println("Arquivo não existe");
            } catch (IOException ex) {
            }
        } else {
            if (file.delete()) {
                System.out.println("Arquivo antigo apagado com sucesso!");
                if (file.createNewFile()) {
                    System.out.println("Novo arquivo criado com sucesso!");
                } else {
                    System.out.println("Não foi possível criar um novo arquivo!");
                }
            }
        }

        if (!file.canWrite()) {
            System.out.println("Permissão para escrita negada!");
        }
        try {
            PrintWriter out = new PrintWriter(file);
            for (String movieLanguage : list) {
                out.println(movieLanguage);
            }
            out.close();
        } catch (FileNotFoundException ex) {
        }
    }

    private void appendFile(List<String> list, String fileName) {
        try {
            // Create file 
            /*
            File file = new File (fileName);
            if (!file.exists()){
            file.createNewFile();
            }*/

            FileWriter fstream = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(fstream);

            for (String line : list) {
                out.write(line + "\n");
            }


            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
