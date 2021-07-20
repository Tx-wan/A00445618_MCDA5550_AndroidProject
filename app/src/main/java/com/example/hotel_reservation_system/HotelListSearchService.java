package com.example.hotel_reservation_system;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelListSearchService {

    @GET("/getListOfHotels")
    Call<HotelListResponse> searchHotelList();
}
