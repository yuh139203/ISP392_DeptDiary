package controller;


import dao.DAOUser;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.User;


public class ResetPasswordController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DAOUser userDao = new DAOUser();
        User user = (User)session.getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOUser userDao = new DAOUser();
        
        int userId = Integer.parseInt(request.getParameter("id"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String retypePassword = request.getParameter("retypePassword");

        
        
        if (newPassword.isEmpty() || retypePassword.isEmpty()) {
            String noti = "New password and re-type password must not be blank";
            request.setAttribute("noti", noti);
            response.sendRedirect("reset_password?id=" + userId);
        } else if (newPassword.equals(oldPassword)) {
            String noti = "New password must not be the same as old password";
            request.setAttribute("noti", noti);
            response.sendRedirect("reset_password?id=" + userId);
        } else if (!newPassword.equals(retypePassword)) {
            String noti = "Re-type password must be the same as new password";
            request.setAttribute("noti", noti);
            response.sendRedirect("reset_password?id=" + userId);
        } else {
            User user = userDao.findByID(userId);
            user.setPassWord(newPassword);
            int updatePassword = userDao.updatePassWord(user);
            if (updatePassword == 1) {
                String noti = "Update password success";
                request.setAttribute("noti", noti);
                response.sendRedirect("login.jsp");
            } else {
                String noti = "Update password fail.";
                request.setAttribute("noti", noti);
                response.sendRedirect("reset_password?id=" + userId);
            }
        }
        
        
        
    }

}


