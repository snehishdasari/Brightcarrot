package com.example.ravipavansne.brightcarrot;

public class Vehicles {

    String name ;
    String desc ;
    int price ;
    int image ;
    String startdate ;
    String enddate ;
    int rating ;

    public Vehicles(String name, String desc, int price, int image, String startdate, String enddate, int rating) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.startdate = startdate;
        this.enddate = enddate;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
