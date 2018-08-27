package com.example.bin.managestudentproject.bean;

public class studentsBean {
    private int id;
    private String hoten;
    private String lop;   
    private String chuyenNganh;
    private String sdt;
    private byte[] anhDaiDien;

    public byte[] getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(byte[] anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public int getId() {

        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
