package com.android_dev.clucle.addressbook.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.item.AddressBookAddressItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddressBookAddressListAdapter extends BaseAdapter {
    private ArrayList<AddressBookAddressItem> addressItemList;

    private Boolean checkMode = false;

    public AddressBookAddressListAdapter(ArrayList<AddressBookAddressItem> itemList) {
        if (itemList == null) {
            addressItemList = new ArrayList<>();
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
        final Context context = parent.getContext();

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_address_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.text_id = (TextView) convertView.findViewById(R.id.text_id_address);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox_address);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        int getPosition = (Integer) v.getTag();
                        addressItemList.get(getPosition).setSelected(true);
                    } else {
                        int getPosition = (Integer) v.getTag();
                        addressItemList.get(getPosition).setSelected(false);
                    }
                }
            });

            viewHolder.checkBox.setVisibility(View.GONE);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.text_id_address, viewHolder.text_id);
            convertView.setTag(R.id.checkBox_address, viewHolder.checkBox);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        AddressBookAddressItem addressBookAddressItem = addressItemList.get(position);

        if (addressBookAddressItem.getCheckBox())
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        else
            viewHolder.checkBox.setVisibility(View.GONE);

        viewHolder.checkBox.setTag(position);
        viewHolder.text_id.setText(addressBookAddressItem.getShowText());
        viewHolder.checkBox.setChecked(addressBookAddressItem.isSelected());

        return convertView;
    }

    static class ViewHolder {
        protected TextView text_id;
        protected CheckBox checkBox;
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

    public void setCheckMode(Boolean isCheckMode) {
        checkMode = isCheckMode;
        for (int getPosition = 0; getPosition < addressItemList.size(); getPosition++) {
            addressItemList.get(getPosition).setCheckBox(checkMode);
        }
        notifyDataSetChanged();
    }

    public Boolean getCheckMode() { return checkMode; }

    public ArrayList<AddressBookAddressItem> getItemList() {
        return addressItemList;
    }
}
