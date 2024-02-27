/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAODebtor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Debtor;
import model.User;


/**
 *
 * @author yuh
 */
public class DiaryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DiaryController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiaryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAODebtor dao = new DAODebtor();
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("userLogin");
        List<Debtor> list = dao.getAllDebtor(u.getId());
        int listSize = list.size();
        int page, numperpage = 8;
        int num = (listSize % 8 == 0 ? (listSize / 8) : ((listSize / 8)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, listSize);
        List<Debtor> data = dao.getListByPage(list, start, end);
        int startPage = start + 1;

        request.setAttribute("page", page);
        request.setAttribute("data", data);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", end);
        request.setAttribute("num", num);
        request.setAttribute("listSize", listSize);
        request.getRequestDispatcher("diary.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
