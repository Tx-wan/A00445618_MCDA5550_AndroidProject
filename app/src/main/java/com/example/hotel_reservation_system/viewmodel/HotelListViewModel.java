package com.example.hotel_reservation_system.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hotel_reservation_system.repository.HotelListRepository;
import com.example.hotel_reservation_system.response.HotelListResponse;

import org.jetbrains.annotations.NotNull;

/*
* The viewmode is designed to store and manage UI-related data in a lifecycle conscious way.
* */
public class HotelListViewModel extends AndroidViewModel {

    //used to build the connection to api
    private HotelListRepository hotelListRepository;

    // the data which can be managed by ViewModel
    private LiveData<HotelListResponse> hotelListLiveData;

    public HotelListViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    /*
    * initialize repository, including build connection with URL, interception
    * and initialize the data which is monitored and managed by ViewModel
    * */
    public void init() {
        hotelListRepository = new HotelListRepository();
        hotelListLiveData = hotelListRepository.getHotelListLiveData();
    }

    //sent request and get call back
    public void searchHotelList() {
        hotelListRepository.setHotelList();
    }

    public LiveData<HotelListResponse> getHotelListLiveData() {
        return hotelListLiveData;
    }
}
