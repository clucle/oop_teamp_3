package com.android_dev.clucle.addressbook.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.data.SQLiteCall;
import com.android_dev.clucle.addressbook.entity.Call;
import com.android_dev.clucle.addressbook.presenter.AddressBookRecentPresenter;
import com.android_dev.clucle.addressbook.utils.Calls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoAddressActivity extends AppCompatActivity {

    @BindView(R.id.image_info) ImageView characterImg;
    @BindView(R.id.text_info_name) TextView infoName;
    @BindView(R.id.text_info_number) TextView infoNumber;
    @BindView(R.id.text_info_club) TextView infoClub;
    @BindView(R.id.text_info_email) TextView infoEmail;

    int nImg;
    String sName;
    String sNumber;
    String sClub;
    String sEmail;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_address);
        ButterKnife.bind(this);


        intent = getIntent();
        nImg = Integer.parseInt(intent.getStringExtra("nImg"));
        if (nImg == 1) {
            characterImg.setImageResource(R.drawable.img_btn_pink_dog);
        } else {
            characterImg.setImageResource(R.drawable.img_btn_blue_dog);
        }


        sName = intent.getStringExtra("sName");
        String number = intent.getStringExtra("sNumber");
        if (number.substring(0, 2).equals("10")) sNumber = "0" + number;
        else  sNumber = number;
        sClub = intent.getStringExtra("sClub");
        sEmail = intent.getStringExtra("sEmail");

        infoName.setText(sName);
        infoNumber.setText(sNumber);
        infoClub.setText(sClub);
        infoEmail.setText(sEmail);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        nImg = Integer.parseInt(data.getStringExtra("nImg"));
        if (nImg == 1) {
            characterImg.setImageResource(R.drawable.img_btn_pink_dog);
        } else {
            characterImg.setImageResource(R.drawable.img_btn_blue_dog);
        }

        sName = data.getStringExtra("sName");
        String number = data.getStringExtra("sNumber");
        if (number.substring(0, 2).equals("10")) sNumber = "0" + number;
        else  sNumber = number;
        sClub = data.getStringExtra("sClub");
        sEmail = data.getStringExtra("sEmail");

        infoName.setText(sName);
        infoNumber.setText(sNumber);
        infoClub.setText(sClub);
        infoEmail.setText(sEmail);
    }

    @OnClick(R.id.text_modify_person)
    public void onClickModify() {

        Intent intent = new Intent(this, ModifyAddressActivity.class);

        intent.putExtra("nImg", Integer.toString(nImg));
        intent.putExtra("sName", sName);
        intent.putExtra("sNumber", sNumber);
        intent.putExtra("sClub", sClub);
        intent.putExtra("sEmail", sEmail);

        startActivityForResult(intent, 1);
    }

    @OnClick({R.id.btn_info_call, R.id.btn_info_sms})
    public void onClickSendCommunicate(View view) {
        switch (view.getId()) {
            case R.id.btn_info_call:

                String callNum = sNumber;
                while (callNum.length() > 0 && callNum.substring(0,1).equals("0")) {
                    callNum = callNum.substring(1);
                }
                SQLiteCall DBCall = new SQLiteCall(getApplicationContext(), "addressBookCallsaTest.db", null, 4);
                DBCall.insert("send", callNum, "defaultTime");

                Cursor cursor = DBCall.getWritableDatabase().rawQuery("SELECT * FROM calltest ORDER BY datetime", null);
                if (cursor.getCount() > 0) {
                    cursor.moveToLast();
                    Toast.makeText(getApplicationContext(), "Call to" + cursor.getString(1), Toast.LENGTH_LONG).show();
                    Calls.getInstance().addCall(new Call(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                    AddressBookRecentPresenter.refresh();
                }
                cursor.close();

                AddressBookRecentPresenter.refresh();

                break;

            case R.id.btn_info_sms:
                //pass
                break;
        }
    }
}
