package com.android_dev.clucle.addressbook.presenter;

import com.android_dev.clucle.addressbook.entity.Call;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.entity.SMS;
import com.android_dev.clucle.addressbook.utils.Calls;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.utils.SMSs;
import com.android_dev.clucle.addressbook.utils.SearchByNumber;
import com.android_dev.clucle.addressbook.view.adapter.AddressBookRecentListAdapter;
import com.android_dev.clucle.addressbook.view.item.AddressBookRecentItem;

import java.util.ArrayList;

public class AddressBookRecentPresenter {
    /* Presenter Setting */
    private AddressBookRecentPresenter.View view;

    /* ListView Setting */
    private AddressBookRecentListAdapter adapterAll;
    private AddressBookRecentListAdapter adapterCall;
    private AddressBookRecentListAdapter adapterSMS;

    private ArrayList<AddressBookRecentItem> itemListAll = new ArrayList<>();
    private ArrayList<AddressBookRecentItem> itemListCall = new ArrayList<>();
    private ArrayList<AddressBookRecentItem> itemListSMS = new ArrayList<>();

    /* View Method */
    public interface View {
        public void showAdapter(AddressBookRecentListAdapter adapter);
    }


    /* Presenter Method */
    public AddressBookRecentPresenter(View view) {
        this.view = view;
        adapterAll = new AddressBookRecentListAdapter(itemListAll);
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

        ArrayList<Person> persons = Persons.getInstance().getPersons();

        /* Load Call */
        for (int index_call = 0; index_call < calls.size(); index_call++) {
            String findNumber = calls.get(index_call).getNumber();

            int iSearch;
            for (iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (SearchByNumber.search(persons.get(iSearch).getsName(), findNumber)) {

                    addListToAdapter(adapterAll,
                            persons.get(iSearch).getnImg(),
                            "call",
                            calls.get(index_call).getType(),
                            persons.get(iSearch).getsName(),
                            findNumber,
                            calls.get(index_call).getTime());

                    addListToAdapter(adapterCall,
                            persons.get(iSearch).getnImg(),
                            "call",
                            calls.get(index_call).getType(),
                            persons.get(iSearch).getsName(),
                            findNumber,
                            calls.get(index_call).getTime());
                    break;
                }
            }
            if (iSearch == persons.size()) {
                addListToAdapter(adapterAll,
                        3,
                        "call",
                        calls.get(index_call).getType(),
                        findNumber,
                        findNumber,
                        calls.get(index_call).getTime());

                addListToAdapter(adapterCall,
                        3,
                        "call",
                        calls.get(index_call).getType(),
                        findNumber,
                        findNumber,
                        calls.get(index_call).getTime());
                break;
            }
        }

        /* Load SMS */
        for (int index_sms = 0; index_sms < smss.size(); index_sms++) {
            String findNumber = smss.get(index_sms).getNumber();

            int iSearch;
            for (iSearch = 0; iSearch < persons.size(); iSearch++) {
                if (SearchByNumber.search(persons.get(iSearch).getsName(), findNumber)) {

                    addListToAdapter(adapterAll,
                            persons.get(iSearch).getnImg(),
                            "sms",
                            smss.get(index_sms).getType(),
                            persons.get(iSearch).getsName(),
                            findNumber,
                            smss.get(index_sms).getTime());

                    addListToAdapter(adapterSMS,
                            persons.get(iSearch).getnImg(),
                            "sms",
                            smss.get(index_sms).getType(),
                            persons.get(iSearch).getsName(),
                            findNumber,
                            smss.get(index_sms).getTime());
                    break;
                }
            }
            if (iSearch == persons.size()) {
                addListToAdapter(adapterAll,
                        3,
                        "sms",
                        smss.get(index_sms).getType(),
                        findNumber,
                        findNumber,
                        smss.get(index_sms).getTime());

                addListToAdapter(adapterSMS,
                        3,
                        "sms",
                        smss.get(index_sms).getType(),
                        findNumber,
                        findNumber,
                        smss.get(index_sms).getTime());
                break;
            }
        }
    }

    /* getter */
    public AddressBookRecentListAdapter getAdapterAll() {
        return adapterAll;
    }

    /* Fragment Method */
    public void showAll() {
        view.showAdapter(adapterAll);
    }

    /* Presenter Logic */
    public void addListToAdapter(AddressBookRecentListAdapter adapter,
                                 int nImg, String kind, String type,
                                 String name, String number, String time) {
        adapter.addItem(nImg, kind, type, name, number, time);

    }
}
