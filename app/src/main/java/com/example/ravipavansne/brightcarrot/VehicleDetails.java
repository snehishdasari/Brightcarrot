package com.example.ravipavansne.brightcarrot;

public class VehicleDetails {


    public String vehiclename ;
    public String vehicleimage;
    public String vehicleno ;
    public String vehicleid ;
    public String ownerid ;
    public String rc ;
    public String dop ;
    public String type ;
    public String nokms ;
    public String psd ;
    public String colorv ;
    public String fuel ;
    public String rating ;
    public String availability ;
    public String price ;
    public String startday ;
    public String starttime ;
    public String endday ;
    public String endtime ;
    public String contactaddress ;
    public String contactphone ;
    public String ownername ;
    public String booked;
    public String bookedby ;

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getBookedby() {
        return bookedby;
    }

    public void setBookedby(String bookedby) {
        this.bookedby = bookedby;
    }


    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public VehicleDetails(String vehiclename, String vehicleimage, String vehicleno, String vehicleid, String ownerid, String rc, String dop, String type, String nokms, String psd, String colorv, String fuel, String rating, String availability, String price, String startday, String starttime, String endday, String endtime, String contactaddress, String contactphone,String ownername,String bookedby) {
        this.vehiclename = vehiclename;
        this.vehicleimage = vehicleimage;
        this.vehicleno = vehicleno;
        this.vehicleid = vehicleid;
        this.ownerid = ownerid;
        this.rc = rc;
        this.dop = dop;
        this.type = type;
        this.nokms = nokms;
        this.psd = psd;
        this.colorv = colorv;
        this.fuel = fuel;
        this.rating = rating;
        this.availability = availability;
        this.price = price;
        this.startday = startday;
        this.starttime = starttime;
        this.endday = endday;
        this.endtime = endtime;
        this.ownername = ownername ;
        this.contactaddress = contactaddress;
        this.contactphone = contactphone;
        this.bookedby = bookedby ;
    }

    public VehicleDetails(){}



    public VehicleDetails(String vehiclename, String vehicleimage, String vehicleno, String vehicleid, String ownerid, String rc, String dop, String type, String nokms, String psd, String colorv, String fuel, String rating) {
        this.vehiclename = vehiclename;
        this.vehicleimage = vehicleimage;
        this.vehicleno = vehicleno;
        this.vehicleid = vehicleid;
        this.ownerid = ownerid;
        this.rc = rc;
        this.dop = dop;
        this.type = type;
        this.nokms = nokms;
        this.psd = psd;
        this.colorv = colorv;
        this.fuel = fuel;
        this.rating = rating;
    }


    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getVehicleimage() {
        return vehicleimage;
    }

    public void setVehicleimage(String vehicleimage) {
        this.vehicleimage = vehicleimage;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNokms() {
        return nokms;
    }

    public void setNokms(String nokms) {
        this.nokms = nokms;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getColorv() {
        return colorv;
    }

    public void setColorv(String colorv) {
        this.colorv = colorv;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
