package com.example.pctruong.apporderfood.DTO;

/**
 * Created by PCTRUONG on 12/30/2016.
 */

public class GoiMonDTO {
   private   int MaGoiMon,MaBan,MaNV;
   private String TinhTrang,NgayGoi;

    public int getMaGoiMon() {
        return MaGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        MaGoiMon = maGoiMon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getNgayGoi() {
        return NgayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        NgayGoi = ngayGoi;
    }
}
