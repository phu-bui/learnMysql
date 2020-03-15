/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCommand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import myObj.GiaoVien;
import myObj.Lop;
import myObj.MonHoc;
import myObj.SinhVien;
import myObj.SinhVienLop;

/**
 *
 * @author meolam39
 */
public class commandOther {
    
    public static ArrayList<SinhVien> getListSVpn4(String MaLop)
    {
        ArrayList<SinhVien> list =new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select * from SinhVien inner join SinhVienLop on SinhVien.MaSV = SinhVienLop.MaSV where MaLop = '"+MaLop+"'"; 
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
    
    public static Map<Integer,Object> getListDiemLop(String maLop)
    {
        ArrayList<SinhVienLop> listSVL = new ArrayList<>();
        ArrayList<SinhVien> listSV = new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select SinhVien.MaSV,SinhVien.HoSV,SinhVien.TenSV,SinhVien.NgaySinh,SinhVien.NoiSinh,SinhVienLop.Diem from SinhVienLop inner join SinhVien on SinhVien.MaSV=SinhVienLop.MaSV where MaLop = '"+maLop+"'"; 
            ResultSet rs =  statement.executeQuery(sql);
            SinhVien sv;
            SinhVienLop svl;
            while(rs.next())    
            {
                sv = new SinhVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                svl=new SinhVienLop();
                svl.setDiem(rs.getDouble(6));
                listSV.add(sv);
                listSVL.add(svl);
            }            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        
        Map<Integer,Object> map = new HashMap();
        map.put(1, listSV);
        map.put(2, listSVL);
        return map;        
    }
    
    public static Map<Integer,Object> getListDiemSV(String maSV)
    {
        ArrayList<SinhVienLop> listSVL = new ArrayList<>();
        ArrayList<MonHoc> listMH = new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "Select MonHoc.MaMH,MonHoc.TenMH,MonHoc.SoTC,SinhVienLop.Diem from (SinhVienLop inner join Lop on Lop.MaLop=SinhVienLop.MaLop) inner join MonHoc on Lop.MaMH=MonHoc.MaMh where MaSV = '"+maSV+"'"; 
            ResultSet rs =  statement.executeQuery(sql);
            MonHoc mh;
            SinhVienLop svl;
            while(rs.next())    
            {
                mh = new MonHoc(rs.getString(1),rs.getString(2),rs.getDouble(3));
                svl=new SinhVienLop();
                svl.setDiem(rs.getDouble(4));
                listMH.add(mh);
                listSVL.add(svl);
            }            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        
        Map<Integer,Object> map = new HashMap();
        map.put(1, listMH);
        map.put(2, listSVL);
        return map;        
    }
    
    public static Map<Integer,Object> getListDiemTKSV(String NamHoc, String HocKy)
    {
        ArrayList<SinhVienLop> listSVL = new ArrayList<>();
        ArrayList<SinhVien> listSV = new ArrayList<>();
        try {
            Connection conn = JDBCConnection.getJDBCConnection();
            Statement statement = conn.createStatement();
            String sql = "select SinhVien.MaSV,SinhVien.HoSV,SinhVien.TenSV,sum(MonHoc.SoTC*SinhVienLop.Diem)/sum(MonHoc.SoTC) as dtk from ((SinhVien inner join SinhVienLop on SinhVien.MaSV = SinhVienLop.MaSV) inner join Lop on Lop.MaLop = SinhVienLop.MaLop) inner join MonHoc on Lop.MaMH = MonHoc.MaMH where Lop.NamHoc = '"+NamHoc+"' and Lop.HocKy ='"+HocKy+"' group by (SinhVien.MaSV) order by (dtk) DESC limit 3;"; 
            ResultSet rs =  statement.executeQuery(sql);
            SinhVien sv;
            SinhVienLop svl;
            while(rs.next())    
            {
                sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setHoSV(rs.getString(2));
                sv.setTenSV(rs.getString(3));
                svl=new SinhVienLop();
                svl.setDiem(rs.getDouble(4));
                listSV.add(sv);
                listSVL.add(svl);
            }            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        
        Map<Integer,Object> map = new HashMap();
        map.put(1, listSV);
        map.put(2, listSVL);
        return map;        
    }
    
    public static void saveBackup(String filePath)
    {
        try 
        {
            PrintWriter out = new PrintWriter(filePath);
            String str =    "DROP TABLE IF EXISTS `SinhVienLop`;\n" +
                            "DROP TABLE IF EXISTS `Lop`;\n" +
                            "DROP TABLE IF EXISTS `MonHoc`;\n" +
                            "DROP TABLE IF EXISTS `GiaoVien`;\n" +
                            "DROP TABLE IF EXISTS `SinhVien`;\n";
            out.println(str);
                        
            str =   "CREATE TABLE `GiaoVien` (" +
                    "  `MaGV` char(20) NOT NULL," +
                    "  `HoGV` char(20) DEFAULT NULL," +
                    "  `TenGV` char(20) DEFAULT NULL," +
                    "  `DonVi` char(30) DEFAULT NULL," +
                    "  PRIMARY KEY (`MaGV`)" +
                    ");\n";
            out.println(str);            
            str =   "LOCK TABLES `GiaoVien` WRITE;";
            out.println(str);
            ArrayList<GiaoVien> list = commandGiaoVien.getListGV();
            for(int i=0;i<list.size();i++)
            {         
                if(list.get(i)!=null)
                {
                    str ="INSERT INTO `GiaoVien` VALUES ('"+list.get(i).getMaGV()+"','"+list.get(i).getHoGV()+"','"+list.get(i).getTenGV()+"','"+list.get(i).getDonVi()+"');";
                    out.println(str);
                }
            }
            str ="UNLOCK TABLES;\n";
            out.println(str);
            
            
            str =   "CREATE TABLE `MonHoc` (" +
                    "  `MaMH` char(20) NOT NULL," +
                    "  `TenMH` char(50) DEFAULT NULL," +
                    "  `SoTC` float DEFAULT NULL," +
                    "  PRIMARY KEY (`MaMH`)" +
                    ");\n";
            out.println(str);
            str ="LOCK TABLES `MonHoc` WRITE;";
            out.println(str);
            ArrayList<MonHoc> list1 = commandMonHoc.getListMH();
            for(int i=0;i<list1.size();i++)
            {
                if(list1.get(i)!=null)
                {
                    str = "INSERT INTO `MonHoc` VALUES ('"+list1.get(i).getMaMH()+"','"+list1.get(i).getTenMH()+"',"+list1.get(i).getSoTC()+");";
                    out.println(str);
                }
            }
            str ="UNLOCK TABLES;\n";
            out.println(str);
            
            
            str =   "CREATE TABLE `SinhVien` (" +
                    "  `MaSV` char(20) NOT NULL," +
                    "  `HoSV` char(20) DEFAULT NULL," +
                    "  `TenSV` char(20) DEFAULT NULL," +
                    "  `NgaySinh` date DEFAULT NULL," +
                    "  `NoiSinh` char(30) DEFAULT NULL," +
                    "  PRIMARY KEY (`MaSV`)" +
                    ");\n";
            out.println(str);
            str ="LOCK TABLES `SinhVien` WRITE;";
            out.println(str);
            ArrayList<SinhVien> list2 = commandSinhVien.getListSV();
            for(int i=0;i<list2.size();i++)
            {
                if(list2.get(i)!=null)
                {
                    str = "INSERT INTO `SinhVien` VALUES ('"+list2.get(i).getMaSV()+"','"+list2.get(i).getHoSV()+"','"+list2.get(i).getTenSV()+"','"+list2.get(i).getNgaySinh()+"','"+list2.get(i).getNoiSinh()+"');";
                    out.println(str);
                }
            }
            str ="UNLOCK TABLES;\n";
            out.println(str);
            
            
            str =   "CREATE TABLE `Lop` (" +
                    "  `Malop` char(20) NOT NULL," +
                    "  `MaMH` char(20) DEFAULT NULL," +
                    "  `NamHoc` char(20) DEFAULT NULL," +
                    "  `HocKy` char(10) DEFAULT NULL," +
                    "  `MaGV` char(20) DEFAULT NULL," +
                    "  PRIMARY KEY (`Malop`)," +
                    "  KEY `MaMH` (`MaMH`)," +
                    "  KEY `MaGV` (`MaGV`)," +
                    "  CONSTRAINT `Lop_ibfk_1` FOREIGN KEY (`MaMH`) REFERENCES `MonHoc` (`MaMH`) ON DELETE CASCADE," +
                    "  CONSTRAINT `Lop_ibfk_2` FOREIGN KEY (`MaGV`) REFERENCES `GiaoVien` (`MaGV`) ON DELETE CASCADE" +
                    ");\n";
            out.println(str);
            str ="LOCK TABLES `Lop` WRITE;";
            out.println(str);
            ArrayList<Lop> list3 = cmmandLop.getListLop();
            for(int i=0;i<list3.size();i++)
            {
                if(list3.get(i)!=null)
                {
                    str = "INSERT INTO `Lop` VALUES ('"+list3.get(i).getMaLop()+"','"+list3.get(i).getMaMH()+"','"+list3.get(i).getNamHoc()+"','"+list3.get(i).getHocKy()+"','"+list3.get(i).getMaGV()+"');";
                    out.println(str);
                }
            }
            str ="UNLOCK TABLES;\n";
            out.println(str);
            
            
            str =   "CREATE TABLE `SinhVienLop` (" +
                    "  `MaSV` char(20) NOT NULL," +
                    "  `MaLop` char(20) NOT NULL," +
                    "  `Diem` float DEFAULT NULL," +
                    "  PRIMARY KEY (`MaSV`,`MaLop`)," +
                    "  KEY `MaLop` (`MaLop`)," +
                    "  CONSTRAINT `SinhVienLop_ibfk_1` FOREIGN KEY (`MaSV`) REFERENCES `SinhVien` (`MaSV`) ON DELETE CASCADE," +
                    "  CONSTRAINT `SinhVienLop_ibfk_2` FOREIGN KEY (`MaLop`) REFERENCES `Lop` (`Malop`) ON DELETE CASCADE" +
                    ");\n";
            out.println(str);
            str ="LOCK TABLES `SinhVienLop` WRITE;";
            out.println(str);
            ArrayList<SinhVienLop> list4 = commandSinhVienLop.getListSVL();
            for(int i=0;i<list4.size();i++)
            {
                if(list4.get(i)!=null)
                {
                    str = "INSERT INTO `SinhVienLop` VALUES ('"+list4.get(i).getMaSV()+"','"+list4.get(i).getMaLop()+"',"+list4.get(i).getDiem()+");"; 
                    out.println(str);
                }
            }
            str ="UNLOCK TABLES;\n";
            out.println(str);
            
            out.close();
        } 
        catch (Exception e) 
        {
        }      
                 
    }
    
    public static void restoreBackup(String filePath) throws IOException
    {
        Connection conn = JDBCConnection.getJDBCConnection();
        BufferedReader br = null;
        try {            
            Statement statement = conn.createStatement();
            br = new BufferedReader(new FileReader(filePath));
            String sql= br.readLine();
            while (sql!=null)
            {
                
                if(sql.equals("")==false) try {
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(commandOther.class.getName()).log(Level.SEVERE, null, ex);
                }  
                //System.out.println(sql);
                sql= br.readLine();
            }                 
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public static void saveInitData(String filePath)
    {
        try 
        {
            PrintWriter out = new PrintWriter(filePath);
            String str =    "DROP TABLE IF EXISTS `SinhVienLop`;\n" +
                            "DROP TABLE IF EXISTS `Lop`;\n" +
                            "DROP TABLE IF EXISTS `MonHoc`;\n" +
                            "DROP TABLE IF EXISTS `GiaoVien`;\n" +
                            "DROP TABLE IF EXISTS `SinhVien`;\n";
            out.println(str);
                        
            str =   "CREATE TABLE `GiaoVien` (" +
                    "  `MaGV` char(20) NOT NULL," +
                    "  `HoGV` char(20) DEFAULT NULL," +
                    "  `TenGV` char(20) DEFAULT NULL," +
                    "  `DonVi` char(30) DEFAULT NULL," +
                    "  PRIMARY KEY (`MaGV`)" +
                    ");\n";
            out.println(str);            
                        
            
            str =   "CREATE TABLE `MonHoc` (" +
                    "  `MaMH` char(20) NOT NULL," +
                    "  `TenMH` char(50) DEFAULT NULL," +
                    "  `SoTC` float DEFAULT NULL," +
                    "  PRIMARY KEY (`MaMH`)" +
                    ");\n";
            out.println(str);
                        
            
            str =   "CREATE TABLE `SinhVien` (" +
                    "  `MaSV` char(20) NOT NULL," +
                    "  `HoSV` char(20) DEFAULT NULL," +
                    "  `TenSV` char(20) DEFAULT NULL," +
                    "  `NgaySinh` date DEFAULT NULL," +
                    "  `NoiSinh` char(30) DEFAULT NULL," +
                    "  PRIMARY KEY (`MaSV`)" +
                    ");\n";
            out.println(str);
                        
            
            str =   "CREATE TABLE `Lop` (" +
                    "  `Malop` char(20) NOT NULL," +
                    "  `MaMH` char(20) DEFAULT NULL," +
                    "  `NamHoc` char(20) DEFAULT NULL," +
                    "  `HocKy` char(10) DEFAULT NULL," +
                    "  `MaGV` char(20) DEFAULT NULL," +
                    "  PRIMARY KEY (`Malop`)," +
                    "  KEY `MaMH` (`MaMH`)," +
                    "  KEY `MaGV` (`MaGV`)," +
                    "  CONSTRAINT `Lop_ibfk_1` FOREIGN KEY (`MaMH`) REFERENCES `MonHoc` (`MaMH`) ON DELETE CASCADE," +
                    "  CONSTRAINT `Lop_ibfk_2` FOREIGN KEY (`MaGV`) REFERENCES `GiaoVien` (`MaGV`) ON DELETE CASCADE" +
                    ");\n";
            out.println(str);
                        
            
            str =   "CREATE TABLE `SinhVienLop` (" +
                    "  `MaSV` char(20) NOT NULL," +
                    "  `MaLop` char(20) NOT NULL," +
                    "  `Diem` float DEFAULT NULL," +
                    "  PRIMARY KEY (`MaSV`,`MaLop`)," +
                    "  KEY `MaLop` (`MaLop`)," +
                    "  CONSTRAINT `SinhVienLop_ibfk_1` FOREIGN KEY (`MaSV`) REFERENCES `SinhVien` (`MaSV`) ON DELETE CASCADE," +
                    "  CONSTRAINT `SinhVienLop_ibfk_2` FOREIGN KEY (`MaLop`) REFERENCES `Lop` (`Malop`) ON DELETE CASCADE" +
                    ");\n";
            out.println(str);
            
            
            out.close();
        } 
        catch (Exception e) 
        {
        }      
                 
    }
    
}
