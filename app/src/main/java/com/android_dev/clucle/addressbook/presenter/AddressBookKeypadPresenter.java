package com.android_dev.clucle.addressbook.presenter;

import com.android_dev.clucle.addressbook.utils.SearchByNumber;

public class AddressBookKeypadPresenter {
    /* Presenter Setting */
    private View view;

    /* View Method */
    public interface View {
        void showNumber(String text);

        // 검색 안했을 시 빈 view
        void showBlankFindView();
        // 검색시 찾았을 view
        void showFindView();
        // 검색시 못 찾았을 view
        void showNotFindView();
    }

    private String keyText;

    /* Presenter Method */
    public AddressBookKeypadPresenter(View view) {
        this.view = view;
        keyText = "";
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

        findNumber(keyText);
    }

    private void findNumber(String lenText) {
        if (keyText.equals("0")) {
            // pass
        } else {
            /*
            if (lenText.substring(0, 1).equals("0")) {
                searchedText = searchedText.substring(1);
                for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                    if (persons.get(iSearch).getsNumber().equals("")) continue;
                    if (SearchByNumber.search(persons.get(iSearch).getsNumber(), searchedText))
                        addSearchedItem(persons.get(iSearch).getnImg(), persons.get(iSearch).getsName());
                }
            } else {
                for (int iSearch = 0; iSearch < persons.size(); iSearch++) {
                    if (persons.get(iSearch).getsNumber().equals("")) continue;
                    if (SearchByNumber.search(searchedText, persons.get(iSearch).getsNumber()))
                        addSearchedItem(persons.get(iSearch).getnImg(), persons.get(iSearch).getsName());
                }
            }*/
        }

    }
}
