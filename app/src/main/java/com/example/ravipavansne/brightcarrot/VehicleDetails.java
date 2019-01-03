package com.example.ravipavansne.brightcarrot;

public class VehicleDetails {
    String Vehiclename ;
    String VehicleImage ;
    String VehicleNo ;
    String VehicleId ;
    String OwnerId ;
    String Rc1 ;
    String Rc2 ;
    String Dop ;
    String Numberofkms ;
    String PrevServicedDate ;
    String ColorOfVehicle ;
    String fuelUsed ;

    public VehicleDetails(){}

    public VehicleDetails(String vehiclename, String vehicleImage, String vehicleNo) {
        Vehiclename = vehiclename;
        VehicleImage = vehicleImage;
        VehicleNo = vehicleNo;
    }

    public VehicleDetails(String vehiclename, String vehicleImage, String vehicleNo, String vehicleId, String ownerId, String rc1, String rc2, String dop, String numberofkms, String prevServicedDate, String colorOfVehicle, String fuelUsed) {
        Vehiclename = vehiclename;
        VehicleImage = vehicleImage;
        VehicleNo = vehicleNo;
        VehicleId = vehicleId;
        OwnerId = ownerId;
        Rc1 = rc1;
        Rc2 = rc2;
        Dop = dop;
        Numberofkms = numberofkms;
        PrevServicedDate = prevServicedDate;
        ColorOfVehicle = colorOfVehicle;
        this.fuelUsed = fuelUsed;
    }

    public String getVehiclename() {
        return Vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        Vehiclename = vehiclename;
    }

    public String getVehicleImage() {
        return VehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        VehicleImage = vehicleImage;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    public String getRc1() {
        return Rc1;
    }

    public void setRc1(String rc1) {
        Rc1 = rc1;
    }

    public String getRc2() {
        return Rc2;
    }

    public void setRc2(String rc2) {
        Rc2 = rc2;
    }

    public String getDop() {
        return Dop;
    }

    public void setDop(String dop) {
        Dop = dop;
    }

    public String getNumberofkms() {
        return Numberofkms;
    }

    public void setNumberofkms(String numberofkms) {
        Numberofkms = numberofkms;
    }

    public String getPrevServicedDate() {
        return PrevServicedDate;
    }

    public void setPrevServicedDate(String prevServicedDate) {
        PrevServicedDate = prevServicedDate;
    }

    public String getColorOfVehicle() {
        return ColorOfVehicle;
    }

    public void setColorOfVehicle(String colorOfVehicle) {
        ColorOfVehicle = colorOfVehicle;
    }

    public String getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(String fuelUsed) {
        this.fuelUsed = fuelUsed;
    }
}
