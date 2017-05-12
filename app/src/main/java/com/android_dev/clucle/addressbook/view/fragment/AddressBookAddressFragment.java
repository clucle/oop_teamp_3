package com.android_dev.clucle.addressbook.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.presenter.AddressBookAddressPresenter;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.view.activity.AddAddressActivity;
import com.android_dev.clucle.addressbook.view.activity.ModifyAddressActivity;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;


public class AddressBookAddressFragment extends Fragment implements AddressBookAddressPresenter.View{

    private Unbinder unbinder;
    private AddressBookAddressPresenter addressPresenter;
    @BindView(R.id.listview_address) ListView listView;

    @BindView(R.id.llayout_del_state) LinearLayout llayoutDelState;
    @BindView(R.id.llayout_nondel_state) LinearLayout llayoutNoneDelState;

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
        addressPresenter.setContext(getContext());

        unbinder = ButterKnife.bind(this, view);

        listView.setAdapter(addressPresenter.getAdapter());
        showDeleteItem(false);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh List
        addressPresenter.savePerson();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /* ==================== View Interface ================== */
    @Override
    public void showSearchedItem(Boolean isSearched) {
        if (isSearched) listView.setAdapter(addressPresenter.getAdapterSearched());
        else listView.setAdapter(addressPresenter.getAdapter());
    }
    @Override
    public void showDeleteItem(Boolean isStateDel) {
        if (isStateDel) {
            llayoutNoneDelState.setVisibility(View.GONE);
            llayoutDelState.setVisibility(View.VISIBLE);
        } else {
            llayoutNoneDelState.setVisibility(View.VISIBLE);
            llayoutDelState.setVisibility(View.GONE);
        }
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
                startActivityForResult(new Intent(getActivity(), AddAddressActivity.class), 1);
                break;
            case R.id.btn_del_person:
                addressPresenter.setStateRemoveAddress(true);
                break;
        }
    }
    @OnClick({R.id.btn_confirm_del, R.id.btn_cancel_del})
    public void clickAddressDelBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm_del:
                //pass
                addressPresenter.confirmDel();
                addressPresenter.setStateRemoveAddress(false);
                break;
            case R.id.btn_cancel_del:
                addressPresenter.setStateRemoveAddress(false);
                break;
        }
    }

    @OnItemClick(R.id.listview_address)
    public void clickPerson(AdapterView<?> parent, View view, int position, long id) {
        if(addressPresenter.getStateRemoveAddress()) {
            // Log.d("[click_list]", "is deleteMode");
         } else {
            ArrayList<AddressBookAddressItem> modifyItem;
            if (addressPresenter.getStateSearch()) {
                modifyItem = addressPresenter.getItemSearchedList();
            } else {
                modifyItem = addressPresenter.getItemList();
            }

            // new Activity for modify
            Intent intent = new Intent(getActivity(), ModifyAddressActivity.class);

            Person searchToModifyPerson = new Person(1, modifyItem.get(position).getShowText(),
                    "", "", "");
            int index = Persons.getInstance().getPersons().
                    indexOf(searchToModifyPerson);

            Person modifyPerson = Persons.getInstance().getPersons().get(index);

            intent.putExtra("nImg", Integer.toString(modifyPerson.getnImg()));
            intent.putExtra("sName", modifyPerson.getsName());
            intent.putExtra("sNumber", modifyPerson.getsNumber());
            intent.putExtra("sClub", modifyPerson.getsClub());
            intent.putExtra("sEmail", modifyPerson.getsEmail());

            startActivityForResult(intent, 2);
        }
    }

    @OnItemLongClick(R.id.listview_address)
    public boolean clickLongPerson(View view) {
        if(!addressPresenter.getStateRemoveAddress()) {
            addressPresenter.setStateRemoveAddress(true);
        }
        return true;
    }

}