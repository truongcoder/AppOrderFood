package com.example.pctruong.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pctruong.apporderfood.DTO.NhanVienDTO;
import com.example.pctruong.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 12/31/2016.
 */

public class NhanVienDAO {
    SQLiteDatabase database;

    public NhanVienDAO(Context context) {
        CreateDatabase createDatabase =new CreateDatabase(context);
        database= createDatabase.open();
    }
    public int themnhanvien(NhanVienDTO nhanVienDTO){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_NHANVIEN_TEDN,nhanVienDTO.getTENDN());
        values.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        values.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        values.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        values.put(CreateDatabase.TB_NHANVIEN_CMND,nhanVienDTO.getCMND());
        values.put(CreateDatabase.TB_NHANVIEN_MAQUYEN,nhanVienDTO.getMAQUYEN());
        int kiemtra= (int) database.insert(CreateDatabase.TB_NHANVIEN,null,values);
        return  kiemtra;
    }

    public ArrayList<NhanVienDTO> LayDanhSachNhanVien(){
        ArrayList<NhanVienDTO> nhanVienDTOs=new ArrayList<>();
        String sql=" select * from " +CreateDatabase.TB_NHANVIEN ;
        Cursor cursor=database.rawQuery(sql,null);
        while (cursor.moveToNext()){
            NhanVienDTO nhanVienDTO=new NhanVienDTO();
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANV)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_TEDN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MATKHAU)));
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_NGAYSINH)));
            nhanVienDTO.setCMND(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_CMND)));
            nhanVienDTOs.add(nhanVienDTO);
        }
        return nhanVienDTOs;
    }
    public boolean KiemTraNhanVien(){
        String truyvan="select * from "+ CreateDatabase.TB_NHANVIEN;
        Cursor cursor=database.rawQuery(truyvan,null);
        if(cursor.getCount()!=0){
            return true;
        }
        return false;
    }

    public  int KiemTraQuyen(int manv) {
        int maquyen=0;
        String sql=" select * from " +CreateDatabase.TB_NHANVIEN+ " where " + CreateDatabase.TB_NHANVIEN_MANV + " = " +manv;
        Cursor cursor=database.rawQuery(sql,null);
        while (cursor.moveToNext()){
            maquyen=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MAQUYEN));
        }
        return maquyen;
    }
    public int KiemTraDangNhap(String ten, String matkhau){
        int manv=0;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TEDN + " = '" + ten
                + "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + matkhau + "'";
        Cursor cursor=database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manv=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANV));
            cursor.moveToNext();
        }
        return manv;

    }


    public boolean XoaNhanVien(int id){
        int ma= database.delete(CreateDatabase.TB_NHANVIEN,CreateDatabase.TB_NHANVIEN_MANV + " = ?",new String[]{String.valueOf(id)});
        if(ma!=0){
            return true;
        }
        return false;
    }

    public long suanhanvien(NhanVienDTO nhanVienDTO){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_NHANVIEN_TEDN,nhanVienDTO.getTENDN());
        values.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        values.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        values.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        values.put(CreateDatabase.TB_NHANVIEN_CMND,nhanVienDTO.getCMND());
        long kiemtra=database.update(CreateDatabase.TB_NHANVIEN,values,CreateDatabase.TB_NHANVIEN_MANV + " = ?" ,new String[]{String.valueOf(nhanVienDTO.getMANV())});
        return  kiemtra;
    }
}
