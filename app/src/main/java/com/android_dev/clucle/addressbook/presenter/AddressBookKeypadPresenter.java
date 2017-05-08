package com.android_dev.clucle.addressbook.presenter;

public class AddressBookKeypadPresenter {
    /* Presenter Setting */
    private View view;

    /* View Method */
    public interface View {
        void showNumber(String text);
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

    }
}
