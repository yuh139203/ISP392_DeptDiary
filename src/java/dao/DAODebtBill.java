/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.DebtBill;
import utils.DBContext;

/**
 *
 * @author yuh
 */
public class DAODebtBill {

    DBContext db;

    public DAODebtBill() {
        db = DBContext.getInstance();
    }

    //Tìm TypeDebtID
    public int findTypeDebtId(String debtType) throws SQLException {
        String sql = "SELECT ID FROM TypeDebt WHERE Type = ?";
        try ( PreparedStatement pstmt = this.db.getConnection().prepareStatement(sql)) { // Sử dụng conn từ DBContextSQLserver
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
        String createdBy = "?"; // Mặc định người tạo

        // Xây dựng câu lệnh SQL với các cột hình ảnh động
        String sql = "INSERT INTO DebtBill (IDDebtor, IDTypeDebt, Amount, Description, EvidenceImg1, EvidenceImg2, EvidenceImg3, EvidenceImg4, EvidenceImg5, DebtTerm, CreatedAt, CreatedBy, isDelete) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";

        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
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

    public List<DebtBill> getDebtbillByDebtorID(int debtorID) {
        List<DebtBill> list = new ArrayList<>();
        String sql = "SELECT * FROM DebtBill WHERE IDDebtor = ? ";
        try ( PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setInt(1, debtorID);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    DebtBill debtBill = new DebtBill(
                            rs.getInt("ID"),
                            rs.getInt("IDDebtor"),
                            rs.getInt("IDTypeDebt"),
                            rs.getString("Amount"),
                            rs.getString("Description"),
                            rs.getString("DebtTerm"),
                            rs.getString("EvidenceImg1"),
                            rs.getString("EvidenceImg2"),
                            rs.getString("EvidenceImg3"),
                            rs.getString("EvidenceImg4"),
                            rs.getString("EvidenceImg5"),
                            rs.getBoolean("isDelete"),
                            rs.getString("CreatedAt"),
                            rs.getString("CreatedBy"),
                            rs.getString("UpdatedAt"),
                            rs.getString("DeletedAt"),
                            rs.getString("DeletedBy")
                    );
                    list.add(debtBill);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public List<DebtBill> getListByPage(List<DebtBill> list, int start, int end) {
        ArrayList<DebtBill> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<DebtBill> getAllDebtBillByIDDebtor(int debtorID) {
        List<DebtBill> list = new ArrayList<>();
        String sql = "SELECT DebtBill.*, TypeDebt.Type "
                + "FROM DebtBill "
                + "INNER JOIN TypeDebt ON DebtBill.IDTypeDebt = TypeDebt.ID "
                + "WHERE DebtBill.IDDebtor = ?";
        try ( PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setInt(1, debtorID);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    DebtBill debtBill = new DebtBill(
                            rs.getInt("ID"),
                            rs.getInt("IDDebtor"),
                            rs.getInt("IDTypeDebt"),
                            rs.getString("Amount"),
                            rs.getString("Description"),
                            rs.getString("DebtTerm"),
                            rs.getString("EvidenceImg1"),
                            rs.getString("EvidenceImg2"),
                            rs.getString("EvidenceImg3"),
                            rs.getString("EvidenceImg4"),
                            rs.getString("EvidenceImg5"),
                            rs.getBoolean("isDelete"),
                            rs.getString("CreatedAt"),
                            rs.getString("CreatedBy"),
                            rs.getString("UpdatedAt"),
                            rs.getString("DeletedAt"),
                            rs.getString("DeletedBy"),
                            rs.getString("Type")
                    );
                    list.add(debtBill);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DAODebtBill dao = new DAODebtBill();
        List<DebtBill> list = new ArrayList<>();
        list = dao.getAllDebtBillByIDDebtor(1);
        for (DebtBill debtBill : list) {
            System.out.println(list);
        }
    }

}
