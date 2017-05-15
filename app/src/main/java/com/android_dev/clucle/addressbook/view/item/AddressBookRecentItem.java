package com.android_dev.clucle.addressbook.view.item;

public class AddressBookRecentItem {
    private int numImg;
    private String kind;
    private String type;
    private String name;
    private String number;
    private String time;

    public AddressBookRecentItem(int numImg, String kind, String type,
                                 String name, String number, String time) {
        this.numImg = numImg;
        this.kind = kind;
        this.type = type;
        this.name = name;
        this.number = number;
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        AddressBookRecentItem itemObj = (AddressBookRecentItem) obj;

        if (numImg != itemObj.getNumImg()) return false;
        if (!kind.equals(itemObj.getKind())) return false;
        if (!type.equals(itemObj.getType())) return false;
        if (!name.equals(itemObj.getName())) return false;
        if (!number.equals(itemObj.getNumber())) return false;
        if (!time.equals(itemObj.getTime())) return false;

        return true;
    }

    public int getNumImg() {
        return numImg;
    }
    public String getKind() {
        return kind;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getTime() {
        return time;
    }
}
