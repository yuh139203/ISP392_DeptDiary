/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import utils.Captcha;

/**
 *
 * @author yuh
 */
public class SignUpController extends HttpServlet {

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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
        String captchaText = Captcha.generateCaptchaText();

        // Lưu CAPTCHA text vào session để kiểm tra sau này
        HttpSession session = request.getSession();
        session.setAttribute("captchaText", captchaText);

        // Tạo hình ảnh CAPTCHA và gửi về client
        BufferedImage image = Captcha.generateCaptchaImage(captchaText);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
        request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        //get về giá trị username và password và gmail
        HttpSession session = request.getSession();
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");
        String captchaInput = request.getParameter("captchaInput");
        String captchaGen = (String) session.getAttribute("captchaText");
          
        
        // đối chứng 2 mật khẩu xem có đúng không
        if (!password.equals(confirmPassword)) {
            // Nếu mật khẩu không khớp, gửi thông báo lỗi
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("confirmPasswordErrorMessage", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        // tạo đối tượng từ giữ liệu đã get về 
        DAOUser daoUser = new DAOUser();
        // check xem username đã tồn tại hay chưa    
        if (daoUser.isUserNameExists(username)) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("userNameErrorMessage", "UserName đã ton tai.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if ("true".equals(request.getParameter("refreshCaptcha"))) {
            // Redirect back to the login page to regenerate the captcha
            response.sendRedirect("signup.jsp");
            return;
        }
        session.setAttribute("emailSignUp", email);   
        session.setAttribute("username", username);   
        session.setAttribute("password", password);
        if (captchaInput != null && captchaInput.equals(captchaGen)) {
                request.getRequestDispatcher("otp").forward(request, response);
            } else {
                request.setAttribute("error", "Captcha invalid!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
