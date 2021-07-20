package com.example.hotel_reservation_system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelListResponse {

    @SerializedName("hotels_list")
    @Expose
    List<HotelListData> hotels_list;

    public void setHotelListData(List<HotelListData> hotelListData) {
        this.hotels_list = hotelListData;
    }

    public List<HotelListData> getHotelListData() {
        return hotels_list;
    }
}
