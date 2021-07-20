package com.example.hotel_reservation_system;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

public class HotelListViewModel extends AndroidViewModel {

    private HotelListRepository hotelListRepository;
    private LiveData<HotelListResponse> hotelListLiveData;
  //  private MutableLiveData<HotelList> hotelListMutableLiveData;

    public HotelListViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void init() {
        hotelListRepository = new HotelListRepository();
        hotelListLiveData = hotelListRepository.getHotelListLiveData();
    }

    public void searchHotelList() {
        hotelListRepository.setHotelList();
    }

    public LiveData<HotelListResponse> getHotelListLiveData() {
        return hotelListLiveData;
    }
}
