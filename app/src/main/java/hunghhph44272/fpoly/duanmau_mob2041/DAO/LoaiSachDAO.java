package hunghhph44272.fpoly.duanmau_mob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duanmau_mob2041.Model.LoaiSach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.ThuThu;
import hunghhph44272.fpoly.duanmau_mob2041.database.Dbhelper;

public class LoaiSachDAO {

    Dbhelper dbhelper;

    public LoaiSachDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    //lấy danh sách loại sách
    public ArrayList<LoaiSach> getDSloaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //Thêm loại sách
    public boolean themLoaiSach(String tenLoai) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);

        long check = sqLiteDatabase.insert("LoaiSach", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    //Xóa loại sách
    //1. Xóa thành công
    //0. Xóa thất bại
    //-1: có sách tồn tại trong thể loại đó
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maLoai = ?",new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LoaiSach","maLoai =?",new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }

    public boolean suaLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loaiSach.getTenLoai());

        long check = sqLiteDatabase.update("LoaiSach",contentValues,"maLoai =?",new String[]{String.valueOf(loaiSach.getMaLoai())});
        if (check == -1)
            return false;
        return true;

    }
}
