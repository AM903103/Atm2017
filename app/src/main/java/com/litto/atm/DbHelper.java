package com.litto.atm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/11/9.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        this(context, "expense.db", null, DB_VERSION);
    }

    private DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table exp ( _id integer primary key, " +
                "cdate datetime not null, " +
                "info varchar, " +
                "amount integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
