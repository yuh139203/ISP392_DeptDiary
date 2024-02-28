/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAODebtor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import model.Debtor;
import utils.GetFileName;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

/**
 *
 * @author yuh
 */
public class UpdateDebtorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateDebtorInforController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateDebtorInforController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idDebtor = Integer.parseInt(request.getParameter("id"));
        DAODebtor dao = new DAODebtor();
        Debtor d = dao.findByID(idDebtor);
        request.setAttribute("debtor", d);
        request.getRequestDispatcher("updateDebtorInfor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idDebtor = Integer.parseInt(request.getParameter("idDebtor"));
        String avatar = request.getParameter("avatar");
        Part avatarPart = request.getPart("avatar");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        DAODebtor daoDebtor = new DAODebtor();
        String fileName = GetFileName.extractFileName(avatarPart);

        if (!fileName.isEmpty()) {
            String uploadPath = "D:/FPT University/Spring_2024/ISP392/Project/ISP392_DeptDiary/web/uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String avatarFilePath = uploadPath + File.separator + fileName;
            try {
                avatarPart.write(avatarFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String impPath = "uploads/" + fileName;
            boolean added = daoDebtor.updateProfile(impPath, fullName, phoneNumber, address, idDebtor);
            if (added) {
                request.setAttribute("noti", "Success!!");
                request.getRequestDispatcher("updateDebtorInfor.jsp").forward(request, response);
            } else {
                request.setAttribute("noti", "Failed!!");
                request.getRequestDispatcher("updateDebtorInfor.jsp").forward(request, response);
            }
        } else {
            if (avatar != null && !avatar.isEmpty()) {
                boolean added = daoDebtor.updateProfile(avatar, fullName, phoneNumber, address, idDebtor);
                if (added) {
                    request.setAttribute("noti", "Success!!");
                    request.getRequestDispatcher("addDebtor.jsp").forward(request, response);
                } else {
                    request.setAttribute("noti", "Failed!!");
                    request.getRequestDispatcher("addDebtor.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
