package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;
import android.database.Cursor;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.Persons;

public class AddressBookPresenter{
    private View view;

    public AddressBookPresenter(View view) {
        this.view = view;
        DB = new SQLiteAddress(context, "addressTest.db", null, 4);
    }
    public interface View {

    }

    /* DB Setting */
    private SQLiteAddress DB;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadDB() {

        /* DB.insert("name", "number", "club", "email"); */

        Cursor cursor = DB.getWritableDatabase().rawQuery("SELECT * FROM address", null);
        if (cursor.moveToFirst()) {
            do {
                Persons.getInstance().addPerson(new Person(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
