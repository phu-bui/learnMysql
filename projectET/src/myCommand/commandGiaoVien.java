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
import myObj.GiaoVien;

/**
 *
 * @author meolam39
 */
public class commandGiaoVien {
    
    public static boolean insertGV(GiaoVien gv)
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        try {            
            Statement statement = conn.createStatement();
            String sql = "Insert into GiaoVien(MaGV,HoGV,TenGV,DonVi) values('"+gv.getMaGV()+"','"+gv.getHoGV()+"','"+gv.getTenGV()+"','"+gv.getDonVi()+"')";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return  false;
    }
    public static boolean updateGV(GiaoVien gv)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Update GiaoVien set HoGV = '"+gv.getHoGV()+"',TenGV = '"+gv.getTenGV()+"',DonVi = '"+gv.getDonVi() +"' where MaGV = '"+gv.getMaGV()+"'";      
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteGV(GiaoVien gv)
    {
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Delete from GiaoVien where MaGV = '"+gv.getMaGV()+"'";
            int rs =  statement.executeUpdate(sql);
            return rs>0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }      
    
    public static void inputGV(String filePath) throws IOException
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
                sql = "INSERT INTO `GiaoVien` VALUES ('"+output[0]+"','"+output[1]+"','"+output[2]+"','"+output[3]+"');";
                if(text.equals("")==false) try {                 
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    sql = "Update GiaoVien set HoGV = '"+output[1]+"',TenGV = '"+output[2]+"',DonVi = '"+output[3] +"' where MaGV = '"+output[1]+"'";
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
    
    public static ArrayList<GiaoVien> getListGV()
    {
        ArrayList<GiaoVien> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from GiaoVien"; 
            ResultSet rs =  statement.executeQuery(sql);
            GiaoVien gv;
            while(rs.next())    
            {
                gv = new GiaoVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                list.add(gv);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;
        
    }  
    
    public static ArrayList<GiaoVien> getListSearchGV(GiaoVien gvs)
    {
        ArrayList<GiaoVien> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from GiaoVien where "; 
            boolean check=false;
            if(gvs.getMaGV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"MaGV = '"+gvs.getMaGV()+"'";
            }
            if(gvs.getHoGV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"HoGV = '"+gvs.getHoGV()+"'";
            }
            if(gvs.getTenGV().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"TenGV = '"+gvs.getTenGV()+"'";
            }
            if(gvs.getDonVi().equals("")==false)
            {
                if(!check) check=true;
                else sql=sql+" and ";
                sql=sql+"DonVi = '"+gvs.getDonVi()+"'";
            }
                 
            ResultSet rs =  statement.executeQuery(sql);
            GiaoVien gv;
            while(rs.next())    
            {
                gv = new GiaoVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                list.add(gv);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }        
        return list;        
    }
    
}
