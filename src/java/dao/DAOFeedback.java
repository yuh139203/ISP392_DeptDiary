/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;

/**
 *
 * @author yuh
 */
public class DAOFeedback {
    
    static DBContext db;

    public DAOFeedback() {
        db = DBContext.getInstance();
    }
    
    
    public boolean addFeedback(int rate, String comment, int createdBy) {
        String sql = "INSERT INTO FeedBack(Rate,Comment, isDelete, CreatedAt, CreatedBy)\n"
                + "VALUES\n"
                + "(?, ?, 0, CURRENT_TIMESTAMP,?)";
        try {
            PreparedStatement ptm = db.getConnection().prepareStatement(sql);
            ptm.setInt(1, rate);
            ptm.setString(2, comment);
            ptm.setInt(3, createdBy);
            int result = ptm.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) {
        DAOFeedback dao = new DAOFeedback();
        boolean add=dao.addFeedback(5, "good", 3);
        if(add){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
        
    }
    
    
}
