package com.example.hotel_reservation_system.repository;

import com.example.hotel_reservation_system.api.ReservationConfirmService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * this class implement the method of building connection
 * getting data from API
 * */
public class ReservationReposistory {

    private static final String HOTEL_LIST_SEARCH_SERVICE_BASE_URL = "http://HotelReservationAPI-tx.us-east-1.elasticbeanstalk.com/";

    private ReservationConfirmService reservationConfirmService;

    private static ReservationReposistory instance;

    public static ReservationReposistory getInstance() {
        if (instance == null) {
            instance = new ReservationReposistory();
        }
        return instance;
    }

    public ReservationReposistory() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        reservationConfirmService = new retrofit2.Retrofit.Builder()
                .baseUrl(HOTEL_LIST_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReservationConfirmService.class);
    }

    public ReservationConfirmService getReservationConfirmService() {
        return reservationConfirmService;
    }

}
