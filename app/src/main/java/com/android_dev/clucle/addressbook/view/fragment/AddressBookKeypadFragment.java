package com.android_dev.clucle.addressbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android_dev.clucle.addressbook.R;
import com.android_dev.clucle.addressbook.presenter.AddressBookKeypadPresenter;

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
    @BindView(R.id.text_show_number)
    TextView text_show_number;

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showNumber(String text) {
        text_show_number.setText(text);
    }

    @OnClick({R.id.btn_keypad_num1, R.id.btn_keypad_num2, R.id.btn_keypad_num3,
            R.id.btn_keypad_num4, R.id.btn_keypad_num5, R.id.btn_keypad_num6,
            R.id.btn_keypad_num7, R.id.btn_keypad_num8, R.id.btn_keypad_num9,
            R.id.btn_keypad_star, R.id.btn_keypad_num0, R.id.btn_keypad_shap})
    public void onKeyClick(View view) {
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


}
