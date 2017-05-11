package com.android_dev.clucle.addressbook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.BackPressCloseHandler;
import com.android_dev.clucle.addressbook.utils.Persons;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity {
    @BindView(R.id.imageButton_character) ImageButton imageButtonCharacter;
    @BindView(R.id.editText_add_modify_name) EditText editTextName;
    @BindView(R.id.editText_add_modify_number) EditText editTextNumber;
    @BindView(R.id.editText_add_modify_club) EditText editTextClub;
    @BindView(R.id.editText_add_modify_email) EditText editTextEmail;

    private int identifyCharacter;
    private BackPressCloseHandler backPressCloseHandler;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        identifyCharacter = 1;
        backPressCloseHandler = new BackPressCloseHandler(this);
        intent = getIntent();
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
        String email = editTextName.getText().toString().trim();

        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "이름은 공백으로 저장 할 수 없습니다.", Toast.LENGTH_LONG).show();
            return ;
        }
        Person newPerson = new Person(name, number, club, email);
        if (Persons.getInstance().getPersons().indexOf(newPerson) == -1) {
            intent.putExtra("new_person_name", name);
            intent.putExtra("new_person_number", number);
            intent.putExtra("new_person_club", club);
            intent.putExtra("new_person_email", email);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "이미 저장되어 있는 이름입니다.", Toast.LENGTH_LONG).show();
            return ;
        }

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
