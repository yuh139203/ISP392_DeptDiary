/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAODebtBill;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.MultipartConfig;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ussat
 */
// Thêm annotation này ngay trước định nghĩa lớp của servlet
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class DebtBillController extends HttpServlet {

    private DAODebtBill DAOdebtBill = new DAODebtBill();

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
            out.println("<title>Servlet DebtBillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DebtBillServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("debtbill.jsp").forward(request, response);
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
        //get về các giá trị trên jsp
        String debtType = request.getParameter("debtType");
        String amount = request.getParameter("amount");
        String cdate = request.getParameter("date");
        String note = request.getParameter("note");
        // Xử lý file upload
        Collection<Part> fileParts = request.getParts();
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + "uploadedImages"; // Thư mục để lưu ảnh

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        List<String> imgPathsForDB = new ArrayList<>();
        for (Part filePart : fileParts) {
            if (filePart.getName().equals("images[]")) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (fileName != null && !fileName.isEmpty()) {
                    String filePath = savePath + File.separator + fileName;
                    filePart.write(filePath); // Lưu file vào đường dẫn
                    imgPathsForDB.add("uploadedImages/" + fileName); // Đường dẫn để lưu vào DB
                }
            }
        }
        // Sử dụng DAO để thực hiện các thao tác với DB
        try {
            // Tìm IDTypeDebt dựa trên debtType
            int idTypeDebt = DAOdebtBill.findTypeDebtId(debtType);
            if (idTypeDebt == -1) {
                // Không tìm thấy idTypeDebt, xử lý lỗi phù hợp
                request.setAttribute("errorMessage", "Loại nợ không hợp lệ.");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            // Insert thông tin vào bảng DebtBill
            boolean insertResult = DAOdebtBill.insertDebtBill(idTypeDebt, amount, note, imgPathsForDB, cdate);

            if (insertResult) {
                // Chuyển hướng người dùng đến trang danh sách nợ sau khi insert thành công
                response.sendRedirect("diary");
            } else {
                // Xử lý trường hợp insert không thành công
                request.setAttribute("errorMessage", "Không thể chèn dữ liệu vào cơ sở dữ liệu.");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, ví dụ: chuyển hướng đến trang lỗi
            request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
