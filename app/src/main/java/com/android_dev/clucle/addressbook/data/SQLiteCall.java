package com.android_dev.clucle.addressbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteCall extends SQLiteOpenHelper{
    private String Table = "call";

    public SQLiteCall(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + Table +
                " (type STRING, number STRING, datetime DATETIME DEFAULT CURRENT_TIMESTAMP)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void insert(String type, String number, String datetime) {
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("number", number);
        if (!datetime.equals("defaultTime")) values.put("datetime", datetime);

        this.getWritableDatabase().insert(Table, null, values);
    }

    public void delete (String type, String number, String datetime) {
        this.getWritableDatabase().delete(Table, "type=? AND number=? AND datetime=?",
                new String[]{type, number, datetime});
    }
}
