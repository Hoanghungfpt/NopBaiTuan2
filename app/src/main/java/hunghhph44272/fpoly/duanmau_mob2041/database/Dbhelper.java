package hunghhph44272.fpoly.duanmau_mob2041.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PNLIB";
    private static final int DB_VERSION = 4;

    static final String CREATE_TABLE_THU_THU =
            "CREATE TABLE ThuThu(maTT TEXT PRIMARY KEY," +
                    "hoten TEXT NOT NULL," +
                    "matkhau TEXT NOT NULL," +
                    "loaitaikhoan TEXT)";


    static final String CREATE_TABLE_THANH_VIEN =
            "CREATE TABLE ThanhVien (" +
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "hoTen TEXT NOT NULL," +
                    "namSinh TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_SACH =
            "create table Sach (" +
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSach (maLoai),"+
                    "namXuatBan INTEGER NOT NULL)";

    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon (" +
                    "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "maTT TEXT REFERENCES ThuThu(maTT), " +
                    "maTV INTEGER REFERENCES ThanhVien (maTV), " +
                    "maSach INTEGER REFERENCES Sach (maSach)," +
                    "tienThue INTEGER NOT NULL," +
                    "ngay TEXT NOT NULL," +
                    "traSach INTEGER NOT NULL )";


    public Dbhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TAO BANG THU THƯ
        db.execSQL(CREATE_TABLE_THU_THU);
        //TAO BANG THANH VIEN
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //TAO BANG LOAI SACH
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //TAO BANG SACH
        db.execSQL(CREATE_TABLE_SACH);
        //TAO BANG PHIẾU MƯỢN
        db.execSQL(CREATE_TABLE_PHIEU_MUON);


        db.execSQL("INSERT INTO LoaiSach VALUES (1,'JAVA'),(2,'JAVAScript'),(3,'HTML')");
        db.execSQL("INSERT INTO Sach VALUES (1,'JAVA 1',2500,1,2020),(2,'JAVAScript',3000,1,2022),(3,'HTML cơ bản',2000,1,2023)");
        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Hoàng Huy Hùng','2003'),(2,'Trần Văn A','2000'),(3,'Trần Văn B','2001')");
        db.execSQL("INSERT INTO ThuThu VALUES ('admin','ADMIN Hoàng Huy Hùng','admin','ADMIN'),('thuthu01','Nguyễn Văn B','12345','ThuThu')");
        db.execSQL("INSERT INTO PhieuMuon VALUES (1,'thuthu01',1,1,2500,'10/06/2023', 1),(2,'thuthu02',2,2,3000,'20/07/2023',0),(3,'thuthu03',3,7,2000,'21/08/2003',1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db. execSQL (dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db. execSQL (dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db. execSQL(dropTableLoaiSach) ;
        String dropTableSach = "drop table if exists Sach";
        db. execSQL (dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db. execSQL (dropTablePhieuMuon) ;

        onCreate (db);
    }
}
