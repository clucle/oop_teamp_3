package com.android_dev.clucle.addressbook.view.adapter;

import com.android_dev.clucle.addressbook.view.item.AddressBookRecentItem;

import java.util.ArrayList;

public class AddressBookRecentListAdapter {
    private ArrayList<AddressBookRecentItem> itemList;

    public AddressBookRecentListAdapter(ArrayList<AddressBookRecentItem> itemList) {
        if (itemList == null) {
            this.itemList = new ArrayList<>();
        } else {
            this.itemList = itemList;
        }
    }

}
