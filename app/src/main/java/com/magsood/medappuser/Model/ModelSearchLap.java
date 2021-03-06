package com.magsood.medappuser.Model;

import java.util.List;

public class ModelSearchLap {



    private String lapID;
    String lap_name;
    String labDiagnosisID;

    public String getLabDiagnosisID() {
        return labDiagnosisID;
    }

    public void setLabDiagnosisID(String labDiagnosisID) {
        this.labDiagnosisID = labDiagnosisID;
    }

    String price,lapPhone,state,city,address,note;


    public List<ModelServices> getModelServices() {
        return modelServices;
    }

    public void setModelServices(List<ModelServices> modelServices) {
        this.modelServices = modelServices;
    }

    List<ModelServices> modelServices;

    public String getLapID() {
        return lapID;
    }

    public void setLapID(String lapID) {
        this.lapID = lapID;
    }

    public String getLap_name() {
        return lap_name;
    }

    public void setLap_name(String lap_name) {
        this.lap_name = lap_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLapPhone() {
        return lapPhone;
    }

    public void setLapPhone(String lapPhone) {
        this.lapPhone = lapPhone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
