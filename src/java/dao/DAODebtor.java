/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBContextSQLserver;
import model.Debtor;

/**
 *
 * @author yuh
 */
public class DAODebtor extends DBContextSQLserver{
    
    
    public Vector<Debtor> getAll(String sql) {
        Vector<Debtor> vector = new Vector<Debtor>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String avatar = rs.getString("Avatar");
                String fullName = rs.getString("FullName");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String amount = rs.getString("Amount");
                int temp = rs.getInt("isDelete");
                boolean isDelete = (temp == 1 ? true : false);
                String createdAt = rs.getString("CreatedAt");
                String createdBy = rs.getString("CreatedBy");
                String updatedAt = rs.getString("UpdatedAt");
                String deletedAt = rs.getString("DeletedAt");
                String deletedBy = rs.getString("DeletedBy");
                Debtor pro = new Debtor(id, avatar, fullName,
                        phoneNumber, email, address, amount, isDelete, createdAt, createdBy,
                        updatedAt, deletedAt, deletedBy);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODebtor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Debtor> getAllDebtor(int id) {
        String sql = "select * from Debtor where  isDelete = 0 and CreatedBy ="+id;
        return getAll(sql);
    }
    
    public List<Debtor> getListByPage(List<Debtor> list, int start, int end) {
        ArrayList<Debtor> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    public boolean addProfileOfDebtor(String avatar, String name, String phoneNumber, String email, String address, int createdBy) {
        String sql = "INSERT INTO Debtor (Avatar, FullName, PhoneNumber, Email, [Address], isDelete, CreatedAt,CreatedBy)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?, 0, GETDATE(),?)";
        try {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1, avatar);
            ptm.setString(2, name);
            ptm.setString(3, phoneNumber);
            ptm.setString(4, email);
            ptm.setString(5, address);
            ptm.setInt(6, createdBy);
            int result = ptm.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAODebtor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
