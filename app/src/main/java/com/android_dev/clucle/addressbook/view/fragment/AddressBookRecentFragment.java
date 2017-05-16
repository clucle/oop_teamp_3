package com.android_dev.clucle.addressbook.view.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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
    @BindView(R.id.spinner_recent) Spinner spinnerRecent;
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

        spinnerRecent.getBackground().setColorFilter(Color.parseColor("#212121"), PorterDuff.Mode.SRC_ATOP);
        spinnerRecent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#212121"));
                ((TextView) parent.getChildAt(0)).setTextSize(16);
                recentPresenter.setViewType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
