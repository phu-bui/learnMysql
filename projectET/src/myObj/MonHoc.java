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
public class MonHoc {
    
    //Khai bao thuoc tinh
    private String maMH;
    private String tenMH;
    private double soTC;
    
    public MonHoc(){}
    
    //Khoi tao
    public MonHoc(MonHoc mh)
    {
        this.maMH=mh.maMH;
        this.tenMH=mh.tenMH;
        this.soTC=mh.soTC;
    }
    
    public MonHoc(String maMH, String tenMH, double soTC)
    {
        this.maMH=maMH;
        this.tenMH=tenMH;
        this.soTC=soTC;
    }
    
    //set/get
    public void setMaMH(String maMH)
    {
        this.maMH=maMH;
    }
    public String getMaMH()
    {
        return this.maMH;
    }
    public void setTenMH(String tenMH)
    {
        this.tenMH=tenMH;
    }
    public String getTenMH()
    {
        return this.tenMH;
    }
    public void setSoTC(double soTC)
    {
        this.soTC=soTC;
    }
    public double getSoTC()
    {
        return this.soTC;
    }
}
