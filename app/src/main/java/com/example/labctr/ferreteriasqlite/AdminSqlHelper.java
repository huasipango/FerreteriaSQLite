package com.example.labctr.ferreteriasqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by labctr on 30/07/18.
 */

public class AdminSqlHelper extends SQLiteOpenHelper {
    public AdminSqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table articulo" +
                "(" +
                "codigo_art primary key," +
                "descripcion_art text," +
                "precio_art real" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
