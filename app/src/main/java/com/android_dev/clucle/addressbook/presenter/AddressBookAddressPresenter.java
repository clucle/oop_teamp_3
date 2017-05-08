package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class AddressBookAddressPresenter {

    /* Presenter Setting */
    private View view;

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

    /* ListView Method */
    public AddressBookAddressListAdapter getAdapter() {
        return adapter;
    }
    public void addItem(int numImg, String text) {
        adapter.addItem(numImg, text);
    }
    public void loadItem() {
        ArrayList<Person> persons = Persons.getInstance().getPersons();
        for (int index_person = 0; index_person < persons.size(); index_person++) {
            addItem(index_person % 10, persons.get(index_person).getsName());
        }
    }

}
