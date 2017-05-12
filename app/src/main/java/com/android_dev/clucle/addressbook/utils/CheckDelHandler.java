package com.android_dev.clucle.addressbook.utils;

import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class CheckDelHandler {
    private static CheckDelHandler instance;
    private static ArrayList<AddressBookAddressItem> chckedList = new ArrayList<>();

    protected CheckDelHandler() {
        // for instance
    }

    public static CheckDelHandler getInstance() {
        if (instance == null) {
            instance = new CheckDelHandler();
        }
        return instance;
    }

    public void refresh() { chckedList.clear(); }

    public void addItem(AddressBookAddressItem item) {
        chckedList.add(item);
    }

    public void removeItem(AddressBookAddressItem item) {
        chckedList.remove(item);
    }

    public ArrayList<AddressBookAddressItem> getChckedList() {
        return chckedList;
    }
}
