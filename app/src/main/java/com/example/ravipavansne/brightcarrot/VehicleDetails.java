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
