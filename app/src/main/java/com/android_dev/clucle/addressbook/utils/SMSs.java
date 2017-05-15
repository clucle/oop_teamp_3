package com.android_dev.clucle.addressbook.utils;

import com.android_dev.clucle.addressbook.entity.SMS;

import java.util.ArrayList;

public class SMSs {
    private static SMSs instance;
    private static ArrayList<SMS> smss = new ArrayList<>();

    protected SMSs() {
        // for instance
    }

    public static SMSs getInstance() {
        if (instance == null) {
            instance = new SMSs();
        }
        return instance;
    }

    public void refresh() { smss.clear(); }

    public void addCall(SMS sms) {
        smss.add(sms);
    }

    public void removeCall(Object sms) {
        smss.remove(sms);
    }

    public ArrayList<SMS> getSMSs() {
        return smss;
    }
}
