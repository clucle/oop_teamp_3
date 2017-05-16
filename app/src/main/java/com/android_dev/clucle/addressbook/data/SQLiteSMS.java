package com.android_dev.clucle.addressbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteSMS extends SQLiteOpenHelper{
    private String Table = "sms";

    public SQLiteSMS(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + Table +
                " (type STRING, number STRING, datetime DATETIME DEFAULT CURRENT_TIMESTAMP, content STRING)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void insert(String type, String number, String datetime, String content) {
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("number", number);
        if (!datetime.equals("defaultTime")) values.put("datetime", datetime);
        values.put("content", content);

        this.getWritableDatabase().insert(Table, null, values);
    }

    public void delete(String type, String number, String datetime, String content) {
        this.getWritableDatabase().delete(Table, "type=? AND number=? AND datetime=? AND content=?",
                new String[]{type, number, datetime, content});
    }
}
