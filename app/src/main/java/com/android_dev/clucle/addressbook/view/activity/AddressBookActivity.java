package com.android_dev.clucle.addressbook.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.fragment.AddressBookAddressFragment;
import com.android_dev.clucle.addressbook.view.fragment.AddressBookKeypadFragment;
import com.android_dev.clucle.addressbook.view.fragment.AddressBookRecentFragment;

import butterknife.ButterKnife;


public class AddressBookActivity extends AppCompatActivity {

    ViewPager vp_addressbook;
    LinearLayout llayout_adressbook_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        /* Use ButterKnife */
        ButterKnife.bind(this);

        /* Show Logo Screen */
        // startActivity(new Intent(this, SplashActivity.class));

        Button btn_keypad = (Button)findViewById(R.id.btn_keypad);
        Button btn_recent = (Button)findViewById(R.id.btn_recent);
        Button btn_address = (Button)findViewById(R.id.btn_address);

        vp_addressbook = (ViewPager) findViewById(R.id.vp_addressbook);
        vp_addressbook.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        vp_addressbook.setOffscreenPageLimit(2);
        vp_addressbook.setCurrentItem(0);

        btn_keypad.setOnClickListener(movePageListener);
        btn_recent.setOnClickListener(movePageListener);
        btn_address.setOnClickListener(movePageListener);

        btn_keypad.setTag(0);
        btn_recent.setTag(1);
        btn_address.setTag(2);
        btn_keypad.setSelected(true);

        llayout_adressbook_btn = (LinearLayout) findViewById(R.id.llayout_adressbook_btn);

        vp_addressbook.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while (i<3)
                {
                    if (position == i) {
                        llayout_adressbook_btn.findViewWithTag(i).setSelected(true);
                    } else {
                        llayout_adressbook_btn.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();

            int i = 0;
            while (i<3)
            {
                if (tag == i) {
                    llayout_adressbook_btn.findViewWithTag(i).setSelected(true);
                } else {
                    llayout_adressbook_btn.findViewWithTag(i).setSelected(false);
                }
                i++;
            }

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
                    AddressBookAddressFragment addressFragment = new AddressBookAddressFragment();
                    addressFragment.onAttach(getApplicationContext());
                    return addressFragment;
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
