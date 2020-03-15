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
import myObj.SinhVien;

/**
 *
 * @author meolam39
 */
public class commandSinhVien {
    
    
    public static boolean insertSV(SinhVien sv)
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        try {            
            Statement statement = conn.createStatement();
            String sql = "Insert into SinhVien(MaSV,HoSV,TenSV,NgaySinh,NoiSinh) values('"+sv.getMaSV()+"','"+sv.getHoSV()+"','"+sv.getTenSV()+"','"+sv.getNgaySinh()+"','"+sv.getNoiSinh()+"')";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return  false;
    }
    public static boolean updateSV(SinhVien sv)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Update SinhVien set HoSV = '"+sv.getHoSV()+"',TenSV = '"+sv.getTenSV()+"',NgaySinh = '"+sv.getNgaySinh()+"',NoiSinh = '"+sv.getNoiSinh() +"' where MaSV = '"+sv.getMaSV()+"'";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteSV(SinhVien sv)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Delete from SinhVien where MaSV = '"+sv.getMaSV()+"'";
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }      
    
    public static void inputSV(String filePath) throws IOException
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
                String[] ngaySinh=output[3].split("/");
                output[3]=ngaySinh[2]+"-"+ngaySinh[1]+"-"+ngaySinh[0];
                sql = "INSERT INTO `SinhVien` VALUES ('"+output[0]+"','"+output[1]+"','"+output[2]+"','"+output[3]+"','"+output[4]+"');";
                if(text.equals("")==false) try {                 
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    sql = "Update SinhVien set HoSV = '"+output[1]+"',TenSV = '"+output[2]+"',NgaySinh = '"+output[3]+"',NoiSinh = '"+output[4] +"' where MaSV = '"+output[0]+"'";
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
    
    public static ArrayList<SinhVien> getListSV()
    {
        ArrayList<SinhVien> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from SinhVien"; 
            ResultSet rs =  statement.executeQuery(sql);
            SinhVien sv;
            while(rs.next())    
            {
                sv = new SinhVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(sv);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;        
    }  
    
    public static ArrayList<SinhVien> getListSearchSV(SinhVien svs)
    {
        ArrayList<SinhVien> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from SinhVien where "; 
            boolean check=false;
            if(svs.getMaSV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"MaSV = '"+svs.getMaSV()+"'";
            }
            if(svs.getHoSV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"HoSV = '"+svs.getHoSV()+"'";
            }
            if(svs.getTenSV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"TenSV = '"+svs.getTenSV()+"'";
            }
            if(svs.getNgaySinh().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"NgaySinh = '"+svs.getNgaySinh()+"'";
            }
            if(svs.getNoiSinh().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"NoiSinh = '"+svs.getNoiSinh()+"'";
            }      
            ResultSet rs =  statement.executeQuery(sql);
            SinhVien sv;
            while(rs.next())    
            {
                sv = new SinhVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(sv);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;        
    }  

}
