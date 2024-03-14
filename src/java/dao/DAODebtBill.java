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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public boolean insertDebtBill(int IDTypeDebt, int idDebtor, float amount, String note, String debtTerm, List<String> imgPathsForDB, int createdBy) {
        // Assume db is a properly initialized database connection object
        String sql = "INSERT INTO DebtBill (IDDebtor, IDTypeDebt, Amount, Description, EvidenceImg1, EvidenceImg2, EvidenceImg3, EvidenceImg4, EvidenceImg5, DebtTerm, CreatedAt, CreatedBy, isDelete) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, 0)";
        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idDebtor);
            ps.setInt(2, IDTypeDebt);
            ps.setFloat(3, amount);
            ps.setString(4, note);
            // Fill image paths or NULL
            for (int i = 0; i < 5; i++) {
                if (i < imgPathsForDB.size()) {
                    ps.setString(5 + i, imgPathsForDB.get(i));
                } else {
                    ps.setNull(5 + i, java.sql.Types.VARCHAR);
                }
            }
            ps.setString(10, debtTerm); 
            ps.setInt(11, createdBy); 
            int result = ps.executeUpdate();
            if(result > 0) {        
         String updateSql = "UPDATE Debtor SET Amount = Amount + ? WHERE ID = ?";
         
         try(PreparedStatement psUpdate = db.getConnection().prepareStatement(updateSql)) {
            psUpdate.setFloat(1, amount);
            psUpdate.setInt(2, idDebtor);            
            psUpdate.executeUpdate();          
         } catch(SQLException e) {
            e.printStackTrace();
         }   
      }    
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
                            rs.getFloat("Amount"),
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
                            rs.getFloat("Amount"),
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
    
    
    public DebtBill findByID(int id) {
        String sql = "SELECT * FROM DebtBill where ID = ?";
        try {
            PreparedStatement ptm = db.getConnection().prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                DebtBill db = new DebtBill();
                db.setId(rs.getInt("ID"));
                db.setIdDebtor(rs.getInt("IDDebtor"));
                db.setIdTypeDebt(rs.getInt("IDTypeDebt"));
                db.setAmount(rs.getFloat("Amount"));
                db.setDescription(rs.getString("Description"));
                db.setEvidenceImg1(rs.getString("EvidenceImg1"));
                db.setEvidenceImg2(rs.getString("EvidenceImg2"));
                db.setEvidenceImg3(rs.getString("EvidenceImg3"));
                db.setEvidenceImg4(rs.getString("EvidenceImg4"));
                db.setEvidenceImg5(rs.getString("EvidenceImg5"));
                db.setDebtTerm(rs.getString("DebtTerm"));
                db.setCreatedAt(rs.getString("CreatedAt"));
                return db;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
//    (String IDTypeDebt, int idDebtor, float amount, String note, String debtTerm, List<String> imgPathsForDB, int createdBy)
    
    public static void main(String[] args) {
        DAODebtBill dao = new DAODebtBill();
//        boolean insert = dao.insertDebtBill("1", 1, 340, "new", "2024-04-06", imgPathsForDB, 0);
//        if (insert){
//            System.out.println("success");
//        }else{
//            System.out.println("fail");
//        }
    }

}
