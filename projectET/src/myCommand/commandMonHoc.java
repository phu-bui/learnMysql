/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import myObj.MonHoc;

/**
 *
 * @author meolam39
 */
public class commandMonHoc {
    
    public static boolean insertMH(MonHoc mh)
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        try {            
            Statement statement = conn.createStatement();
            String sql = "Insert into MonHoc(MaMH,TenMH,SoTC) values('"+mh.getMaMH()+"','"+mh.getTenMH()+"',"+mh.getSoTC()+")";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return  false;
    }
    public static boolean updateMH(MonHoc mh)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Update MonHoc set TenMH = '"+mh.getTenMH()+"',SoTC = "+mh.getSoTC() +" where MaMH = '"+mh.getMaMH()+"'";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteMH(MonHoc mh)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Delete from MonHoc where MaMH = '"+mh.getMaMH()+"'";
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void inputMH(String filePath) throws IOException
    {
        File file = new File(filePath);
        if(!file.exists()) return;
        Connection conn = JDBCConnection.getJDBCConnection();
        BufferedReader br = null;
        try {            
            Statement statement = conn.createStatement();
            String sql="";
            br = new BufferedReader(new FileReader(filePath));
            String text= br.readLine();
            while (text!=null)
            {
                String[] output=text.split(",");
                sql = "INSERT INTO `MonHoc` VALUES ('"+output[0]+"','"+output[1]+"',"+output[2]+");";
                if(text.equals("")==false) try {                 
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    sql = "Update MonHoc set TenMH = '"+output[1]+"',SoTC = "+output[2] +" where MaMH = '"+output[0]+"'";  
                    statement.executeUpdate(sql);
                    Logger.getLogger(commandOther.class.getName()).log(Level.SEVERE, null, ex);
                }  
                //System.out.println(sql);
                text= br.readLine();
            }                 
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    
    public static ArrayList<MonHoc> getListMH()
    {
        ArrayList<MonHoc> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from MonHoc"; 
            ResultSet rs =  statement.executeQuery(sql);
            MonHoc mh;
            while(rs.next())
            {
                mh=new MonHoc(rs.getString(1),rs.getString(2),rs.getDouble(3));
                list.add(mh);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;
        
    }  
  
}
