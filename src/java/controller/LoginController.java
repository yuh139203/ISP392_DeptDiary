/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import model.User;
import utils.Captcha;
import utils.MD5;

/**
 *
 * @author yuh
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        String captchaText = Captcha.generateCaptchaText();
//
//        // Lưu CAPTCHA text vào session để kiểm tra sau này
//        HttpSession session = request.getSession();
//        session.setAttribute("captchaText", captchaText);
//        BufferedImage image = Captcha.generateCaptchaImage(captchaText);
//        response.setContentType("image/png");
//        OutputStream os = response.getOutputStream();
//        ImageIO.write(image, "png", os);
//        os.close();
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String rem = request.getParameter("rem");
        String captchaInput = request.getParameter("captchaInput");
        String captchaGen = (String) session.getAttribute("captchaText");
        String encryptedPassword = MD5.hashPassword(pass);

        //tao cookie
        Cookie cu = new Cookie("cuser", user);
        Cookie cp = new Cookie("cpass", pass);
        Cookie cr = new Cookie("crem", rem);

        if (rem != null) {
            cu.setMaxAge(60 * 60 * 24 * 7); //7 ngay
            cp.setMaxAge(60 * 60 * 24 * 7); //7 ngay
            // cr.setMaxAge(60 * 60 * 24 * 7); //7 ngay
        } else {// khong tich
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        response.addCookie(cu);//luu vao browser
        response.addCookie(cp);
        response.addCookie(cr);

        if ("true".equals(request.getParameter("refreshCaptcha"))) {
            // Redirect back to the captcha page to regenerate the captcha
            response.sendRedirect("login.jsp");
            return;
        }

        DAOUser daoUser = new DAOUser();
        User u = daoUser.checkExistentUser(user, encryptedPassword);
        if (u == null) {
            //khong thay
            request.setAttribute("error", "Username or Password invalid!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //tim thay
            if (captchaInput != null && captchaInput.equals(captchaGen)) {
                if (u.getIdRole() == 2) {
                    // Nếu idRole là 2, chuyển hướng đến trang admin.jsp
                    response.sendRedirect("admin.jsp");
                    session.setAttribute("userLogin", u);
                } else {
                    // Nếu idRole không phải là 2, chuyển hướng đến trang welcome.jsp
                    session.setAttribute("userLogin", u);
                    response.sendRedirect("welcome.jsp");
                }
            } else {
                request.setAttribute("error", "Captcha invalid!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
