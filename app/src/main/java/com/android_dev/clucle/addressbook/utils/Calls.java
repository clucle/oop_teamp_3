package com.android_dev.clucle.addressbook.utils;

import com.android_dev.clucle.addressbook.entity.Call;

import java.util.ArrayList;

public class Calls {
    private static Calls instance;
    private static ArrayList<Call> calls = new ArrayList<>();

    protected Calls() {
        // for instance
    }

    public static Calls getInstance() {
        if (instance == null) {
            instance = new Calls();
        }
        return instance;
    }

    public void refresh() { calls.clear(); }

    public void addCall(Call call) {
        calls.add(call);
    }

    public void removeCall(Object call) {
        calls.remove(call);
    }

    public ArrayList<Call> getCalls() {
        return calls;
    }
}
