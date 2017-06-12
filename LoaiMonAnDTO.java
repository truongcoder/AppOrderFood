package com.example.pctruong.apporderfood.DTO;

/**
 * Created by PCTRUONG on 12/30/2016.
 */

public class LoaiMonAnDTO {
    int MaLoai;
    String TenLoai;
    String HinhAnh;

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }



    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
