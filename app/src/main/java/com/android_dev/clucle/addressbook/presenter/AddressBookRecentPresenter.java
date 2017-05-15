package com.android_dev.clucle.addressbook.presenter;

import com.android_dev.clucle.addressbook.entity.Call;
import com.android_dev.clucle.addressbook.entity.SMS;
import com.android_dev.clucle.addressbook.utils.Calls;
import com.android_dev.clucle.addressbook.utils.SMSs;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookRecentListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookRecentItem;

import java.util.ArrayList;

public class AddressBookRecentPresenter {
    /* Presenter Setting */
    private AddressBookRecentPresenter.View view;

    /* ListView Setting */
    private AddressBookRecentListAdapter adpaterAll;
    private AddressBookRecentListAdapter adapterCall;
    private AddressBookRecentListAdapter adapterSMS;

    private ArrayList<AddressBookRecentItem> itemListAll = new ArrayList<>();
    private ArrayList<AddressBookRecentItem> itemListCall = new ArrayList<>();
    private ArrayList<AddressBookRecentItem> itemListSMS = new ArrayList<>();

    /* View Method */
    public interface View {

    }


    /* Presenter Method */
    public AddressBookRecentPresenter(View view) {
        this.view = view;
        adpaterAll = new AddressBookRecentListAdapter(itemListAll);
        adapterCall = new AddressBookRecentListAdapter(itemListCall);
        adapterSMS = new AddressBookRecentListAdapter(itemListSMS);
        loadItem();
    }

    /* Management Data */
    private void loadItem() {
        itemListAll.clear();
        itemListCall.clear();
        itemListSMS.clear();

        ArrayList<Call> calls = Calls.getInstance().getCalls();
        ArrayList<SMS> smss = SMSs.getInstance().getSMSs();

        for (int index_call = 0; index_call < calls.size(); index_call++) {
            //addItem(persons.get(index_person).getnImg(), persons.get(index_person).getsName());
        }
    }
}
