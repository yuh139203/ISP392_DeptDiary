package controller;

import dao.SendMail;
import dao.DAOUser;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import model.User;
import utils.Captcha;

public class ForgotPasswordController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String token = SendMail.generateRandomToken();
        String user = request.getParameter("username");
        String captchaInput = request.getParameter("captchaInput");
        String captchaGen = (String) session.getAttribute("captchaText");

        DAOUser daoUser = new DAOUser();
        User u = daoUser.findByUserName(user);
        session.setAttribute("userForgotPass", u);
        
        if ("true".equals(request.getParameter("refreshCaptcha"))) {
            // Redirect back to the login page to regenerate the captcha
            response.sendRedirect("forgotPassword.jsp");
            return;
        }

        if (u == null) {
            //khong thay
            request.setAttribute("error", "Username not found!!!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        } else {
            //tim thay
            if (captchaInput != null && captchaInput.equals(captchaGen)) {
                response.sendRedirect("token.jsp");
                try {
                    SendMail.sendMail(u.getEmail(), token);
                    session.setAttribute("generatedToken", token);
                } catch (Exception ex) {
                    Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                request.setAttribute("error", "Captcha invalid!!!");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            }

        }
    }
}


