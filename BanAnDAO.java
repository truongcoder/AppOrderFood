package com.example.pctruong.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pctruong.apporderfood.DTO.BanAnDTO;
import com.example.pctruong.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 12/31/2016.
 */

public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context) {
        CreateDatabase createDatabase =new CreateDatabase(context);
        database= createDatabase.open();

    }
    public boolean them_BanAn(String tenban){
        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN ,tenban);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,"false");
        long kt= database.insert(CreateDatabase.TB_BANAN,null,contentValues);
        if(kt!=0){
            return true;
        }
        return false;
    }
    public ArrayList<BanAnDTO> load_DanhSach_BanAn(){
        ArrayList<BanAnDTO> list=new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from "+ CreateDatabase.TB_BANAN,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
               BanAnDTO banAnDTO=new BanAnDTO();
               banAnDTO.setMaBan(cursor.getInt(0));
               banAnDTO.setTenBan(cursor.getString(1));
               list.add(banAnDTO);
               cursor.moveToNext();
        }
        return list;
    }
    public String LayTinhTrangBanTheoma(int maban){
        String tinhtrang="";
        String sql="select * from " + CreateDatabase.TB_BANAN + " where " + CreateDatabase.TB_BANAN_MABAN+ " = '" + maban +"' ";
        Cursor cursor=database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tinhtrang=cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG));
            cursor.moveToNext();

        }
        return tinhtrang;
    }
    public boolean CapNhapLaiTinhTrangban(int maban, String tinhtrang)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,tinhtrang);
        long kt=  database.update(CreateDatabase.TB_BANAN,contentValues, CreateDatabase.TB_BANAN_MABAN + " = '" +maban+ " ' ",null);
        if(kt!=0){
            return true;
        }
        return false;

    }

}
