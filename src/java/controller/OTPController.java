/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOUser;
import dao.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.MD5;

/**
 *
 * @author yuh
 */
public class OTPController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OTPController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OTPController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("otp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOUser daoUser = new DAOUser();
        HttpSession session = request.getSession();
        String emailSignUp = (String) session.getAttribute("emailSignUp");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String tokenIn = request.getParameter("tokenIn");

        // Kiểm tra xem token đã được tạo và lưu trong phiên làm việc chưa
        String token = (String) session.getAttribute("token");
        // Nếu token không tồn tại trong phiên làm việc, hãy tạo một token mới
        if (token == null) {
            token = SendMail.generateRandomToken();
            try {
                SendMail.sendMail(emailSignUp, token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Lưu trữ token được tạo trong phiên làm việc
            session.setAttribute("token", token);
        }
        System.out.println("token input "+tokenIn);
        System.out.println("token gen "+ token);
        System.out.println("username "+ username);
        System.out.println("password "+ password);
        System.out.println("emailSignUp "+ emailSignUp);
        
        if (tokenIn != null && tokenIn.equals(token)) {
            String encryptedPassword = MD5.hashPassword(password);
            daoUser.insertUser(username, encryptedPassword, emailSignUp);
            response.sendRedirect("login.jsp");
        }
        else {
            request.setAttribute("error", "Token không hợp lệ!!!");
            request.getRequestDispatcher("otp.jsp").forward(request, response);
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
