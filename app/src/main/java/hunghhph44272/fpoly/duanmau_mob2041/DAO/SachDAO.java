package hunghhph44272.fpoly.duanmau_mob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duanmau_mob2041.Model.LoaiSach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.Sach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.ThanhVien;
import hunghhph44272.fpoly.duanmau_mob2041.database.Dbhelper;

public class SachDAO {
    Dbhelper dbhelper;

    public SachDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    //lấy toàn bộ đầu sách có trong thư viện
    public ArrayList<Sach> getDsDauSach() {
        ArrayList<Sach> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.maSach,sc.tenSach,sc.giaThue,sc.maLoai,lo.tenLoai,sc.namXuatBan " +
                "FROM Sach sc,LoaiSach lo WHERE sc.maLoai = lo.maLoai", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSachMoi(String tensach, int giatien, int maloai, int namXuatBan){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",tensach);
        contentValues.put("giaThue",giatien);
        contentValues.put("maLoai",maloai);
        contentValues.put("namXuatBan",namXuatBan);

        long check = sqLiteDatabase.insert("Sach",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public boolean capnhatThongTinSach(int masach,String tensach,int giathue, int maloai,int namXuatBan){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",tensach);
        contentValues.put("giaThue",giathue);
        contentValues.put("maLoai",maloai);
        contentValues.put("namXuatBan",namXuatBan);
        long check = sqLiteDatabase.update("Sach",contentValues,"maSach = ?",new String[]{String.valueOf(masach)});
        if (check == -1)
            return false;
        return true;

    }

    public int xoaSach(int masach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maSach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("Sach","maSach = ?",new String[]{String.valueOf(masach)});
        if (check == -1)
            return 0;
        return 1;
    }
}
