/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBContextSQLserver;

/**
 *
 * @author yuh
 */
public class DAODebtor extends DBContextSQLserver{
    public boolean addProfileOfDebtor(String name, String phoneNumber, String email, String address, int createdBy) {
        String sql = "INSERT INTO Debtor ( FullName, PhoneNumber, Email, [Address], isDelete, CreatedAt,CreatedBy)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, 0, GETDATE(),?)";
        try {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1, name);
            ptm.setString(2, phoneNumber);
            ptm.setString(3, email);
            ptm.setString(4, address);
            ptm.setInt(5, createdBy);
            int result = ptm.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAODebtor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean addProfileOfDebtorWithAvatar(String avatar, String name, String phoneNumber, String email, String address, int createdBy) {
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
