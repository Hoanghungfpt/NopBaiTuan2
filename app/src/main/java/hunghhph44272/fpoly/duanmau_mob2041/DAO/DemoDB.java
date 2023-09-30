package hunghhph44272.fpoly.duanmau_mob2041.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import hunghhph44272.fpoly.duanmau_mob2041.database.Dbhelper;

public class DemoDB {
    private SQLiteDatabase db;
    public DemoDB(Context context){
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();

    }
}
