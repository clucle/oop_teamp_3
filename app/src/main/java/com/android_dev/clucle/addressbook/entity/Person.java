package com.android_dev.clucle.addressbook.entity;

public class Person {
    private String sName;
    private String sNumber;
    private String sClub;
    private String sEmail;

    public Person(String name, String number, String club, String email) {
        sName = name;
        sNumber = number;
        sClub = club;
        sEmail = email;
    }

    @Override
    public String toString() {
        return sName;
    }

    public String getsName() { return sName; }
    public String getsNumber() { return sName; }
    public String getsClub() { return sName; }
    public String getsEmail() { return sName; }

}
