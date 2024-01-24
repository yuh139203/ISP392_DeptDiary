package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class TokenController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("token.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String generatedToken = (String) session.getAttribute("generatedToken");
        String enteredToken = (String) request.getParameter("enteredToken");
        
        if (enteredToken != null && enteredToken.equals(generatedToken)) {
            response.sendRedirect("resetPassword.jsp");
        } else {
            request.setAttribute("error", "Token invalid!!!");
            request.getRequestDispatcher("token.jsp").forward(request, response);
        }  

    }

}
