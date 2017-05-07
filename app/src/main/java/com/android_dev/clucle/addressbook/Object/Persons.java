package com.android_dev.clucle.addressbook.Object;

import com.android_dev.clucle.addressbook.entity.Person;

import java.util.ArrayList;

public class Persons {
    private static ArrayList<Person> persons = new ArrayList<>();

    public void addPerson(String name, String number, String club, String email) {
        persons.add(new Person(name, number, club, email));
    }

    public void delPerson(String name, String number, String club, String email) {
        persons.remove(new Person(name, number, club, email));
    }
}
