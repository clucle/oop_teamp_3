package com.android_dev.clucle.addressbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAddress extends SQLiteOpenHelper{
    private String Table = "persontest";

    public SQLiteAddress(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + Table +
                " (img INTEGER, name STRING PRIMARY KEY, number STRING, club STRING, email STRING)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void insert(int img, String name, String number, String group, String email) {
        ContentValues values = new ContentValues();
        values.put("img", img);
        values.put("name", name);
        values.put("number", number);
        values.put("club", group);
        values.put("email", email);
        this.getWritableDatabase().insert(Table, null, values);
    }

    public void delete (String name) {
        this.getWritableDatabase().delete(Table, "name=?", new String[]{name});
    }
}
