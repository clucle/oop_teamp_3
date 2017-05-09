package com.android_dev.clucle.addressbook.presenter;

import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;

public class AddressBookAddressPresenter {

    /* Presenter Setting */
    private View view;

    /* ListView Setting */
    private AddressBookAddressListAdapter adapter;
    private AddressBookAddressListAdapter adapterSearched;

    private ArrayList<AddressBookAddressItem> itemList = new ArrayList<>();
    private ArrayList<AddressBookAddressItem> itemSearchedList = new ArrayList<>();

    /* View Method */
    public interface View {
        void showCheckItemMode(Boolean isCheckMode);
        void showSearchedItem(Boolean isSearched);
    }

    /* Presenter Method */
    public AddressBookAddressPresenter(View view) {
        this.view = view;
        adapter = new AddressBookAddressListAdapter(itemList);
        adapterSearched = new AddressBookAddressListAdapter(itemSearchedList);
    }

    /* ListView Method */
    public AddressBookAddressListAdapter getAdapter() {
        return adapter;
    }
    public AddressBookAddressListAdapter getAdapterSearched() {
        return adapterSearched;
    }

    /* Management Data */
    public void loadItem() {
        ArrayList<Person> persons = Persons.getInstance().getPersons();
        for (int index_person = 0; index_person < persons.size(); index_person++) {
            addItem(index_person % 10, persons.get(index_person).getsName());
        }
    }

    public void loadSearchedItem() {
        ArrayList<Person> persons = Persons.getInstance().getPersons();
    }

    public void addItem(int numImg, String text) {
        adapter.addItem(numImg, text);
    }

    public void readyRemoveItem(Boolean isCheckMode) {
        view.showCheckItemMode(isCheckMode);
        adapter.notifyDataSetChanged();
    }


    public void addSearchedItem(int numImg, String text) {
        adapterSearched.addItem(numImg, text);
    }

    /* Logic */
    public void searchAddress(String text) {
        String nonBlankText = text;
        nonBlankText = nonBlankText.replaceAll(" ", "");
        if (nonBlankText == "") {
            adapter.notifyDataSetChanged();
            view.showSearchedItem(false);
        } else {
            itemSearchedList.clear();
            loadSearchedItem();
            adapterSearched.notifyDataSetChanged();
            view.showSearchedItem(true);
        }
    }

}
