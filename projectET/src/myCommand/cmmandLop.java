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
import myObj.Lop;

/**
 *
 * @author meolam39
 */
public class cmmandLop {
    
   public static boolean insertLop(Lop lop)
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        try {            
            Statement statement = conn.createStatement();
            String sql = "Insert into Lop(MaLop,MaMH,NamHoc,HocKy,MaGV) values('"+lop.getMaLop()+"','"+lop.getMaMH()+"','"+lop.getNamHoc()+"','"+lop.getHocKy()+"','"+lop.getMaGV()+"')";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return  false;
    }
    public static boolean updateLop(Lop lop)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Update Lop set NamHoc = '"+lop.getNamHoc()+"',HocKy = '"+lop.getHocKy()+"',MaGV = '"+lop.getMaGV() +"' where MaLop = '"+lop.getMaLop()+"'";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteLop(Lop lop)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Delete from Lop where MaLop = '"+lop.getMaLop()+"'";
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }    
    
    public static void inputLop(String filePath) throws IOException
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
                sql = "INSERT INTO `Lop` VALUES ('"+output[0]+"','"+output[1]+"','"+output[2]+"','"+output[3]+"','"+output[4]+"');";
                if(text.equals("")==false) try {                 
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    sql = "Update Lop set MaMH = '"+output[1]+"',NamHoc = '"+output[2]+"',HocKy = '"+output[3]+"',MaGV = '"+output[4] +"' where MaLop = '"+output[0]+"'";
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
    
    public static ArrayList<Lop> getListLop()
    {
        ArrayList<Lop> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from Lop"; 
            ResultSet rs =  statement.executeQuery(sql);
            Lop lop;
            while(rs.next())    
            {
                lop = new Lop(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(lop);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;
        
    }  
    
    public static ArrayList<Lop> getListSearchLop(Lop lops)
    {
        ArrayList<Lop> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from Lop where "; 
            boolean check=false;
            if(lops.getMaLop().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"MaLop = '"+lops.getMaLop()+"'";
            }
            if(lops.getMaMH().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"MaMH = '"+lops.getMaMH()+"'";
            }
            if(lops.getNamHoc().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"NamHoc = '"+lops.getNamHoc()+"'";
            }
            if(lops.getHocKy().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"HocKy = '"+lops.getHocKy()+"'";
            }
            if(lops.getMaGV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"MaGV = '"+lops.getMaGV()+"'";
            }      
            System.out.println(sql);
            ResultSet rs =  statement.executeQuery(sql);
            Lop lop;
            while(rs.next())    
            {
                lop = new Lop(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(lop);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;        
    }  
    
}
