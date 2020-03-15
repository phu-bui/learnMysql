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
public class Lop {
    
    //Khai bao thuoc tinh
    private String maLop;
    private String maMH;
    private String namHoc;
    private String hocKy;
    private String maGV;
    
    public  Lop(){};
    
    //Khoi tao
    public Lop(Lop l)
    {
        this.maLop=l.maLop;
        this.maMH=l.maMH;
        this.namHoc=l.namHoc;
        this.hocKy=l.hocKy;
        this.maGV=l.maGV;
    }
    
    public Lop(String maLop, String maMH, String namHoc, String hocKy, String maGV)
    {
        this.maLop=maLop;
        this.maMH=maMH;
        this.namHoc=namHoc;
        this.hocKy=hocKy;
        this.maGV=maGV;
    }
    
    //set/get
    public void setMaLop(String maLop)
    {
        this.maLop=maLop;
    }
    public String getMaLop()
    {
        return this.maLop;
    }
    public void setMaMH(String maMH)
    {
        this.maMH=maMH;
    }
    public String getMaMH()
    {
        return this.maMH;
    }
    public void setNamHoc(String namHoc)
    {
        this.namHoc=namHoc;                
    }
    public String getNamHoc()
    {
        return this.namHoc;
    }
    public void setHocKy(String hocKy)
    {
        this.hocKy=hocKy;
    }
    public String getHocKy()
    {
        return this.hocKy;
    }
    public void setMaGV(String maGV)
    {
        this.maGV=maGV;
    }
    public String getMaGV()
    {
        return this.maGV;
    }
}
