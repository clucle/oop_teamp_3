package com.android_dev.clucle.addressbook.entity;

public class SMS extends CommunicateLog{

    private String content;

    public SMS(String type, String number, String time) {
        super(type, number, time);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
 }
