package com.example.hotel_reservation_system;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://HotelReservationAPI-tx.us-east-1.elasticbeanstalk.com/")
                .build();

        ApiInterface api = adapter.create(ApiInterface.class);
        return api;
    }
}
