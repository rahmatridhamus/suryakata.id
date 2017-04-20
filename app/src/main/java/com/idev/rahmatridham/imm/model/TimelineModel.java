package com.idev.rahmatridham.imm.model;

/**
 * Created by rahmatridham on 8/17/2016.
 */
public class TimelineModel {
    String id, date, title, desc, division, status, contact;

    public TimelineModel(String id, String date, String title, String desc, String division, String status, String contact) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.desc = desc;
        this.division = division;
        this.status = status;
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return title + "    " + division ;
    }
}
