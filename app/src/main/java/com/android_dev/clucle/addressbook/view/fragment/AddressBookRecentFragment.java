package com.android_dev.clucle.addressbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.presenter.AddressBookRecentPresenter;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookRecentListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddressBookRecentFragment extends Fragment implements AddressBookRecentPresenter.View{

    private Unbinder unbinder;
    private AddressBookRecentPresenter recentPresenter;
    @BindView(R.id.listview_recent) ListView listViewRecent;

    public AddressBookRecentFragment() {
        super();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addressbook_recent, container, false);

        recentPresenter = new AddressBookRecentPresenter(this);

        unbinder = ButterKnife.bind(this, view);

        recentPresenter.showAll();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /* View Interface */
    @Override
    public void showAdapter(AddressBookRecentListAdapter adapter) {
        listViewRecent.setAdapter(adapter);
    }

}
