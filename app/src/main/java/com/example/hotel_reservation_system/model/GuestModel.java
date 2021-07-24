package com.example.hotel_reservation_system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuestModel {

    @SerializedName("guest_firstname")
    @Expose
    private String firstName;

    @SerializedName("guest_lastname")
    @Expose
    private String lastName;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("age")
    @Expose
    private String age;

    public GuestModel(String firstName, String lastName, String gender, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
