package com.example.hotel_reservation_system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReservationModel {

    @SerializedName("hotel_name")
    @Expose
    private String hotelName;

    @SerializedName("checkin")
    @Expose
    private String checkInDate;

    @SerializedName("checkout")
    @Expose
    private String checkOutDate;

    @SerializedName("guests_list")
    @Expose
    private List<GuestModel> guestModelList;

    public ReservationModel(String hotelName, String checkInDate, String checkOutDate, List<GuestModel> guestModelList) {
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestModelList = guestModelList;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<GuestModel> getGuestModelList() {
        return guestModelList;
    }

    public void setGuestModelList(List<GuestModel> guestModelList) {
        this.guestModelList = guestModelList;
    }
}
