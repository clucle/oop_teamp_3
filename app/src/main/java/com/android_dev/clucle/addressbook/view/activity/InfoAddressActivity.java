package com.android_dev.clucle.addressbook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android_dev.clucle.addressbook.R;

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

}
