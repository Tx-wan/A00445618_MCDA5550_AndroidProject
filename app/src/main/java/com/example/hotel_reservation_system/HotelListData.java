package com.example.hotel_reservation_system;

public class HotelListData {

    //
    String hotelName;
    String price;
    String availability;

    public HotelListData(String hotelName, String price, String availability) {
        this.hotelName = hotelName;
        this.price = price;
        this.availability = availability;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
