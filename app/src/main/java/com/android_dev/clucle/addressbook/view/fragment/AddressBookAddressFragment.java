package com.android_dev.clucle.addressbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class AddressBookAddressFragment extends Fragment {

    ListView listView;
    AddressBookAddressListAdapter adapter;

    ArrayList<AddressBookAddressItem> itemList = new ArrayList<AddressBookAddressItem>();

    public AddressBookAddressFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new AddressBookAddressListAdapter(itemList);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout)
                inflater.inflate(R.layout.fragment_addressbook_address, container, false);

        listView = (ListView) layout.findViewById(R.id.listview_address);
        listView.setAdapter(adapter);

        adapter.addItem(1, "AA");
        adapter.addItem(2, "BB");

        return layout;
    }
}
