package com.android_dev.clucle.addressbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.presenter.AddressBookAddressPresenter;


public class AddressBookAddressFragment extends Fragment implements AddressBookAddressPresenter.View{

    private ListView listView;
    private AddressBookAddressPresenter addressPresenter;


    public AddressBookAddressFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout)
                inflater.inflate(R.layout.fragment_addressbook_address, container, false);

        addressPresenter = new AddressBookAddressPresenter(this);

        listView = (ListView) layout.findViewById(R.id.listview_address);
        listView.setAdapter(addressPresenter.getAdapter());

        addressPresenter.addItem(1, "AA");

        //adapter.addItem(2, "BB");
        //adapter.removeItem(1);

        return layout;
    }
}
