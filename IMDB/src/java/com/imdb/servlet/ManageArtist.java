/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.servlet;

import com.imdb.dao.DAOArtist;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Valter
 */
public class ManageArtist extends HttpServlet {

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

        if (action.equals("selectMovies")) {
            selectMovies(request, response);
        } else if (action.equals("register")) {
            register(request, response);
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
        HttpSession session = request.getSession();

        String name = (String) session.getAttribute("artistName");
        String gender = (String) session.getAttribute("artistGender");

        String idMovie = "";
        String info = "";

        String status = "box-info";
        String message = "";

        int iMovie;

        for (int i = 0; i < 20; i++) {
            if (request.getParameter("result-" + i) != null) {
                idMovie += request.getParameter("result-" + i) + "&&";
                info += request.getParameter("info-" + i) + "&&";
            }
        }

        DAOArtist daoArtist = new DAOArtist();
        int idArtist;

        if (gender != null) {
            idArtist = daoArtist.getActorId() + 1;
            if (daoArtist.insertActor(idArtist, name, gender)) {

                String[] split = idMovie.split("&&");
                String[] character = info.split("&&");
                for (int j = 0; j < split.length; j++) {
                    iMovie = Integer.parseInt(split[j]);

                    if (!daoArtist.insertActorMovie(iMovie, idArtist, character[j])) {
                        status = "box-error";
                        message += "Ocorreu um erro ao tentar cadastrar o seu artista no(s) filme(s) que você selecionou , tente novamente. <br/>";
                    }
                }
                status = "box-success";
                message += "O seu artista foi cadastrado com sucesso! <br/>";

            } else {
                status = "box-error";
                message += "Ocorreu um erro ao tentar cadastrar o seu artista, tente novamente. <br/>";

            }

        } else {
            idArtist = daoArtist.getDirectorId() + 1;

            if (daoArtist.insertDirector(idArtist, name)) {

                String[] split = idMovie.split("&&");
                String[] information = info.split("&&");

                for (int j = 0; j < split.length; j++) {
                    iMovie = Integer.parseInt(split[j]);

                    if (!daoArtist.insertDirectorMovie(iMovie, idArtist, information[j])) {
                        status = "box-error";
                        message += "Ocorreu um erro ao tentar cadastrar o seu artista no(s) filme(s) que você selecionou , tente novamente. <br/>";
                    }
                }
                status = "box-success";
                message += "O seu artista foi cadastrado com sucesso! <br/>";

            } else {
                status = "box-error";
                message += "Ocorreu um erro ao tentar cadastrar o seu artista, tente novamente. <br/>";

            }
        }


        session.setAttribute("status", status);
        session.setAttribute("message", message);

        session.removeAttribute("artistName");
        session.removeAttribute("artistGender");

        forward("status.jsp", request, response);
    }

    private void selectMovies(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");

        request.getSession().setAttribute("artistName", name);
        request.getSession().setAttribute("artistGender", gender);

        forward("selectMovies.jsp", request, response);

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
}
