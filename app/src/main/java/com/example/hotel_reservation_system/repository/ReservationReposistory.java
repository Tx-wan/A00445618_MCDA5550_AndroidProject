package com.example.hotel_reservation_system.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.api.HotelListSearchService;
import com.example.hotel_reservation_system.api.ReservationConfirmService;
import com.example.hotel_reservation_system.model.ReservationModel;
import com.example.hotel_reservation_system.response.HotelListResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
