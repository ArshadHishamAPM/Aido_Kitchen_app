package com.rathore.health.model;

/**
 * Created by archi on 12/23/2016.
 */

public class HospitalFindModel
{
    String strHospitalName,strHospitalLat,strHospitalLong,strHospitalAddress,hospitalId;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getStrHospitalName() {
        return strHospitalName;
    }

    public void setStrHospitalName(String strHospitalName) {
        this.strHospitalName = strHospitalName;
    }

    public String getStrHospitalLat() {
        return strHospitalLat;
    }

    public void setStrHospitalLat(String strHospitalLat) {
        this.strHospitalLat = strHospitalLat;
    }

    public String getStrHospitalLong() {
        return strHospitalLong;
    }

    public void setStrHospitalLong(String strHospitalLong) {
        this.strHospitalLong = strHospitalLong;
    }

    public String getStrHospitalAddress() {
        return strHospitalAddress;
    }

    public void setStrHospitalAddress(String strHospitalAddress) {
        this.strHospitalAddress = strHospitalAddress;
    }
}
