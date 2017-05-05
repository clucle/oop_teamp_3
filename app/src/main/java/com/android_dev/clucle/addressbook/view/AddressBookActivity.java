package com.android_dev.clucle.addressbook.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.view.SplashActivity;

public class AddressBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        /* Show Logo Screen */
        startActivity(new Intent(this, SplashActivity.class));

    }
}
