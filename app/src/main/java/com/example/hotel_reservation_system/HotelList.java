package com.example.hotel_reservation_system;

import java.util.List;

public class HotelList {
    //
    List<HotelListData> hotels_list;

    public void setHotelListData(List<HotelListData> hotelListData) {
        this.hotels_list = hotelListData;
    }

    public List<HotelListData> getHotelListData() {
        return hotels_list;
    }
}
