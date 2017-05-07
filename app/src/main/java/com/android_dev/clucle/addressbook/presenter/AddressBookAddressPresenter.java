package com.android_dev.clucle.addressbook.presenter;

import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class AddressBookAddressPresenter {

    private View view;
    public interface View {

    }

    private AddressBookAddressListAdapter adapter;

    private ArrayList<AddressBookAddressItem> itemList = new ArrayList<AddressBookAddressItem>();

    public AddressBookAddressPresenter(View view) {
        this.view = view;
        adapter = new AddressBookAddressListAdapter(itemList);
    }

    public AddressBookAddressListAdapter getAdapter() {
        return adapter;
    }
    public void addItem(int numImg, String text) {
        adapter.addItem(numImg, text);
    }

}
