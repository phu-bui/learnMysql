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
public class GiaoVien {
    
    //Khai bao thuoc tinh
    private String maGV;
    private String hoGV;
    private String tenGV;
    private String donVi;
    
    public GiaoVien(){};
    
    //Khoi tao
    public GiaoVien(GiaoVien gv)
    {
        this.maGV=gv.maGV;
        this.hoGV=gv.hoGV;
        this.tenGV=gv.tenGV;
        this.donVi=gv.donVi;
    }
    
    public GiaoVien(String maGV, String hoGV, String tenGV, String donVi)
    {
        this.maGV=maGV;
        this.hoGV=hoGV;
        this.tenGV=tenGV;
        this.donVi=donVi;
    }

    public GiaoVien(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //set/get
    public void setMaGV(String maGV)
    {
        this.maGV=maGV;
    }
    public String getMaGV()
    {
        return this.maGV;
    }
    public void setHoGV(String hoGV)
    {
        this.hoGV=hoGV;
    }
    public String getHoGV()
    {
        return this.hoGV;
    }
    public void setTenGV(String tenGV)
    {
        this.tenGV=tenGV;
    }
    public String getTenGV()
    {
        return this.tenGV;
    }
    public void setDonVi(String donVi)
    {
        this.donVi=donVi;
    }
    public String getDonVi()
    {
        return this.donVi;
    }
            
}
