package com.example.contactapp;

public class ModelContact {

    private String id,name,image,phone,email,note,addedTime,updatedTime, latitude, longitude;

    // create constructor

    public ModelContact(String id, String name, String image, String phone, String email, String note, String addedTime, String updatedTime, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // create getter and setter method



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public  String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }
}
