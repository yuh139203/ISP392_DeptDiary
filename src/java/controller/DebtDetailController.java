/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DAODebtBill;
import dao.DAODebtor;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DebtBill;
import model.Debtor;

/**
 *
 * @author namte
 */
public class DebtDetailController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DebtDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DebtDetailController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       //        String resultLimitParameter = request.getParameter("resultlimit");
        int numPerPage;
//        if (resultLimitParameter == null || resultLimitParameter.isEmpty()) {
            numPerPage = 10; // giá trị mặc định
//        } else {
//            numPerPage = Integer.parseInt(resultLimitParameter);
//        }
        int idDebtor = 3;
        DAODebtor daoDebtor = new DAODebtor();
        Debtor d = daoDebtor.findByID(idDebtor);
        DAODebtBill dao = new DAODebtBill();
        List<DebtBill> list = dao.getDebtbillByDebtorID(idDebtor);
        int listSize = list.size();
        int page;
        int num = (listSize % numPerPage == 0 ? (listSize / numPerPage) : ((listSize / numPerPage)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numPerPage;
        end = Math.min(page * numPerPage, listSize);
        List<DebtBill> data = dao.getListByPage(list, start, end);
        int startPage = start + 1;

        request.setAttribute("data", data);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", end);
        request.setAttribute("num", num);
        request.setAttribute("listSize", listSize);
        request.setAttribute("listSizePerPage", data.size());
        request.setAttribute("numperpage", numPerPage);
        request.setAttribute("debtor", d);
        request.getRequestDispatcher("debtBillDetail.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
