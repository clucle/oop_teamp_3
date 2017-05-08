package com.android_dev.clucle.addressbook.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddressBookAddressListAdapter extends BaseAdapter {
    private ArrayList<AddressBookAddressItem> addressItemList;

    public AddressBookAddressListAdapter(ArrayList<AddressBookAddressItem> itemList) {
        if (itemList == null) {
            addressItemList = new ArrayList<AddressBookAddressItem>();
        } else {
            addressItemList = itemList;
        }
    }

    @Override
    public int getCount() {
        return addressItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_address_item, parent, false);
        }

        TextView userId = (TextView) convertView.findViewById(R.id.text_id_address);

        AddressBookAddressItem addressBookAddressItem = addressItemList.get(position);

        userId.setText(addressBookAddressItem.getShowText());

        return convertView;
    }

    public void addItem(int numImg, String text) {
        AddressBookAddressItem item = new AddressBookAddressItem();

        item.setImgNum(numImg);
        item.setShowText(text);

        addressItemList.add(item);
        sortItem();
    }

    public void sortItem() {
        Comparator<AddressBookAddressItem> order = new Comparator<AddressBookAddressItem>() {
            @Override
            public int compare(AddressBookAddressItem o1, AddressBookAddressItem o2) {
                int ret;
                if (o1.getShowText().compareTo(o2.getShowText()) < 0)
                    ret = -1;
                else
                    ret = 1;
                return ret;
            }
        };
        Collections.sort(addressItemList, order);
        notifyDataSetChanged();

    }

    public void removeItem(int index) {

        addressItemList.remove(index);

        notifyDataSetChanged();
    }

    public ArrayList<AddressBookAddressItem> getItemList() {
        return addressItemList;
    }
}
