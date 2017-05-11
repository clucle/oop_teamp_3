package com.android_dev.clucle.addressbook.presenter;

import android.util.Log;

import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.utils.SearchByName;
import com.android_dev.clucle.addressbook.utils.SearchByNumber;
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

    /* init var */
    private Boolean state_del = false;

    /* View Method */
    public interface View {
        void showSearchedItem(Boolean isSearched);
        void showDeleteItem(Boolean isStateDel);
    }

    /* Presenter Method */
    public AddressBookAddressPresenter(View view) {
        this.view = view;
        adapter = new AddressBookAddressListAdapter(itemList);
        adapterSearched = new AddressBookAddressListAdapter(itemSearchedList);
        loadItem();
    }

    /* ListView Method */
    public AddressBookAddressListAdapter getAdapter() {
        return adapter;
    }
    public AddressBookAddressListAdapter getAdapterSearched() {
        return adapterSearched;
    }

    /* Management Data */
    private void loadItem() {
        ArrayList<Person> persons = Persons.getInstance().getPersons();
        for (int index_person = 0; index_person < persons.size(); index_person++) {
            addItem(index_person % 10, persons.get(index_person).getsName());
        }
    }

    private void loadSearchedItem(String searchedText) {
        ArrayList<Person> persons = Persons.getInstance().getPersons();

        for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
            if (SearchByName.search(persons.get(iSearch).getsName(), searchedText))
                addSearchedItem(iSearch % 10, persons.get(iSearch).getsName());
        }
        if (searchedText.substring(0, 1).equals("0")) {
            searchedText = searchedText.substring(1);
            for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (SearchByNumber.search(persons.get(iSearch).getsNumber(), searchedText))
                    addSearchedItem(iSearch % 10, persons.get(iSearch).getsName());
            }
        } else {
            for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (SearchByNumber.search(persons.get(iSearch).getsNumber(), searchedText))
                    addSearchedItem(iSearch % 10, persons.get(iSearch).getsName());
            }
        }
    }

    private void addItem(int numImg, String text) {
        adapter.addItem(numImg, text);
    }

    private void addSearchedItem(int numImg, String text) {
        adapterSearched.addItem(numImg, text);
    }

    /* Logic */
    public void searchAddress(String text) {
        String nonBlankText = text;
        nonBlankText = nonBlankText.replaceAll(" ", "");
        if (nonBlankText.equals("")) {
            adapter.notifyDataSetChanged();
            view.showSearchedItem(false);
        } else {
            itemSearchedList.clear();
            loadSearchedItem(nonBlankText);
            adapterSearched.setCheckMode(state_del);

            adapterSearched.notifyDataSetChanged();
            view.showSearchedItem(true);
        }
    }

    private void setCheckMode(Boolean isChecked) {
        adapter.setCheckMode(isChecked);
        adapterSearched.setCheckMode(isChecked);
        view.showDeleteItem(isChecked);
    }

    /* Fragment 에서 부르는 Method */
    public void setStateRemoveAddress(Boolean isStateRemove) {
        state_del = isStateRemove;
        setCheckMode(isStateRemove);
    }
}
