package com.android_dev.clucle.addressbook.entity;

public class SMS extends CommunicateLog{

    private String content;

    public SMS(String type, String number, String time, String content) {
        super(type, number, time);
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
 }
