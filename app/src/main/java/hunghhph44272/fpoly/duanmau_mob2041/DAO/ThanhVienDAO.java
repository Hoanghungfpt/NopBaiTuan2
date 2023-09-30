package hunghhph44272.fpoly.duanmau_mob2041.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.Model.ThanhVien;
import hunghhph44272.fpoly.duanmau_mob2041.database.Dbhelper;

public class ThanhVienDAO {
    Dbhelper dbhelper;
    public ThanhVienDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<ThanhVien> getDSthanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThanhVien(String hoten, String namsinh){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoten);
        contentValues.put("namSinh",namsinh);

        long check = sqLiteDatabase.insert("ThanhVien",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinTV( int matv,String hoten, String namsinh){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoten);
        contentValues.put("namSinh",namsinh);

        long check = sqLiteDatabase.update("ThanhVien",contentValues,"maTV = ?",new String[]{String.valueOf(matv)});

        if (check == -1)
            return false;
        return true;
    }

    public int xoaThongTinThanhVien(int matv){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maTV = ?", new String[]{String.valueOf(matv)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("ThanhVien","maTV = ?",new String[]{String.valueOf(matv)});

        if (check == -1)
            return 0;
        return 1;
    }
}
