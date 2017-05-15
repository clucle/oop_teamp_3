package com.android_dev.clucle.addressbook.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android_dev.clucle.addressbook.view.item.AddressBookRecentItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddressBookRecentListAdapter extends BaseAdapter{
    private ArrayList<AddressBookRecentItem> itemList;

    public AddressBookRecentListAdapter(ArrayList<AddressBookRecentItem> itemList) {
        if (itemList == null) {
            this.itemList = new ArrayList<>();
        } else {
            this.itemList = itemList;
        }
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    static class ViewHolder {
        //protected ImageView character;
        //protected TextView text_id;
        //protected CheckBox checkBox;
    }

    public void addItem(int numImg, String kind, String type, String name, String number, String time) {
        AddressBookRecentItem item = new AddressBookRecentItem(numImg, kind, type, name, number, time);

        itemList.add(item);
        sortItem();
    }

    public void sortItem() {
        Comparator<AddressBookRecentItem> order = new Comparator<AddressBookRecentItem>() {
            @Override
            public int compare(AddressBookRecentItem o1, AddressBookRecentItem o2) {
                int ret;
                if (o1.getTime().compareTo(o2.getTime()) < 0)
                    ret = -1;
                else
                    ret = 1;
                return ret;
            }
        };
        Collections.sort(itemList, order);
        notifyDataSetChanged();

    }
}
