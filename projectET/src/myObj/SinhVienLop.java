/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myObj;

/**
 *
 * @author meolam39
 */
public class SinhVienLop {
    
    //Khai bao thuoc tinh
    private String maSV;
    private String maLop;
    private double diem = -1;
    
    public SinhVienLop(){}
    
    //Khoi tao
    public SinhVienLop(SinhVienLop svl)
    {
        this.maSV=svl.maSV;
        this.maLop=svl.maLop;
        this.diem=svl.diem;
    }
    
    public SinhVienLop(String maSV, String maLop, double diem)
    {
        this.maSV=maSV;
        this.maLop=maLop;
        this.diem=diem;
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
    public void setMaLop(String maLop)
    {
        this.maLop=maLop;
    }
    public String getMaLop()
    {
        return this.maLop;
    }
    public void setDiem(double diem)
    {
        this.diem=diem;
    }
    public double getDiem()
    {
        return this.diem;
    }
}
