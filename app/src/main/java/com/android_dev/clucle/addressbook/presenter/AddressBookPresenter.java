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
        DB = new SQLiteAddress(context, "addressTest.db", null, 4);
        /* DB.insert("name", "number", "club", "email"); */
        /* Test Data */
        /*
        DB.insert("정두진", "01063958996", "club", "email");
        DB.insert("김장현", "01077520788", "", "");
        DB.insert("김지은", "01094042326", "k", "");
        DB.insert("신은정", "01080286134", "club", "email");
        DB.insert("엄준식", "01022577298", "club", "email");
        DB.insert("이규혁", "01092181879", "club", "email");
        DB.insert("임준수", "01040127351", "club", "email");
        DB.insert("김지홍", "01041686298", "", "q");
        DB.insert("박성환", "01083387022", "club", "email");
        DB.insert("정은지", "01084660146", "club", "email");
        DB.insert("정조형", "01090789060", "", "q");
        DB.insert("최승민", "01048173738", "club", "email");
        */
        DB.delete("a5");

        Cursor cursor = DB.getWritableDatabase().rawQuery("SELECT * FROM address ORDER BY name", null);
        if (cursor.moveToFirst()) {
            do {
                Persons.getInstance().addPerson(new Person(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
