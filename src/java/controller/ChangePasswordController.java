/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOUser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import utils.MD5;

/**
 *
 * @author admin
 */
public class ChangePasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOUser userDao = new DAOUser();
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.findByID(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        DAOUser userDao = new DAOUser();

        int userId = Integer.parseInt(request.getParameter("id"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String retypePassword = request.getParameter("retypePassword");

        if (newPassword.isEmpty() || retypePassword.isEmpty()) {
            String notification = "New password and re-type password must not be blank";
            session.setAttribute("notification", notification);
            response.sendRedirect("change_password?id=" + userId);
        } else if (newPassword.equals(oldPassword)) {
            String notification = "New password must not be the same as old password";
            session.setAttribute("notification", notification);
            response.sendRedirect("change_password?id=" + userId);
        } else if (!newPassword.equals(retypePassword)) {
            String notification = "Re-type password must be the same as new password";
            session.setAttribute("notification", notification);
            response.sendRedirect("change_password?id=" + userId);
        } else {
            User user = userDao.findByID(userId);
            String encryptedPassword = MD5.hashPassword(newPassword);
            user.setPassWord(encryptedPassword);
            int updatePassword = userDao.updatePassWord(user);
            if (updatePassword == 1) {
                String notification = "Update password success";
                session.setAttribute("notification", notification);
                response.sendRedirect("change_password?id=" + userId);
            } else {
                String notification = "Update password fail.";
                session.setAttribute("notification", notification);
                response.sendRedirect("change_password?id=" + userId);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
