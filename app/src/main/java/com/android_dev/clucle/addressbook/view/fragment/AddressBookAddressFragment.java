package com.android_dev.clucle.addressbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.presenter.AddressBookAddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;


public class AddressBookAddressFragment extends Fragment implements AddressBookAddressPresenter.View{

    private Unbinder unbinder;
    private AddressBookAddressPresenter addressPresenter;

    @BindView(R.id.listview_address) ListView listView;

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
        View view = inflater.inflate(R.layout.fragment_addressbook_address, container, false);

        addressPresenter = new AddressBookAddressPresenter(this);
        unbinder = ButterKnife.bind(this, view);

        listView.setAdapter(addressPresenter.getAdapter());

        addressPresenter.loadItem();
        addressPresenter.readyRemoveItem(true);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /* ========================= View ===================== */
    @Override
    public void showCheckItemMode(Boolean isCheckMode) {
        //Log.d("[user]","checkItemMode");
        //if (isCheckMode) listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //else listView.setChoiceMode(ListView.CHOICE_MODE_NONE);

    }

    @Override
    public void showSearchedItem(Boolean isSearched) {
        if (isSearched) listView.setAdapter(addressPresenter.getAdapterSearched());
        else listView.setAdapter(addressPresenter.getAdapter());
    }
    /* ==================================================== */

    @OnTextChanged(R.id.editText_address_search)
    protected void handleTextChange(Editable editable) {
        addressPresenter.searchAddress(editable.toString());
    }

    @OnClick({R.id.btn_add_person, R.id.btn_del_person})
    public void clickAddressToolbarBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_add_person:
                // pass
                // Log.d("[dujin]", "add_person");
                break;
            case R.id.btn_del_person:
                // pass
                // Log.d("[dujin]", "del_person");
                break;
        }
    }
}
