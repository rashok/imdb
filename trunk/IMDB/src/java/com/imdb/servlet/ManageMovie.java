/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.servlet;

import com.imdb.dao.DAOGenre;
import com.imdb.dao.DAOLanguage;
import com.imdb.dao.DAOMovie;
import com.imdb.database.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valter
 */
public class ManageMovie extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action.equals("register")) {
            register(request, response);
        } else if (action.equals("getInfo")) {
            getGenres(request, response);
            getLanguages(request, response);
            forward("cadastro.jsp", request, response);
        } else if (action.equals("search")) {
            search(request, response);
        } else if (action.equals("getLanguagesGenres")) {
            getLanguages(request, response);
            getGenres(request, response);
            forward("search.jsp", request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void register(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        int idGenre = Integer.parseInt(request.getParameter("genre"));
        int idLanguage = Integer.parseInt(request.getParameter("language"));
        String info = request.getParameter("info");

        String status = "box-info";
        String message = "";

        DAOMovie daoMovie = new DAOMovie();
        int idMovie = daoMovie.getMovieId() + 1;

        if (daoMovie.insertMovie(idMovie, title, year)) {

            DAOGenre daoGenre = new DAOGenre();
            if (!daoGenre.insertGenreMovie(idGenre, idMovie)) {
                status = "box-error";
                message += "Não conseguimos cadastrar o gênero do seu filme, por favor tente novamente.";

            }

            DAOLanguage daoLanguage = new DAOLanguage();
            if (!daoLanguage.insertLanguageMovie(idLanguage, idMovie, info)) {
                status = "box-error";
                message += "Não conseguimos cadastrar a língua do seu filme, por favor tente novamente.";

            }
            status = "box-success";
            message += "Parabéns. O seu filme foi cadastrado com sucesso!";

        } else {
            status = "box-error";
            message += "Não conseguimos cadastrar o seu filme, por favor tente novamente.";
        }

        request.getSession().setAttribute("status", status);
        request.getSession().setAttribute("message", message);

        forward("status.jsp", request, response);

    }

    private void getGenres(HttpServletRequest request, HttpServletResponse response) {
        DAOMovie daoMovie = new DAOMovie();
        List<String> genres = daoMovie.getGenres();

        request.getSession().setAttribute("genres", genres);

    }

    private void getLanguages(HttpServletRequest request, HttpServletResponse response) {
        DAOMovie daoMovie = new DAOMovie();
        List<String> languages = daoMovie.getLanguages();

        request.getSession().setAttribute("languages", languages);

    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            System.out.println("ManageMovie:forward");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ManageMovie:forward");
            System.out.println(ex.getMessage());
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("search");
        int index = Integer.parseInt(request.getParameter("index"));

        DAOMovie daoMovie = new DAOMovie();
        List<String> results = daoMovie.search(search, index);

        request.getSession().setAttribute("search", search);
        request.getSession().setAttribute("index", index);
        request.getSession().setAttribute("results", results);

        forward("selectMovies.jsp", request, response);
    }
}
