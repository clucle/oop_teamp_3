package com.android_dev.clucle.addressbook.entity;

public class CommunicateLog {

    private String type; // SEND, RECEIVE
    private String number;
    private String time;

    public CommunicateLog(String type, String number, String time) {
        this.type = type;
        this.number = number;
        this.time = time;
    }

    public String getType() {
        return type;
    }
    public String getNumber() {
        return number;
    }
    public String getTime() {
        return time;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        CommunicateLog other = (CommunicateLog) obj;
        if (!type.equals(other.getType())) return false;
        if (!number.equals(other.getNumber())) return false;
        if (!time.equals(other.getTime())) return false;

        return true;
    }
}
