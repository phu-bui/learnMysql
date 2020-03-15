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
import myObj.SinhVienLop;

/**
 *
 * @author meolam39
 */
public class commandSinhVienLop {
    
    public static boolean insertSVL(SinhVienLop svl)
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        try {            
            Statement statement = conn.createStatement();
            String sql= new String();
            if(svl.getDiem()!=-1) sql = "Insert into SinhVienLop(MaSV,MaLop,Diem) values('"+svl.getMaSV()+"','"+svl.getMaLop()+"',"+svl.getDiem()+")";   
            else sql = "Insert into SinhVienLop(MaSV,MaLop) values('"+svl.getMaSV()+"','"+svl.getMaLop()+"')"; 
            int rs =  statement.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return  false;
    }
    public static boolean updateSVL(SinhVienLop svl)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Update SinhVienLop set Diem = "+svl.getDiem() +" where MaSV = '"+svl.getMaSV()+"' and MaLop = '"+svl.getMaLop()+"'";   
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteSVL(SinhVienLop svl)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Delete from SinhVienLop where MaSV = '"+svl.getMaSV()+"' and MaLop = '"+svl.getMaLop()+"'";
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void inputSVL(String filePath) throws IOException
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
                sql = "INSERT INTO `SinhVienLop` VALUES ('"+output[0]+"','"+output[1]+"',"+output[2]+");";
                if(text.equals("")==false) try {                 
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    sql = "Update SinhVienLop set Diem = "+output[2] +" where MaSV = '"+output[0]+"' and MaLop = '"+output[1]+"'";
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
    
    public static ArrayList<SinhVienLop> getListSVL()
    {
        ArrayList<SinhVienLop> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from SinhVienLop"; 
            ResultSet rs =  statement.executeQuery(sql);
            SinhVienLop svl;
            while(rs.next())
            {
                svl=new SinhVienLop(rs.getString(1),rs.getString(2),rs.getDouble(3));
                list.add(svl);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;
        
    } 
    
}
