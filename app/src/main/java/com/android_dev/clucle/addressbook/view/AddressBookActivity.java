package com.android_dev.clucle.addressbook.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressBookActivity extends AppCompatActivity {

    ViewPager vp_addressbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        /* Use ButterKnife */
        ButterKnife.bind(this);

        /* Show Logo Screen */
        startActivity(new Intent(this, SplashActivity.class));

        Button btn_keypad = (Button)findViewById(R.id.btn_keypad);
        Button btn_recent = (Button)findViewById(R.id.btn_recent);
        Button btn_address = (Button)findViewById(R.id.btn_address);

        vp_addressbook = (ViewPager) findViewById(R.id.vp_addressbook);
        vp_addressbook.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        vp_addressbook.setCurrentItem(0);

        btn_keypad.setOnClickListener(movePageListener);
        btn_recent.setOnClickListener(movePageListener);
        btn_address.setOnClickListener(movePageListener);

        btn_keypad.setTag(0);
        btn_recent.setTag(1);
        btn_address.setTag(2);
    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            vp_addressbook.setCurrentItem(tag);
        }
    };

    private class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new AddressBookKeypadFragment();
                case 1:
                    return new AddressBookRecentFragment();
                case 2:
                    return new AddressBookAddressFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
