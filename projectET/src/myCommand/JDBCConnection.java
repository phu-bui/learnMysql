/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author meolam39
 */
public class JDBCConnection {
    
    public static Connection getJDBCConnection()
    {
        final String url="jdbc:mysql://localhost:3306/projectET";
        final String user="root";
        final String password="123";
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return null;        
    }
}
