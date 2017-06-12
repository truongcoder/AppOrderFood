package com.example.pctruong.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pctruong.apporderfood.DTO.MonAnDTO;
import com.example.pctruong.apporderfood.Database.CreateDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class MonAnDAO {
    SQLiteDatabase database;
    CreateDatabase createDatabase;
    public MonAnDAO(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.open();

    }

    public boolean them_MonAn(MonAnDTO dto) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_MONAN_TENMONAN, dto.getTenMonAn());
        values.put(CreateDatabase.TB_MONAN_MALOAI, dto.getMaLoai());
        values.put(CreateDatabase.TB_MONAN_GIATIEN, dto.getGiaTien());
        Bitmap bitmap=dto.getHinhAnh();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte [] hinhanh=byteArrayOutputStream.toByteArray();
        values.put(CreateDatabase.TB_MONAN_HINHANH,hinhanh);
        long kt = database.insert(CreateDatabase.TB_MONAN, null, values);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<MonAnDTO> Lay_DanhSach_MonAn(int maloai){
        ArrayList<MonAnDTO> arrayList=new ArrayList<>();
        String sql="select * from " + CreateDatabase.TB_MONAN+ " where " + CreateDatabase.TB_MONAN_MALOAI+ " = '" +maloai+ "' " ;
        Cursor cursor=database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO monAnDTO=new MonAnDTO();
            monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            byte [] hinhanh=cursor.getBlob(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH));
            Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
            monAnDTO.setHinhAnh(bitmap);
            arrayList.add(monAnDTO);
            cursor.moveToNext();
        }
        return arrayList;
    }
    public int Sua_MonAn(MonAnDTO dto) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_MONAN_TENMONAN, dto.getTenMonAn());
        values.put(CreateDatabase.TB_MONAN_MALOAI, dto.getMaLoai());
        values.put(CreateDatabase.TB_MONAN_GIATIEN, dto.getGiaTien());
        Bitmap bitmap=dto.getHinhAnh();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte [] hinhanh=byteArrayOutputStream.toByteArray();
        values.put(CreateDatabase.TB_MONAN_HINHANH,hinhanh);
        return database.update(CreateDatabase.TB_MONAN,values, CreateDatabase.TB_MONAN_MAMON + " =? " ,new String[]{String.valueOf(CreateDatabase.TB_MONAN_MAMON)});
    }
    public int XoaMonAn(int mamonan){
        return database.delete(CreateDatabase.TB_MONAN, CreateDatabase.TB_MONAN_MAMON + " =? ",new String[]{String.valueOf(mamonan)});
    }

}
