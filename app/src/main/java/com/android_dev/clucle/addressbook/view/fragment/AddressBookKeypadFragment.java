package com.android_dev.clucle.addressbook.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.data.SQLiteCall;
import com.android_dev.clucle.addressbook.data.SQLiteSMS;
import com.android_dev.clucle.addressbook.entity.Call;
import com.android_dev.clucle.addressbook.entity.SMS;
import com.android_dev.clucle.addressbook.presenter.AddressBookKeypadPresenter;
import com.android_dev.clucle.addressbook.presenter.AddressBookRecentPresenter;
import com.android_dev.clucle.addressbook.utils.Calls;
import com.android_dev.clucle.addressbook.utils.SMSs;
import com.android_dev.clucle.addressbook.view.activity.AddAddressNumberActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddressBookKeypadFragment extends Fragment implements AddressBookKeypadPresenter.View{

    public AddressBookKeypadFragment() {
        super();
    }

    private Unbinder unbinder;
    private AddressBookKeypadPresenter keypadPresenter;
    @BindView(R.id.text_show_number) TextView text_show_number;

    @BindView(R.id.llayout_keypad_searched) LinearLayout llayoutKeypadSearched;
    @BindView(R.id.llayout_keypad_not_searched) LinearLayout llayoutKeypadNotSearched;

    @BindView(R.id.image_keypad_character) ImageView imageKeypadCharacter;
    @BindView(R.id.text_keypad_name) TextView textKeypadName;
    @BindView(R.id.text_keypad_number) TextView textKeypadNumber;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addressbook_keypad, container, false);
        keypadPresenter = new AddressBookKeypadPresenter(this);
        unbinder = ButterKnife.bind(this, view);
        showBlankFindView();
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh List
        keypadPresenter.setText(keypadPresenter.getNumber());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /* View Interface */
    @Override
    public void showNumber(String text) {
        text_show_number.setText(text);
    }

    @Override
    public void showBlankFindView() {
        llayoutKeypadSearched.setVisibility(View.GONE);
        llayoutKeypadNotSearched.setVisibility(View.GONE);
    }

    @Override
    public void showFindView(int character, String name, String number) {
        llayoutKeypadSearched.setVisibility(View.VISIBLE);
        llayoutKeypadNotSearched.setVisibility(View.GONE);

        if (character == 1)
            imageKeypadCharacter.setImageResource(R.drawable.img_btn_pink_dog);
        else if (character == 2)
            imageKeypadCharacter.setImageResource(R.drawable.img_btn_blue_dog);

        textKeypadName.setText(name);
        textKeypadNumber.setText(number);
    }

    @Override
    public void showNotFindView() {
        llayoutKeypadSearched.setVisibility(View.GONE);
        llayoutKeypadNotSearched.setVisibility(View.VISIBLE);
    }


    /* click keypad */
    @OnClick({R.id.btn_keypad_num1, R.id.btn_keypad_num2, R.id.btn_keypad_num3,
            R.id.btn_keypad_num4, R.id.btn_keypad_num5, R.id.btn_keypad_num6,
            R.id.btn_keypad_num7, R.id.btn_keypad_num8, R.id.btn_keypad_num9,
            R.id.btn_keypad_star, R.id.btn_keypad_num0, R.id.btn_keypad_shap})
    public void clickKey(View view) {
        switch (view.getId()) {
            case R.id.btn_keypad_num1:
                keypadPresenter.addText("1");
                break;
            case R.id.btn_keypad_num2:
                keypadPresenter.addText("2");
                break;
            case R.id.btn_keypad_num3:
                keypadPresenter.addText("3");
                break;
            case R.id.btn_keypad_num4:
                keypadPresenter.addText("4");
                break;
            case R.id.btn_keypad_num5:
                keypadPresenter.addText("5");
                break;
            case R.id.btn_keypad_num6:
                keypadPresenter.addText("6");
                break;
            case R.id.btn_keypad_num7:
                keypadPresenter.addText("7");
                break;
            case R.id.btn_keypad_num8:
                keypadPresenter.addText("8");
                break;
            case R.id.btn_keypad_num9:
                keypadPresenter.addText("9");
                break;
            case R.id.btn_keypad_star:
                keypadPresenter.addText("*");
                break;
            case R.id.btn_keypad_num0:
                keypadPresenter.addText("0");
                break;
            case R.id.btn_keypad_shap:
                keypadPresenter.delText();
                break;
        }
    }
    /* click bottom tool bar */
    @OnClick({R.id.btn_keypad_call, R.id.btn_keypad_sms, R.id.btn_keypad_erase})
    public void clickTool(View view) {
        switch (view.getId()) {
            case R.id.btn_keypad_call:
                if (keypadPresenter.sendCall() != null) {
                    // Add DataBase
                    String number = keypadPresenter.sendCall();
                    SQLiteCall DBCall = new SQLiteCall(getContext(), "addressBookCall.db", null, 4);
                    DBCall.insert("send", number, "defaultTime");

                    Cursor cursor = DBCall.getWritableDatabase().rawQuery("SELECT * FROM call ORDER BY datetime", null);
                    if (cursor.getCount() > 0) {
                        cursor.moveToLast();
                        Toast.makeText(getContext(), "Call to" + cursor.getString(1), Toast.LENGTH_LONG).show();
                        Calls.getInstance().addCall(new Call(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                        AddressBookRecentPresenter.refresh();
                    }
                    cursor.close();

                }
                break;

            case R.id.btn_keypad_sms:
                if (keypadPresenter.sendCall() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("문자 보내기");
                    final EditText input = new EditText(getContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    builder.setPositiveButton("보내기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String number = keypadPresenter.sendCall();
                            SQLiteSMS DBSMS = new SQLiteSMS(getContext(), "addressBookSMS.db", null, 4);
                            DBSMS.insert("send", number, "defaultTime", input.getText().toString());

                            Cursor cursor = DBSMS.getWritableDatabase().rawQuery("SELECT * FROM sms ORDER BY datetime", null);
                            if (cursor.getCount() > 0) {
                                cursor.moveToLast();
                                Toast.makeText(getContext(), "Send to" + cursor.getString(1), Toast.LENGTH_LONG).show();
                                SMSs.getInstance().addSMS(new SMS(cursor.getString(0), cursor.getString(1),
                                        cursor.getString(2), cursor.getString(3)));
                                AddressBookRecentPresenter.refresh();
                            }
                            cursor.close();


                        }
                    });

                    builder.setNegativeButton("취소", null);
                    builder.show();
                }

                break;
            case R.id.btn_keypad_erase:
                keypadPresenter.eraseText();
                break;

        }
    }
    @OnClick(R.id.llayout_keypad_searched)
    public void clickFindPerson(View view) {
        keypadPresenter.setText(textKeypadNumber.getText().toString());
    }

    @OnClick(R.id.llayout_keypad_not_searched)
    public void clickNotFindPerson(View view) {
        Intent intent = new Intent(getActivity(), AddAddressNumberActivity.class);

        intent.putExtra("sNumber", keypadPresenter.getNumber());

        startActivityForResult(intent, 1);
    }



}
