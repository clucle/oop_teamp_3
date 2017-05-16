package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.data.SQLiteCall;
import com.android_dev.clucle.addressbook.data.SQLiteSMS;
import com.android_dev.clucle.addressbook.entity.Call;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.entity.SMS;
import com.android_dev.clucle.addressbook.utils.Calls;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.utils.SMSs;

public class AddressBookPresenter{
    private View view;

    public AddressBookPresenter(View view) {
        this.view = view;
    }
    public interface View {

    }

    /* DB Setting */
    private SQLiteAddress DBPerson;
    private SQLiteCall DBCall;
    private SQLiteSMS DBSMS;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadDB() {
        loadPerson();
        loadCommunicateLog();
    }

    public void loadPerson() {
        DBPerson = new SQLiteAddress(context, "addressBookPersonTest.db", null, 4);

        /* Test Data */
        /*
        DB.insert(2, "정두진", "01063958996", "club", "email");
        DB.insert(2, "김장현", "01077520788", "", "");
        DB.insert(1, "김지은", "01094042326", "k", "");
        DB.insert(1, "신은정", "01080286134", "club", "email");
        DB.insert(2, "엄준식", "01022577298", "club", "email");
        DB.insert(2, "이규혁", "01092181879", "club", "email");
        DB.insert(2, "임준수", "01040127351", "club", "email");
        DB.insert(1, "김지홍", "01041686298", "", "q");
        DB.insert(2, "박성환", "01083387022", "club", "email");
        DB.insert(1, "정은지", "01084660146", "club", "email");
        DB.insert(2, "정조형", "01090789060", "", "q");
        DB.insert(2, "최승민", "01048173738", "club", "email");*/

        // DB.delete("name");
        Persons.getInstance().refresh();

        Cursor cursor = DBPerson.getWritableDatabase().rawQuery("SELECT * FROM persontest ORDER BY name", null);
        if (cursor.moveToFirst()) {
            do {
                Persons.getInstance().addPerson(new Person(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void loadCommunicateLog() {

        /* Load Call Log */
        DBCall = new SQLiteCall(context, "addressBookCallsaTest.db", null, 4);

        //DBCall.getWritableDatabase().execSQL("DELETE FROM calltest");
        ///* Test Data */
        //DBCall.insert("send", "1012341234", "defaultTime");

        // DB.delete("name");
        Calls.getInstance().refresh();
        Cursor cursor = DBCall.getWritableDatabase().rawQuery("SELECT * FROM calltest ORDER BY datetime", null);
        if (cursor.moveToFirst()) {
            do {
                Calls.getInstance().addCall(new Call(
                        cursor.getString(0),
                        cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        /* Load SMS Log */
        DBSMS = new SQLiteSMS(context, "addressBookSMSTest.db", null, 4);

        //DBCall.getWritableDatabase().execSQL("DELETE FROM calltest");
        ///* Test Data */
        //DBSMS.insert("receive", "1012341234", "defaultTime", "content1");

        // DB.delete("name");
        SMSs.getInstance().refresh();
        cursor = DBSMS.getWritableDatabase().rawQuery("SELECT * FROM smstest ORDER BY datetime", null);
        if (cursor.moveToFirst()) {
            do {
                SMSs.getInstance().addSMS(new SMS(
                        cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
