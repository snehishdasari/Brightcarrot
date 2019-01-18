package com.example.ravipavansne.brightcarrot;

public class Vehicles {

    private String price,starthr,startmin,startday,startmon,startyr;
    private String endhr,endmin,endday,endmon,endyr,contact;

    public Vehicles(String price, String starthr, String startmin, String startday, String startmon, String startyr, String endhr, String endmin, String endday, String endmon, String endyr, String contact) {
        this.price = price;
        this.starthr = starthr;
        this.startmin = startmin;
        this.startday = startday;
        this.startmon = startmon;
        this.startyr = startyr;
        this.endhr = endhr;
        this.endmin = endmin;
        this.endday = endday;
        this.endmon = endmon;
        this.endyr = endyr;
        this.contact = contact;
    }
    public Vehicles(){}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStarthr() {
        return starthr;
    }

    public void setStarthr(String starthr) {
        this.starthr = starthr;
    }

    public String getStartmin() {
        return startmin;
    }

    public void setStartmin(String startmin) {
        this.startmin = startmin;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    public String getStartmon() {
        return startmon;
    }

    public void setStartmon(String startmon) {
        this.startmon = startmon;
    }

    public String getStartyr() {
        return startyr;
    }

    public void setStartyr(String startyr) {
        this.startyr = startyr;
    }

    public String getEndhr() {
        return endhr;
    }

    public void setEndhr(String endhr) {
        this.endhr = endhr;
    }

    public String getEndmin() {
        return endmin;
    }

    public void setEndmin(String endmin) {
        this.endmin = endmin;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public String getEndmon() {
        return endmon;
    }

    public void setEndmon(String endmon) {
        this.endmon = endmon;
    }

    public String getEndyr() {
        return endyr;
    }

    public void setEndyr(String endyr) {
        this.endyr = endyr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
