package com.android_dev.clucle.addressbook.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
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

        final Context context = parent.getContext();

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_recent_item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.imgCharacter = (ImageView) convertView.findViewById(R.id.img_recent_character);
            viewHolder.imgKind = (ImageView) convertView.findViewById(R.id.img_recent_kind);
            viewHolder.imgType = (ImageView) convertView.findViewById(R.id.img_recent_type);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.text_recent_name);
            viewHolder.textNumber = (TextView) convertView.findViewById(R.id.text_recent_number);
            viewHolder.textTime = (TextView) convertView.findViewById(R.id.text_recent_time);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.img_recent_character, viewHolder.imgCharacter);
            convertView.setTag(R.id.img_recent_kind, viewHolder.imgKind);
            convertView.setTag(R.id.img_recent_type, viewHolder.imgType);
            convertView.setTag(R.id.text_recent_name, viewHolder.imgType);
            convertView.setTag(R.id.text_recent_number, viewHolder.textTime);
            convertView.setTag(R.id.text_recent_time, viewHolder.textNumber);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final AddressBookRecentItem recentItem = itemList.get(position);

        int nImg = recentItem.getNumImg();
        if (nImg == 1) viewHolder.imgCharacter.setImageResource(R.drawable.img_btn_pink_dog);
        else if (nImg == 2) viewHolder.imgCharacter.setImageResource(R.drawable.img_btn_blue_dog);
        else viewHolder.imgCharacter.setImageResource(R.drawable.img_btn_gray_dog);

        String kind = recentItem.getKind();
        if (kind.equals("call")) viewHolder.imgKind.setImageResource(R.drawable.img_call);
        if (kind.equals("sms")) viewHolder.imgKind.setImageResource(R.drawable.img_sms);

        String type = recentItem.getType();
        if (type.equals("send")) viewHolder.imgType.setImageResource(R.drawable.img_send_log);
        if (type.equals("receive")) viewHolder.imgType.setImageResource(R.drawable.img_receive_log);

        viewHolder.textName.setText(recentItem.getName());
        viewHolder.textNumber.setText(recentItem.getNumber());
        viewHolder.textTime.setText(recentItem.getTime());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!recentItem.getContent().equals(""))
                    Toast.makeText(context, recentItem.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    static class ViewHolder {
        protected ImageView imgCharacter;
        protected ImageView imgKind;
        protected ImageView imgType;
        protected TextView textName;
        protected TextView textNumber;
        protected TextView textTime;
    }

    public void addItem(int numImg, String kind, String type, String name, String number, String time, String content) {
        AddressBookRecentItem item = new AddressBookRecentItem(numImg, kind, type, name, number, time, content);
        if (item.getNumber().substring(0, 2).equals("10")) item.setNumber("0" + item.getNumber());
        itemList.add(item);

        sortItem();
    }

    public void sortItem() {
        Comparator<AddressBookRecentItem> order = new Comparator<AddressBookRecentItem>() {
            @Override
            public int compare(AddressBookRecentItem o1, AddressBookRecentItem o2) {
                int ret;
                if (o1.getTime().compareTo(o2.getTime()) < 0)
                    ret = 1;
                else
                    ret = -1;
                return ret;
            }
        };
        Collections.sort(itemList, order);
        notifyDataSetChanged();

    }

    public void clearList() {
        itemList.clear();
    }
}
