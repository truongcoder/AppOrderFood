package com.example.pctruong.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pctruong.apporderfood.DTO.ChiTietGoiMonDTO;
import com.example.pctruong.apporderfood.DTO.GoiMonDTO;
import com.example.pctruong.apporderfood.DTO.ThanhToanDTO;
import com.example.pctruong.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;

/**
 * Created by PVTruong on 07/03/2017.
 */

public class GoiMonDAO {
    SQLiteDatabase database;
    public GoiMonDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemGoiMonBanAn(GoiMonDTO dto) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_GOIMON_MABAN, dto.getMaBan());
        values.put(CreateDatabase.TB_GOIMON_MANV, dto.getMaNV());
        values.put(CreateDatabase.TB_GOIMON_NGAYGOI, dto.getNgayGoi());
        values.put(CreateDatabase.TB_GOIMON_TINHTRANG, dto.getTinhTrang());
        long kt = database.insert(CreateDatabase.TB_GOIMON, null, values);
        return kt;
    }

    public int LayMaGoiMonMaBan(int maban, String tinhtrang) {
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_GOIMON + " WHERE " + CreateDatabase.TB_GOIMON_MABAN + " = '" + maban + "' AND "
                + CreateDatabase.TB_GOIMON_TINHTRANG + " = '" + tinhtrang + "'";

        int magoimon = 0;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            magoimon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));

            cursor.moveToNext();
        }

        return magoimon;
    }

    public boolean KiemTraMonAnTonTai(int magoimon, int mamonan) {
        String sql = " select * from " + CreateDatabase.TB_CHITIETGOIMON + " where " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + "= '" + magoimon + " ' and " +
                CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " =  ' " + mamonan + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }

    public int LaySoLuongTheoMaGoiMon(int magoimon, int mamonan) {
        int sl = 0;
        String sql = " select * from " + CreateDatabase.TB_CHITIETGOIMON + " where "
                + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = '" + magoimon +
                "' and " + CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " = '" + mamonan + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        while (!cursor.isAfterLast()) {
            sl = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));
            cursor.moveToNext();
        }
        return sl;
    }

    public boolean CapNhatLaiSoLuong(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG, chiTietGoiMonDTO.getSoLuong());
        int kt = database.update(CreateDatabase.TB_CHITIETGOIMON, values, CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = ?" + chiTietGoiMonDTO.getMaGoiMon() + " and "
                + CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " = " + chiTietGoiMonDTO.getMaMonAn(), null);
        if (kt != 0) {
            return true;
        }
        return false;
    }

    public boolean ThemChiTietGoiMon(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_CHITIETGOIMON_MAGOIMON, chiTietGoiMonDTO.getMaGoiMon());
        values.put(CreateDatabase.TB_CHITIETGOIMON_MAMONAN, chiTietGoiMonDTO.getMaMonAn());
        values.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG, chiTietGoiMonDTO.getSoLuong());
        long kt = database.insert(CreateDatabase.TB_CHITIETGOIMON, null, values);
        if (kt != 0) {
            return true;
        }
        return false;
    }

    public ArrayList<ThanhToanDTO> LayDanhSachMonAnTheoMaGoiMon(int magoimon) {
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " ct," + CreateDatabase.TB_MONAN + " ma WHERE "
                + "ct." + CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " = ma." + CreateDatabase.TB_MONAN_MAMON
                + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = '" + magoimon + "'";

        ArrayList<ThanhToanDTO> thanhToanDTOs = new ArrayList<ThanhToanDTO>();
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG)));
            thanhToanDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            thanhToanDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            thanhToanDTOs.add(thanhToanDTO);
            cursor.moveToNext();
        }

        return thanhToanDTOs;
    }

    public boolean CapNhatTrangThaiGoiMonTheoMaBan(int maban, String tinhtrang) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG, maban);
        long kiemtra = database.update(CreateDatabase.TB_GOIMON, contentValues, CreateDatabase.TB_GOIMON_MABAN + " = '" + maban + "'", null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
}
