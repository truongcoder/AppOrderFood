package com.example.pctruong.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pctruong.apporderfood.DTO.LoaiMonAnDTO;
import com.example.pctruong.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class LoaiMonAnDAO {
    SQLiteDatabase database;

    public LoaiMonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();

    }

    public boolean ThemLoaiMonAn(String tenloai) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_LOAIMONAN_TENLOAI, tenloai);
        long kt = database.insert(CreateDatabase.TB_LOAIMONAN, null, values);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<LoaiMonAnDTO>
    loadLoaiMonAn() {
        ArrayList<LoaiMonAnDTO> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + CreateDatabase.TB_LOAIMONAN, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMaLoai(cursor.getInt(0));
            loaiMonAnDTO.setTenLoai(cursor.getString(1));
            list.add(loaiMonAnDTO);
            cursor.moveToNext();
        }
        return list;
    }
    public int Xoa_LoaiMonAn(int maloai){
       return database.delete(CreateDatabase.TB_LOAIMONAN, CreateDatabase.TB_LOAIMONAN_MALOAI + "=?",new String[]{String.valueOf(maloai)}) ;
    }
    public Bitmap load_LoaiMonAn_CoHinh(int maloai) {
       Bitmap bitmap=null;
        String sql = " select * from " + CreateDatabase.TB_MONAN + " where " + CreateDatabase.TB_MONAN_MALOAI+ " = " + maloai + " AND " +CreateDatabase.TB_MONAN_HINHANH+ " != '' order by " +CreateDatabase.TB_MONAN_MAMON + " limit 1";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

             byte [] hinhanh=cursor.getBlob(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH));
             bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
             cursor.moveToNext();


        }
        return bitmap;
    }


}
