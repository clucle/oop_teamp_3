package com.android_dev.clucle.addressbook.view.item;

public class AddressBookAddressItem {
    private int numImg;
    private String showText;
    private boolean selected = false;
    private boolean isCheckBox = false;

    public void setImgNum(int numImg) {
        this.numImg = numImg;
    }
    public void setShowText(String text) {
        this.showText = text;
    }

    public int getNumImg() {
        return numImg;
    }
    public String getShowText() {
        return showText;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setCheckBox(Boolean isCheckBox) {
        this.isCheckBox = isCheckBox;
    }
    public boolean getCheckBox() {
        return isCheckBox;
    }
}
