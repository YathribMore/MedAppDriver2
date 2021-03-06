package com.magsood.medappuser.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelSearchHospital {

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    ArrayList<String>days;

    public List<ModelServices> getModelServices() {
        return modelServices;
    }

    public void setModelServices(List<ModelServices> modelServices) {
        this.modelServices = modelServices;
    }

    List<ModelServices>modelServices;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    String specialization;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String price;


    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_lat() {
        return hospital_lat;
    }

    public void setHospital_lat(String hospital_lat) {
        this.hospital_lat = hospital_lat;
    }

    public String getHospital_lng() {
        return hospital_lng;
    }

    public void setHospital_lng(String hospital_lng) {
        this.hospital_lng = hospital_lng;
    }

    public String getHospital_city() {
        return hospital_city;
    }

    public void setHospital_city(String hospital_city) {
        this.hospital_city = hospital_city;
    }

    public String getDoc_days() {
        return doc_days;
    }

    public void setDoc_days(String doc_days) {
        this.doc_days = doc_days;
    }

    private String docID;
    String hospital_name;
    String hospital_address;
    String hospital_lat;
    String hospital_lng;
    String hospital_city;
    String doc_days;
    String doctor_name;

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }

    String doctor_phone;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String state;



}
