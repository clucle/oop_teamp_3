package com.android_dev.clucle.addressbook.utils;

import com.android_dev.clucle.addressbook.entity.Person;

import java.util.ArrayList;

public class Persons {
    private static Persons instance;
    private static ArrayList<Person> persons = new ArrayList<>();

    protected Persons() {
        //
    }

    public static Persons getInstance() {
        if (instance == null) {
            instance = new Persons();
        }
        return instance;
    }

    public void addPerson(String name, String number, String club, String email) {
        persons.add(new Person(name, number, club, email));
    }

    public void delPerson(String name, String number, String club, String email) {
        persons.remove(new Person(name, number, club, email));
    }
}
