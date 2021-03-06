package com.example.hotel_reservation_system.response;

import com.example.hotel_reservation_system.model.HotelListModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* the response is in the format of json. this class is used to analyze response body get with GET method
* */
public class HotelListResponse {

    @SerializedName("hotels_list")
    @Expose
    List<HotelListModel> hotels_list;

    public void setHotelListData(List<HotelListModel> hotelListData) {
        this.hotels_list = hotelListData;
    }

    public List<HotelListModel> getHotelListData() {
        return hotels_list;
    }
}
