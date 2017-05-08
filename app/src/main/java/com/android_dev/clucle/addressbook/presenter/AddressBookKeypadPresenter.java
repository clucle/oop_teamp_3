package com.android_dev.clucle.addressbook.presenter;

public class AddressBookKeypadPresenter {
    /* Presenter Setting */
    private View view;

    /* View Method */
    public interface View {

    }

    private String keyText;

    /* Presenter Method */
    public AddressBookKeypadPresenter(View view) {
        this.view = view;
        keyText = "";
    }

    public void addText(String text) {

    }
}
