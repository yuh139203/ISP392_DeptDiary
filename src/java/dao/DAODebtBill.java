/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import model.DBContext;
import model.DBContextSQLserver;

/**
 *
 * @author yuh
 */
public class DAODebtBill extends DBContextSQLserver {

    private DBContext db;

    public DAODebtBill() {
        db = DBContext.getInstance();
    }

    //Tìm TypeDebtID
    public int findTypeDebtId(String debtType) throws SQLException {
        String sql = "SELECT ID FROM TypeDebt WHERE [Type] = ?";
        try ( PreparedStatement pstmt = this.conn.prepareStatement(sql)) { // Sử dụng conn từ DBContextSQLserver
            pstmt.setString(1, debtType);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID");
                } else {
                    return -1; // Loại nợ không tìm thấy
                }
            }
        }
    }
    // Insert vào trong DebtBill

    public boolean insertDebtBill(int idTypeDebt, String amount, String note, List<String> imgPathsForDB, String cdate) {
        int IDDebtor = 1; // Ví dụ: Lấy từ form hoặc session
        String createdBy = "Admin"; // Mặc định người tạo

        // Xây dựng câu lệnh SQL với các cột hình ảnh động
        String sql = "INSERT INTO DebtBill (IDDebtor, IDTypeDebt, Amount, Description, EvidenceImg1, EvidenceImg2, EvidenceImg3, EvidenceImg4, EvidenceImg5, DebtTerm, CreatedAt, CreatedBy, isDelete) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, IDDebtor);
            ps.setInt(2, idTypeDebt);
            ps.setString(3, amount);
            ps.setString(4, note);

            // Điền đường dẫn ảnh hoặc NULL nếu không có ảnh
            for (int i = 0; i < 5; i++) {
                if (i < imgPathsForDB.size()) {
                    ps.setString(5 + i, imgPathsForDB.get(i));
                } else {
                    ps.setNull(5 + i, Types.VARCHAR);
                }
            }

            ps.setString(10, cdate);
            ps.setString(11, createdBy);
            ps.setBoolean(12, false); // Giả định rằng isDelete mặc định là false

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
