package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class AddressBookAddressPresenter {

    /* Presenter Setting */
    private View view;
    private Context context;

    /* DB Setting */
    private SQLiteAddress DB;

    /* ListView Setting */
    private AddressBookAddressListAdapter adapter;
    private ArrayList<AddressBookAddressItem> itemList = new ArrayList<AddressBookAddressItem>();

    /* View Method */
    public interface View {

    }

    /* Presenter Method */
    public AddressBookAddressPresenter(View view) {
        this.view = view;
        adapter = new AddressBookAddressListAdapter(itemList);
    }
    public void setContext(Context context) {
        this.context = context;
        loadDB();
    }

    public void loadDB() {

        DB = new SQLiteAddress(context, "addressTest.db", null, 4);
        //Persons.getInstance().addPerson(new Person(a,a,,sd,as,d);
        /*
        DB.insert("a5", "b2", "c2", "d2");

        Cursor cursor = DB.getWritableDatabase().rawQuery("SELECT * FROM address", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("A", cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();*/
    }




    /* ListView Method */
    public AddressBookAddressListAdapter getAdapter() {
        return adapter;
    }
    public void addItem(int numImg, String text) {
        adapter.addItem(numImg, text);
    }

}
