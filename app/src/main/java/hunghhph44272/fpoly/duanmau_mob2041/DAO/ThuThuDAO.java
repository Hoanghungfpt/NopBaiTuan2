package hunghhph44272.fpoly.duanmau_mob2041.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duanmau_mob2041.Model.ThanhVien;
import hunghhph44272.fpoly.duanmau_mob2041.Model.ThuThu;
import hunghhph44272.fpoly.duanmau_mob2041.database.Dbhelper;

public class ThuThuDAO {
    Dbhelper dbhelper;
    SharedPreferences sharedPreferences;
    public ThuThuDAO(Context context) {
        dbhelper = new Dbhelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }

    //đăng nhập
    public boolean checkDangNhap(String matt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("maTT", cursor.getString(0));
            editor.putString("loaitaikhoan",cursor.getString(3));
            editor.putString("hoTen",cursor.getString(1));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public int capNhatMK(String username, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matKhau", newPass);
            long check = sqLiteDatabase.update("ThuThu", contentValues, "maTT =?", new String[]{username});
            if (check == -1) {
                return -1;
            } else {
                return 1;
            }

        }
        return 0;
    }

}
