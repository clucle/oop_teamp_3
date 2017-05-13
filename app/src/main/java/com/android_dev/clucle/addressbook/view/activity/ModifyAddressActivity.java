package com.android_dev.clucle.addressbook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.data.SQLiteAddress;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.BackPressCloseHandler;
import com.android_dev.clucle.addressbook.utils.Persons;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyAddressActivity extends AppCompatActivity {
    @BindView(R.id.imageButton_character) ImageButton imageButtonCharacter;
    @BindView(R.id.editText_add_modify_name) EditText editTextName;
    @BindView(R.id.editText_add_modify_number) EditText editTextNumber;
    @BindView(R.id.editText_add_modify_club) EditText editTextClub;
    @BindView(R.id.editText_add_modify_email) EditText editTextEmail;

    private int identifyCharacter;
    private BackPressCloseHandler backPressCloseHandler;
    private Intent intent;
    private String myName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);


        backPressCloseHandler = new BackPressCloseHandler(this);
        intent = getIntent();

        identifyCharacter = Integer.parseInt(intent.getStringExtra("nImg"));
        if (identifyCharacter == 1) {
            imageButtonCharacter.setImageResource(R.drawable.img_btn_pink_dog);
        } else {
            imageButtonCharacter.setImageResource(R.drawable.img_btn_blue_dog);
        }
        myName = intent.getStringExtra("sName");
        editTextName.setText(intent.getStringExtra("sName"));

        String number = intent.getStringExtra("sNumber");
        if (number.substring(0, 2).equals("10")) editTextNumber.setText("0" + number);
        else editTextNumber.setText(number);

        editTextClub.setText(intent.getStringExtra("sClub"));
        editTextEmail.setText(intent.getStringExtra("sEmail"));

    }

    @OnClick(R.id.imageButton_character)
    public void onClickCharacter() {
        if (identifyCharacter == 1) {
            imageButtonCharacter.setImageResource(R.drawable.img_btn_blue_dog);
            identifyCharacter = 2;
        } else {
            imageButtonCharacter.setImageResource(R.drawable.img_btn_pink_dog);
            identifyCharacter = 1;
        }
    }

    @OnClick(R.id.text_save)
    public void onClickSave() {
        String name = editTextName.getText().toString().trim();
        String number = editTextNumber.getText().toString().trim();
        String club = editTextClub.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "이름은 공백으로 저장 할 수 없습니다.", Toast.LENGTH_LONG).show();
            return ;
        }
        Person newPerson = new Person(identifyCharacter, name, number, club, email);
        if (Persons.getInstance().getPersons().indexOf(newPerson) == -1 || name.equals(myName)) {

            // Add DataBase
            SQLiteAddress DB = new SQLiteAddress(getApplicationContext(), "addressBookPersonTest.db", null, 4);
            // 기존 DB에서 수정 전 제거
            DB.delete(myName);
            // DB에 수정 후 데이터 추가
            DB.insert(identifyCharacter, name, number, club, email);

            // Del Local Data
            Persons.getInstance().removePerson(myName);
            // Add Local Data
            Persons.getInstance().addPerson(newPerson);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nImg", Integer.toString(identifyCharacter));
            resultIntent.putExtra("sName", name);
            resultIntent.putExtra("sNumber", number);
            resultIntent.putExtra("sClub", club);
            resultIntent.putExtra("sEmail", email);

            setResult(RESULT_OK, resultIntent);

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "이미 저장되어 있는 이름입니다.", Toast.LENGTH_LONG).show();
            return;
        }

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
