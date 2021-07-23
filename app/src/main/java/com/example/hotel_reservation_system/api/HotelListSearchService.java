package com.example.hotel_reservation_system.api;

import com.example.hotel_reservation_system.response.HotelListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelListSearchService {

    @GET("/getListOfHotels")
    Call<HotelListResponse> searchHotelList();
}
