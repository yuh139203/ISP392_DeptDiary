package controller;

import dao.SendMail;
import dao.DAOUser;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;

public class ForgotPasswordController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String token = SendMail.generateRandomToken();
        DAOUser daoUser = new DAOUser();
        User u = daoUser.findByUserName(userName);
        session.setAttribute("user", u);
        if (u == null) {
            request.setAttribute("error", "Username not found!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("token.jsp");
            try {
                SendMail.sendMail(u.getEmail(), token, userName);
                session.setAttribute("generatedToken", token);
            } catch (Exception ex) {
                Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
