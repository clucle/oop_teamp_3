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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        if (!sName.equals(other.getsName())) return false;
        if (!sNumber.equals(other.getsNumber())) return false;
        if (!sClub.equals(other.getsClub())) return false;
        if (!sEmail.equals(other.getsEmail())) return false;

        return true;
    }

    public String getsName() { return sName; }
    public String getsNumber() { return sName; }
    public String getsClub() { return sName; }
    public String getsEmail() { return sName; }

}
