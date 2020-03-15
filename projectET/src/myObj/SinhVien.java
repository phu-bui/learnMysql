/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myObj;

import java.util.Date;

/**
 *
 * @author meolam39
 */
public class SinhVien {
    
    //Khai bao thuoc tinh
    private String maSV;
    private String hoSV;
    private String tenSV;
    private String ngaySinh;
    private String noiSinh;
    
    public SinhVien(){}
    
    //Khoi tao
    public SinhVien(SinhVien sv)
    {
        this.maSV=sv.maSV;
        this.hoSV=sv.hoSV;
        this.tenSV=sv.tenSV;
        this.ngaySinh=sv.ngaySinh;
        this.noiSinh=sv.noiSinh;
    }
    
    public SinhVien(String maSV, String hoSV, String tenSV, String ngaySinh, String noiSinh)
    {
        this.maSV=maSV;
        this.hoSV=hoSV;
        this.tenSV=tenSV;
        this.ngaySinh=ngaySinh;
        this.noiSinh=noiSinh;
    }
    
    //set/get
    public void setMaSV(String maSV)
    {
        this.maSV=maSV;
    }
    public String getMaSV()
    {
        return this.maSV;
    }
    public void setHoSV(String hoSV)
    {
        this.hoSV=hoSV;
    }
    public String getHoSV()
    {
        return this.hoSV;
    }
    public void setTenSV(String tenSV)
    {
        this.tenSV=tenSV;
    }
    public String getTenSV()
    {
        return this.tenSV;
    }
    public void setNgaySinh(String ngaySinh)
    {
        this.ngaySinh=ngaySinh;
    }
    public String getNgaySinh()
    {
        return this.ngaySinh;
    }
    public void setNoiSinh(String noiSinh)
    {
        this.noiSinh=noiSinh;
    }
    public String getNoiSinh()
    {
        return this.noiSinh;
    }
    
}
