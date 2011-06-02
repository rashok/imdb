/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.servlet;

import com.imdb.beans.User;
import com.imdb.dao.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author 317306
 */
public class ManageUser extends HttpServlet {

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
        } else if (action.equals("login")) {
            login(request, response);
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
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String profile = request.getParameter("profile");

        int prof = 0;

        String status = "box-info";
        String message = "";

        DAOUser daoUser = new DAOUser();
        if (profile.equals("ajudante")) {
            prof = 1;
        } else if (profile.equals("administrador")) {
            prof = 2;
        }
        if (daoUser.insertUser(email, name, password, prof)) {
            status = "box-success";
            message += "Cadastro realizado com sucesso!.";
        } else {
            status = "box-error";
            message += "Não conseguimos realizar o cadastro , por favor tente novamente.";
        }

        request.getSession().setAttribute("status", status);
        request.getSession().setAttribute("message", message);

        forward("status.jsp", request, response);

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

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DAOUser daoUser = new DAOUser();
        User user = daoUser.login(email, password);

        request.getSession().setAttribute("user", user);

        forward("index.jsp", request, response);


    }
}
