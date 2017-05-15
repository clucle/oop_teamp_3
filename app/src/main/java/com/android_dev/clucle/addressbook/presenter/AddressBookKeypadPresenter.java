package com.android_dev.clucle.addressbook.presenter;

import android.util.Log;

import com.android_dev.clucle.addressbook.entity.Person;
import com.android_dev.clucle.addressbook.utils.Persons;
import com.android_dev.clucle.addressbook.utils.SearchByNumber;

import java.util.ArrayList;

public class AddressBookKeypadPresenter {
    /* Presenter Setting */
    private View view;

    /* View Method */
    public interface View {
        void showNumber(String text);

        // 검색 안했을 시 빈 view
        void showBlankFindView();
        // 검색시 찾았을 view
        void showFindView(int character, String name, String number);
        // 검색시 못 찾았을 view
        void showNotFindView();
    }

    private String keyText;

    /* Presenter Method */
    public AddressBookKeypadPresenter(View view) {
        this.view = view;
        keyText = "";
    }

    public String getNumber() {
        return keyText;
    }
    public void setText(String number) {
        keyText = number;
        setFormNumber();
    }
    public void addText(String text) {
        if (keyText.length() > 10) return ;
        keyText += text;
        setFormNumber();
    }
    public void eraseText() {
        if (keyText.length() > 0) {
            keyText = keyText.substring(0, keyText.length() - 1);
            setFormNumber();
        }
    }
    public void delText() {
        keyText = "";
        setFormNumber();
    }

    public void setFormNumber() {
        int lenText = keyText.length();
        findNumber(keyText);
        if (lenText < 4) {
            view.showNumber(keyText);
            return ;
        }
        if (lenText < 8) {
            view.showNumber(keyText.substring(0,3) + "-" + keyText.substring(3));
            return ;
        }
        view.showNumber(keyText.substring(0,3) + "-" + keyText.substring(3, lenText - 4)
                        + "-" + keyText.substring(lenText - 4));


    }

    private void findNumber(String findText) {
        if (findText.equals("")) {
            view.showBlankFindView();
            return ;
        } else {
            ArrayList<Person> persons = Persons.getInstance().getPersons();

            if (findText.substring(0, 1).equals("0")) {
                for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                    if (persons.get(iSearch).getsNumber().equals("")) continue;
                    if (SearchByNumber.search(persons.get(iSearch).getsNumber(), findText)) {
                        view.showFindView(
                                persons.get(iSearch).getnImg(),
                                persons.get(iSearch).getsName(),
                                persons.get(iSearch).getsNumber()
                        );
                        return ;
                    }
                }

                findText = findText.substring(1);
                for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                    if (persons.get(iSearch).getsNumber().equals("")) continue;
                    if (SearchByNumber.search(persons.get(iSearch).getsNumber(), findText)) {
                        view.showFindView(
                                persons.get(iSearch).getnImg(),
                                persons.get(iSearch).getsName(),
                                "0" + persons.get(iSearch).getsNumber()
                        );
                        return ;
                    }
                }
            } else {
                for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                    if (persons.get(iSearch).getsNumber().equals("")) continue;
                    if (SearchByNumber.search(findText, persons.get(iSearch).getsNumber())){
                        view.showFindView(
                                persons.get(iSearch).getnImg(),
                                persons.get(iSearch).getsName(),
                                persons.get(iSearch).getsNumber()
                        );
                        return ;
                    }
                }
            }
        }
        view.showNotFindView();
    }
}
