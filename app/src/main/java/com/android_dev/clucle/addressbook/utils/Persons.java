package com.android_dev.clucle.addressbook.utils;

import com.android_dev.clucle.addressbook.entity.Person;

import java.util.ArrayList;

public class Persons {
    private static Persons instance;
    private static ArrayList<Person> persons = new ArrayList<>();

    protected Persons() {
        // for instance
    }

    public static Persons getInstance() {
        if (instance == null) {
            instance = new Persons();
        }
        return instance;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void delPerson(Person person) {
        persons.remove(person);
    }
}
