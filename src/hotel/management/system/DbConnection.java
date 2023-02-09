/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.management.system;

import java.sql.*;

public class DbConnection {
    
    Connection con;
    
    public DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem","root","root");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}
