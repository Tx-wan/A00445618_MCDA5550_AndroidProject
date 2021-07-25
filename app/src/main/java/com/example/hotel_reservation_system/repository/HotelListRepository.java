package com.example.hotel_reservation_system.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hotel_reservation_system.response.HotelListResponse;
import com.example.hotel_reservation_system.api.HotelListSearchService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelListRepository {

    //private static final String HOTEL_LIST_SEARCH_SERVICE_BASE_URL = "http://localhost:8080/";

    private static final String HOTEL_LIST_SEARCH_SERVICE_BASE_URL = "http://HotelReservationAPI-tx.us-east-1.elasticbeanstalk.com/";

    private HotelListSearchService hotelListSearchService;
    private MutableLiveData<HotelListResponse> hotelListLiveData;

  /*  private BookSearchService bookSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;*/

    public HotelListRepository() {
        hotelListLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        hotelListSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(HOTEL_LIST_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HotelListSearchService.class);

    }

    //
    public void setHotelList () {
        hotelListSearchService.searchHotelList().enqueue(new Callback<HotelListResponse>() {
            @Override
            public void onResponse(Call<HotelListResponse> call, Response<HotelListResponse> response) {
                if (response.body() != null) {
                    hotelListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<HotelListResponse> call, Throwable t) {
                hotelListLiveData.postValue(null);
            }
        });
    }

    public LiveData<HotelListResponse> getHotelListLiveData() {
        return hotelListLiveData;
    }
}
