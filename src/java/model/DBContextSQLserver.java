/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuh
 */
public class DBContextSQLserver {
    public Connection conn=null;
    public DBContextSQLserver(String url,String user,String pass){
        try {
            //call driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // connection
            conn=DriverManager.getConnection(url,user,pass);
                    
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContextSQLserver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContextSQLserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBContextSQLserver() {
       this("jdbc:sqlserver://DESKTOP-NV0CHEA\\\\SQLEXPRESS:1433;databaseName=ISP392", 
                    "sa","12345678");
    }
    public static void main(String[] args) {
        new DBContextSQLserver();
    }


// khaild

}
