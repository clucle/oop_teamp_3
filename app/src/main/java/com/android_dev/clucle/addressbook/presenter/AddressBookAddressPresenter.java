package com.android_dev.clucle.addressbook.presenter;

import android.content.Context;
import android.util.Log;

import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.CheckDelHandler;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.utils.SearchByName;
import com.android_dev.clucle.addressbook.utils.SearchByNumber;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookAddressListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.io.Console;
import java.util.ArrayList;

public class AddressBookAddressPresenter {

    /* Presenter Setting */
    private View view;
    private Context context;

    /* ListView Setting */
    private AddressBookAddressListAdapter adapter;
    private AddressBookAddressListAdapter adapterSearched;

    private ArrayList<AddressBookAddressItem> itemList = new ArrayList<>();
    private ArrayList<AddressBookAddressItem> itemSearchedList = new ArrayList<>();
    //private ArrayList<AddressBookAddressItem> checkedList = new ArrayList<>();

    /* init var */
    private Boolean state_del = false;
    private Boolean state_search = false;
    private String searchedText = "";


    /* View Method */
    public interface View {
        void showSearchedItem(Boolean isSearched);
        void showDeleteItem(Boolean isStateDel);
    }
    public void setContext(Context context) {
        this.context = context;
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
    public ArrayList<AddressBookAddressItem> getItemList() { return itemList; }
    public ArrayList<AddressBookAddressItem> getItemSearchedList() { return itemSearchedList; }


    /* Management Data */
    private void loadItem() {
        itemList.clear();
        ArrayList<Person> persons = Persons.getInstance().getPersons();
        for (int index_person = 0; index_person < persons.size(); index_person++) {
            addItem(persons.get(index_person).getnImg(), persons.get(index_person).getsName());
        }
    }

    private void loadSearchedItem(String searchedText) {
        ArrayList<Person> persons = Persons.getInstance().getPersons();
        itemSearchedList.clear();
        this.searchedText = searchedText;
        for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
            if (SearchByName.search(persons.get(iSearch).getsName(), searchedText))
                addSearchedItem(persons.get(iSearch).getnImg(), persons.get(iSearch).getsName());
        }
        if (searchedText.substring(0, 1).equals("0")) {
            searchedText = searchedText.substring(1);
            for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (persons.get(iSearch).getsNumber().equals("")) continue;
                if (SearchByNumber.search(persons.get(iSearch).getsNumber(), searchedText))
                    addSearchedItem(persons.get(iSearch).getnImg(), persons.get(iSearch).getsName());
            }
        } else {
            for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (persons.get(iSearch).getsNumber().equals("")) continue;
                if (SearchByNumber.search(searchedText, persons.get(iSearch).getsNumber()))
                    addSearchedItem(persons.get(iSearch).getnImg(), persons.get(iSearch).getsName());
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

            ArrayList<AddressBookAddressItem>checkedList = CheckDelHandler.getInstance().getChckedList();

            for (int i = 0; i < checkedList.size(); i++) {
                int index = itemList.indexOf(checkedList.get(i));
                if (index != -1) {
                    itemList.get(index).setSelected(true);
                }
            }
            state_search = false;
            adapter.notifyDataSetChanged();
            view.showSearchedItem(false);

        } else {
            loadSearchedItem(nonBlankText);
            adapterSearched.setCheckMode(state_del);

            ArrayList<AddressBookAddressItem>checkedList = CheckDelHandler.getInstance().getChckedList();
            for (int i = 0; i < checkedList.size(); i++) {
                int index = itemSearchedList.indexOf(checkedList.get(i));
                if (index != -1) {
                    itemSearchedList.get(index).setSelected(true);
                }
            }
            state_search = true;
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
        if (!state_del) {
            ArrayList<AddressBookAddressItem>checkedList = CheckDelHandler.getInstance().getChckedList();
            checkedList.clear();
        }
        setCheckMode(isStateRemove);
    }
    public Boolean getStateRemoveAddress() {
        return state_del;
    }
    public Boolean getStateSearch() {
        return state_search;
    }

    public void confirmDel() {
        ArrayList<AddressBookAddressItem>checkedList = CheckDelHandler.getInstance().getChckedList();
        AddressBookAddressItem checkedItem;
        for (int i = 0; i < checkedList.size(); i++) {
            checkedItem = checkedList.get(i);
            itemList.remove(checkedItem);
            itemSearchedList.remove(checkedItem);
            Persons.getInstance().removePerson(checkedItem.getShowText());

            SQLiteAddress DB = new SQLiteAddress(context, "addressBookPersonTest.db", null, 4);
            DB.delete(checkedItem.getShowText());

        }
        AddressBookRecentPresenter.refresh();
    }

    public void savePerson() {
        loadItem();
        if (!searchedText.equals("")) loadSearchedItem(searchedText);

        adapter.notifyDataSetChanged();
        adapterSearched.notifyDataSetChanged();

    }
}
