package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;

public class AddressBookPresenter{
    private View view;

    public AddressBookPresenter(View view) {
        this.view = view;
    }
    public interface View {

    }

    /* DB Setting */
    private SQLiteAddress DB;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        loadDB();
    }

    public void loadDB() {

        DB = new SQLiteAddress(context, "addressTest.db", null, 4);
        //Persons.getInstance().addPerson(new Person(a,a,,sd,as,d);

        //DB.insert("a5", "b2", "c2", "d2");

        Cursor cursor = DB.getWritableDatabase().rawQuery("SELECT * FROM address", null);
        if (cursor.moveToFirst()) {
            do {
                //Log.d("A", cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
